package components.dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class EquipmentDamageQuestionDialog(

    dialogManager: DialogManager,
    positiveButtonAction: (() -> Unit)?,
    negativeButtonAction: (() -> Unit)?,
) : Dialog(dialogManager, positiveButtonAction, negativeButtonAction){

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EquipmentDamageQuestionDialogComponent(
    dialogModel: EquipmentDamageQuestionDialog
) {
    AlertDialog(

        title = { Text("予約") },
        text = { Text("予約しました") },
        onDismissRequest = {},
        buttons = {
            Button(onClick = {
                dialogModel.onPositiveButtonPressed()
            }) {
                Text("はい")
            }
            Button(onClick = {
                dialogModel.onNegativeButtonPressed()
            }) {
                Text("いいえ")
            }
        },
    )

}

enum class Selection{
    Positive, Negative
}