package components.dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class QuitConfirmationDialog(
    dialogManager: DialogManager,
    positiveButtonAction: (() -> Unit)?,
    negativeButtonAction: () -> Unit,
) : Dialog(dialogManager, positiveButtonAction, negativeButtonAction){

}

@Composable
fun QuitConfirmationDialogComponent(
    dialogModel: QuitConfirmationDialog
) {
    DialogComponent(
        dialogModel,
        "利用終了",
        "利用終了しました",
        positiveButton = {
            Button(onClick = {dialogModel.onPositiveButtonPressed()}){
                Text("OK")
            }
        },
        negativeButton = null
    )
}