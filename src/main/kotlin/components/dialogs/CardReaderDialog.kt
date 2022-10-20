package components.dialogs

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
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

                    validate(studentId)
                } catch (e: RCS300.FelicaException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun validate(studentId: String){

        // 学籍番号認証
        if (studentId.matches(Regex("[a-zA-Z]{2}[0-9]{5}")) && scanning) {
            println(studentId)
            val validateResult = onCardScanned?.invoke(studentId)
            println(validateResult)
            if (validateResult == CardReaderResult.Succeeded) {
                scanning = false
                stopScan()
                showNextDialog(Selection.Positive)
            } else {
                println("学籍番号が異なります")
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
        additionalButtons = {
            var studentIdTextBoxValue by remember { mutableStateOf("AF20000") }
            Row {
                OutlinedTextField(value = studentIdTextBoxValue, onValueChange = { text -> studentIdTextBoxValue = text })
                Button(
                    onClick = {
                        cardReaderDialog.validate(studentIdTextBoxValue)
                    }
                ) {
                    Text("学生証をかざした（仮）")
                }
            }
        }
    )
}

enum class CardReaderResult {
    Succeeded, Failed
}
