package components.dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class EquipmentDamageQuestionDialog(

    dialogManager: DialogManager,
    positiveButtonAction: (() -> Unit)?,
    negativeButtonAction: () -> Unit,
) : Dialog(dialogManager, positiveButtonAction, negativeButtonAction){

}

@Composable
fun EquipmentDamageQuestionDialogComponent(
    dialogModel: EquipmentDamageQuestionDialog
) {
    DialogComponent(
        dialogModel,
        "消耗品の交換",
        "交換が必要な消耗品はありますか？",
        positiveButton = {
            Button(onClick = {
                dialogModel.onPositiveButtonPressed()
            }) {
                Text("はい")
            }
        },
        negativeButton = {
            Button(onClick = { dialogModel.onNegativeButtonPressed() }) {
                Text("いいえ")
            }
        }
    )
}