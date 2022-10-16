package components.dialogs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class CardReaderDialog(
    nextDialog: Dialog?,
    positiveButtonAction: (() -> Unit)?,
    negativeButtonAction: (() -> Unit)?,
    cancelButtonAction: (() -> Unit)?,
) : Dialog(nextDialog, null, positiveButtonAction, negativeButtonAction, cancelButtonAction) {

    override fun onPositiveButtonPressed() {
        super.onPositiveButtonPressed()
    // TODO 学籍番号をカードリーダーから取得
    }
}

@Composable
fun CardReaderDialogComponent(cardReaderDialog: CardReaderDialog) {
    DialogComponent(
        cardReaderDialog,
        "学籍番号確認",
        "学生証をカードリーダーにかざしてください",
        positiveButtonText = "学生証かざした（仮）",
        negativeButtonText = "キャンセル",
    )
}


