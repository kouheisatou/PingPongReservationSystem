package components.dialogs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import components.dialogs.Result

class CardReaderDialog(
    nextDialog: Dialog?,
    val onCardScanned: ((studentId: String) -> Result)?,
) : Dialog(nextDialog, null, null, null, null) {

    fun scanCard(): Result{
        val studentId = "学籍番号"
        return onCardScanned?.invoke(studentId) ?: Result.Failed
    }
}

@Composable
fun CardReaderDialogComponent(cardReaderDialog: CardReaderDialog) {
    DialogComponent(
        cardReaderDialog,
        "学籍番号確認",
        "学生証をカードリーダーにかざしてください",
        positiveButtonText = "",
        negativeButtonText = "",
        additionalButtons = {
            Button(onClick = {
                when(cardReaderDialog.scanCard()){
                    Result.Succeeded -> cardReaderDialog.showNextDialog(Selection.Positive)
                    Result.Failed -> {}
                }
            }){
                Text("学生証かざした（仮）")
            }
        }
    )
}

enum class Result{
    Succeeded, Failed
}
