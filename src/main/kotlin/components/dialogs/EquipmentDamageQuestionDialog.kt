package components.dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class EquipmentDamageQuestionDialog(
    nextDialogOnSelectedPositive: Dialog?,
    nextDialogOnSelectedNegative: Dialog?,
    positiveButtonAction: (() -> Unit)?,
    negativeButtonAction: (() -> Unit)?,
    cancelButtonAction: (() -> Unit)?,
) : Dialog(nextDialogOnSelectedPositive, nextDialogOnSelectedNegative, positiveButtonAction, negativeButtonAction, cancelButtonAction){

}

@Composable
fun EquipmentDamageQuestionDialogComponent(
    dialogModel: EquipmentDamageQuestionDialog
) {
    DialogComponent(
        dialogModel,
        "消耗品の交換",
        "交換が必要な消耗品はありますか？",
        positiveButtonText = "はい",
        negativeButtonText = "いいえ",
    )
}