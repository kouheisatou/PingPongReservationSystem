package common

import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.Pointer

const val SHIBAURA_STUDENT_CARD_POLLING: Short = 0x8277.toShort()
const val SHIBAURA_STUDENT_CARD_SERVICE_CODE: Int = 0x010B

class RCS300 {
    interface FelicaLib : Library {
        fun pasori_open(dummy: String?): Pointer?
        fun pasori_init(pasoriHandle: Pointer?): Int
        fun pasori_close(pasoriHandle: Pointer?)
        fun felica_polling(pasoriHandle: Pointer?, systemCode: Short, rfu: Byte, time_slot: Byte): Pointer?
        fun felica_free(felicaHandle: Pointer?)
        fun felica_getidm(felicaHandle: Pointer?, data: ByteArray?)
        fun felica_getpmm(felicaHandle: Pointer?, data: ByteArray?)
        fun felica_read_without_encryption02(
            felicaHandle: Pointer?,
            serviceCode: Int,
            mode: Int,
            addr: Byte,
            data: ByteArray?
        ): Int

        companion object {
            val INSTANCE = Native.load(
                "felicalib64",
                FelicaLib::class.java
            ) as FelicaLib
        }
    }

    inner class FelicaException(string: String?) : Exception(string)

    var pasoriHandle: Pointer? = FelicaLib.INSTANCE.pasori_open(null)
    var felicaHandle: Pointer? = null

    init {
        if (pasoriHandle == null) {
            throw FelicaException("felicalib.dllを開けません")
        }
        if (FelicaLib.INSTANCE.pasori_init(pasoriHandle) != 0) {
            throw FelicaException("PaSoRiに接続できません")
        }
    }

    fun close() {
        if (felicaHandle !== Pointer.NULL) {
            FelicaLib.INSTANCE.felica_free(felicaHandle)
        }
        if (pasoriHandle !== Pointer.NULL) {
            FelicaLib.INSTANCE.pasori_close(pasoriHandle)
        }
    }

    @Throws(FelicaException::class)
    fun polling(systemCode: Short) {
        FelicaLib.INSTANCE.felica_free(felicaHandle)
        felicaHandle = FelicaLib.INSTANCE.felica_polling(pasoriHandle, systemCode, 0.toByte(), 0.toByte())
        if (felicaHandle === Pointer.NULL) {
            throw FelicaException("カード読み取り失敗")
        }
    }

    @get:Throws(FelicaException::class)
    val iDm: ByteArray
        get() {
            if (felicaHandle === Pointer.NULL) {
                throw FelicaException("no polling executed.")
            }
            val buf = ByteArray(8)
            FelicaLib.INSTANCE.felica_getidm(felicaHandle, buf)
            return buf
        }

    fun readBlock(serviceCode: Int, addr: Byte): ByteArray {

        if (felicaHandle === Pointer.NULL) {
            throw FelicaException("no polling executed.")
        }
        val buf = ByteArray(16)
        FelicaLib.INSTANCE.felica_read_without_encryption02(felicaHandle, serviceCode, 0, addr, buf)

        return buf
    }

    /**
     * FelicaカードのID番号を取得するメソッド
     * @param systemCode システムコード(例えばSuicaは0x03、ワイルドカードは0xFF)
     * @return カードのID番号
     * @throws FelicaException
     */
    @Throws(FelicaException::class)
    fun getID(systemCode: Short): String {
        FelicaLib.INSTANCE.felica_free(felicaHandle)
        felicaHandle = FelicaLib.INSTANCE.felica_polling(pasoriHandle, systemCode, 0.toByte(), 0.toByte())
        if (felicaHandle === Pointer.NULL) {
            throw FelicaException("カード読み取り失敗")
        }
        val buf = ByteArray(8)
        FelicaLib.INSTANCE.felica_getidm(felicaHandle, buf)
        return String.format(
            "%02X%02X%02X%02X%02X%02X%02X%02X",
            buf[0], buf[1], buf[2], buf[3], buf[4], buf[5], buf[6], buf[7]
        )
    }
}

fun main() {

    try {
        val rcS300 = RCS300()

        println("カードリーダーに学生証をかざしてください")

        while(true){

            try{

                // ポーリング
                rcS300.polling(SHIBAURA_STUDENT_CARD_POLLING)

                // 学籍番号抽出
                val studentId = String(rcS300.readBlock(SHIBAURA_STUDENT_CARD_SERVICE_CODE, 0x00)).substring(3..9)

                println(studentId)
            }catch (_: RCS300.FelicaException){

            }
        }
    } catch (e: RCS300.FelicaException) {
        System.err.println("Cannot access to RCS300")
    }
}