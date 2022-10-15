package components.dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class ReserveConfirmationDialog(
    dialogManager: DialogManager,
    positiveButtonAction: (() -> Unit)?,
    negativeButtonAction: () -> Unit,
) : Dialog(dialogManager, positiveButtonAction, negativeButtonAction) {

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReserveConfirmationDialogComponent(
    dialogModel: ReserveConfirmationDialog
) {

    DialogComponent(
        dialogModel,
        "予約",
        "予約しました",
        positiveButton = {
            Button(onClick = {dialogModel.onPositiveButtonPressed()}){
                Text("OK")
            }
        },
        negativeButton = null,
    )
}