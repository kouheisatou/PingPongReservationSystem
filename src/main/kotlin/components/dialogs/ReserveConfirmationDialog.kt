package components.dialogs

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class ReserveConfirmationDialog : Dialog(null, null, null, null, null) {

}

@Composable
fun ReserveConfirmationDialogComponent(
    dialogModel: ReserveConfirmationDialog
) {

    DialogComponent(
        dialogModel,
        "予約",
        "予約しました",
        positiveButtonText = "OK",
        negativeButtonText = "",
    )
}