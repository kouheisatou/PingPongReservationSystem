package components.dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class EquipmentDamageFormDialog(
    dialogManager: DialogManager,
    positiveButtonAction: (() -> Unit)?,
    negativeButtonAction: () -> Unit,
) : Dialog(dialogManager, positiveButtonAction, negativeButtonAction) {

}

@Composable
fun EquipmentDamageFormDialogComponent(
    dialogModel: EquipmentDamageFormDialog
) {
    DialogComponent(
        dialogModel,
        "消耗品の申告",
        "消耗品を選択してください",
        positiveButton = {
            Button(onClick = {dialogModel.onPositiveButtonPressed()}){
                Text("確定")
            }
        },
        negativeButton = {
            Button(onClick = { dialogModel.onNegativeButtonPressed() }) {
                Text("キャンセル")
            }
        }
    )
}