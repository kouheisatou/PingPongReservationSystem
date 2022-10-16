package components.dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class QuitConfirmationDialog : Dialog(null, null, null, null, null){

}

@Composable
fun QuitConfirmationDialogComponent(
    dialogModel: QuitConfirmationDialog
) {
    DialogComponent(
        dialogModel,
        "利用終了",
        "利用終了しました",
        positiveButtonText = "OK",
        negativeButtonText = "",
    )
}