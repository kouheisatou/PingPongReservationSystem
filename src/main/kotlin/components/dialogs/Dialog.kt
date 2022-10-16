package components.dialogs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.*


abstract class Dialog(
    private val nextDialogOnSelectedPositive: Dialog?,
    private val nextDialogOnSelectedNegative: Dialog?,
    private val positiveButtonAction: (() -> Unit)?,
    private val negativeButtonAction: (() -> Unit)?,
    private val cancelButtonAction: (() -> Unit)?,
) {

    var selection: Selection? = null

    var isEnabledPositiveButton = positiveButtonAction != null
    var isEnabledNegativeButton = negativeButtonAction != null

    fun show() {
        DialogManager.showDialog(this@Dialog)
    }

    fun close() {
        DialogManager.closeCurrentDialog()
    }

    open fun onPositiveButtonPressed() {
        positiveButtonAction?.invoke()
        selection = Selection.Positive
        close()
        nextDialogOnSelectedPositive?.show()
    }

    open fun onNegativeButtonPressed() {
        negativeButtonAction?.invoke()
        selection = Selection.Negative
        close()
        nextDialogOnSelectedNegative?.show()
    }

    open fun onCancelButtonPressed(){
        cancelButtonAction?.invoke()
        selection = null
        close()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DialogComponent(
    dialogModel: Dialog,
    title: String,
    text: String,
    positiveButtonText: String,
    negativeButtonText: String,
    additionalButtons: (@Composable () -> Unit)? = null,
) {
    AlertDialog(
        modifier = Modifier.fillMaxSize(),

        title = { Text(title) },
        text = { Text(text) },
        onDismissRequest = {},
        buttons = {

            additionalButtons?.invoke()

            if (dialogModel.isEnabledPositiveButton) {
                Button(onClick = {dialogModel.onPositiveButtonPressed()}){
                    Text(positiveButtonText)
                }
            }

            if(dialogModel.isEnabledNegativeButton){
                Button(onClick = {dialogModel.onNegativeButtonPressed()}){
                    Text(negativeButtonText)
                }
            }

            Button(onClick = { dialogModel.onCancelButtonPressed() }) {
                Text("x")
            }
        },
    )
}

