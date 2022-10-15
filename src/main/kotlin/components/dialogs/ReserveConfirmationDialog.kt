package components.dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class ReserveConfirmationDialog(
    dialogManager: DialogManager,
    positiveButtonAction: (() -> Unit)?,
    negativeButtonAction: (() -> Unit)?,
) : Dialog(dialogManager, positiveButtonAction, negativeButtonAction) {

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReserveConfirmationDialogComponent(
    dialogModel: ReserveConfirmationDialog
) {

    AlertDialog(

        title = { Text("予約") },
        text = { Text("予約しました") },
        onDismissRequest = {},
        buttons = {},
    )
}