package components.dialogs

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class EquipmentDamageFormDialog(
    nextDialog: Dialog?,
    positiveButtonAction: (() -> Unit)?,
    negativeButtonAction: (() -> Unit)?,
    cancelButtonAction: (() -> Unit)?,
) : Dialog( nextDialog, null, positiveButtonAction, negativeButtonAction, cancelButtonAction) {

}

@Composable
fun EquipmentDamageFormDialogComponent(
    dialogModel: EquipmentDamageFormDialog
) {
    DialogComponent(
        dialogModel,
        "消耗品の申告",
        "消耗品を選択してください",
        positiveButtonText = "送信",
        negativeButtonText = "キャンセル",
    )
}