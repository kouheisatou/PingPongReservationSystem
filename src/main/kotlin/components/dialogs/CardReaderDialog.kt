package components.dialogs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class CardReaderDialog(
    dialogManager: DialogManager,
    positiveButtonAction: (() -> Unit)?,
    negativeButtonAction: () -> Unit,
) : Dialog(dialogManager, positiveButtonAction, negativeButtonAction) {

    override fun onPositiveButtonPressed() {
        super.onPositiveButtonPressed()
    // TODO 学籍番号をカードリーダーから取得
    }
}

@Composable
fun CardReaderDialogComponent(cardReaderDialog: CardReaderDialog) {
    DialogComponent(
        cardReaderDialog,
        "学籍番号確認",
        "学生証をカードリーダーにかざしてください",
        positiveButton = {
            Button(onClick = {cardReaderDialog.onPositiveButtonPressed()}){
                Text("学生証かざした（仮）")
            }
        },
        negativeButton = {
            Button(onClick = { cardReaderDialog.onNegativeButtonPressed() }) {
                Text("キャンセル")
            }
        }
    )
}


