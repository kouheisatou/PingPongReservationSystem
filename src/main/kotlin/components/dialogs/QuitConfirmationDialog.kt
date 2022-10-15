package components.dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class QuitConfirmationDialog(
    dialogManager: DialogManager,
    positiveButtonAction: (() -> Unit)?,
    negativeButtonAction: (() -> Unit)?,
) : Dialog(dialogManager, positiveButtonAction, negativeButtonAction){

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuitConfirmationDialogComponent(
    dialogModel: QuitConfirmationDialog
) {
    AlertDialog(
        title = { Text("利用終了") },
        text = { Text("利用終了しました") },
        onDismissRequest = {},
        buttons = {},
    )
}