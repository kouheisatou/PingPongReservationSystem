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

    open fun show() {
        DialogManager.showDialog(this@Dialog)
    }

    open fun close() {
        DialogManager.closeCurrentDialog()
    }

    fun showNextDialog(selection: Selection){
        this.selection = selection
        close()
        when(selection){
            Selection.Positive -> nextDialogOnSelectedPositive?.show()
            Selection.Negative -> nextDialogOnSelectedNegative?.show()
        }
    }

    open fun onPositiveButtonPressed() {
        positiveButtonAction?.invoke()
        showNextDialog(Selection.Positive)
    }

    open fun onNegativeButtonPressed() {
        negativeButtonAction?.invoke()
        showNextDialog(Selection.Negative)
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

