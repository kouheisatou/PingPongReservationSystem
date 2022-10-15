package components.dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class EquipmentDamageFormDialog(
    dialogManager: DialogManager,
    positiveButtonAction: (() -> Unit)?,
    negativeButtonAction: (() -> Unit)?,
) : Dialog(dialogManager, positiveButtonAction, negativeButtonAction) {

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EquipmentDamageFormDialogComponent(
    dialogModel: EquipmentDamageFormDialog
) {
    AlertDialog(
        title = { Text("消耗品の申告") },
        text = { Text("消耗品を選択してください") },
        onDismissRequest = {},
        buttons = {},
    )
}