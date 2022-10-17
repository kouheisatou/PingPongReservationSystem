package components.dialogs

import androidx.compose.runtime.Composable

class CancelConfirmationDialog: Dialog(null, null, null, null, null) {

}

@Composable
fun CcancelConfirmationDialogComponent(dialogModel: CancelConfirmationDialog){

    DialogComponent(
        dialogModel,
        "キャンセル",
        "予約をキャンセルしました",
        positiveButtonText = "OK",
        negativeButtonText = "",
    )
}