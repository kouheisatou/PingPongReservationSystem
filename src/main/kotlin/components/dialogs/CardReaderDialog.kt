package components.dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class CardReaderDialog(
    dialogManager: DialogManager,
    positiveButtonAction: (() -> Unit)?,
    negativeButtonAction: (() -> Unit)?,
) : Dialog(dialogManager, positiveButtonAction, negativeButtonAction) {

    // TODO 学籍番号をカードリーダーから取得
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardReaderDialogComponent(cardReaderDialog: CardReaderDialog) {
    AlertDialog(

        title = { Text("学籍番号確認") },
        text = { Text("学生証をカードリーダーにかざしてください") },
        onDismissRequest = {},
        buttons = {

            if(cardReaderDialog.isEnabledPositiveButton){
                Button(onClick = {cardReaderDialog.onPositiveButtonPressed()}){
                    Text("学生証かざした（仮）")
                }
            }

            if(cardReaderDialog.isEnabledNegativeButton){
                Button(onClick = {cardReaderDialog.onNegativeButtonPressed()}){
                    Text("キャンセル")
                }
            }
        },
    )
}


