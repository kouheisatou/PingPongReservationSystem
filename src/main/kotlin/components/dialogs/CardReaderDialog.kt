package components.dialogs

import androidx.compose.runtime.Composable
import common.RCS300
import common.SHIBAURA_STUDENT_CARD_POLLING
import common.SHIBAURA_STUDENT_CARD_SERVICE_CODE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardReaderDialog(
    nextDialog: Dialog?,
    val onCardScanned: ((studentId: String) -> CardReaderResult)?,
) : Dialog(nextDialog, null, null, null, null) {

    var rcS300: RCS300? = null
    var scanning: Boolean = false

    init {
        // カードリーダを初期化
        try {
            rcS300 = RCS300()
        } catch (e: Throwable) {
            System.err.println("Cannot connect RC-S300")
            close()
        }
    }

    fun startScan() {

        scanning = true
        CoroutineScope(Dispatchers.IO).launch {
            while (scanning) {
                try {
                    // 学籍番号読み取り
                    rcS300?.polling(SHIBAURA_STUDENT_CARD_POLLING)
                    val studentId =
                        String(rcS300?.readBlock(SHIBAURA_STUDENT_CARD_SERVICE_CODE, 0) ?: continue).substring(3..9)

                    // 学籍番号認証
                    if (studentId.matches(Regex("[a-zA-Z]{2}[0-9]{5}")) && scanning) {
                        println(studentId)
                        val validateResult = onCardScanned?.invoke(studentId)
                        if (validateResult == CardReaderResult.Succeeded) {
                            scanning = false
                            stopScan()
                            showNextDialog(Selection.Positive)
                        } else {
                            println("学籍番号が異なります")
                        }
                    }
                } catch (e: RCS300.FelicaException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun stopScan() {
        scanning = false
    }

    override fun show() {
        super.show()
        startScan()
    }

    override fun close() {
        stopScan()
        rcS300?.close()
        super.close()
    }
}

@Composable
fun CardReaderDialogComponent(cardReaderDialog: CardReaderDialog) {
    cardReaderDialog.startScan()
    DialogComponent(
        cardReaderDialog,
        "学籍番号確認",
        "学生証をカードリーダーにかざしてください",
        positiveButtonText = "",
        negativeButtonText = "",
    )
}

enum class CardReaderResult {
    Succeeded, Failed
}
