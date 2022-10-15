package components.dialogs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


abstract class Dialog(
    private val dialogManager: DialogManager,
    private val positiveButtonAction: (() -> Unit)?,
    private val negativeButtonAction: () -> Unit,
) {

    val isEnabledPositiveButton = positiveButtonAction != null

    fun show() {
        dialogManager.showDialog(this@Dialog)
    }

    open fun onPositiveButtonPressed() {
        positiveButtonAction?.invoke()
        dialogManager.closeCurrentDialog()
    }

    open fun onNegativeButtonPressed() {
        negativeButtonAction()
        dialogManager.closeCurrentDialog()
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DialogComponent(
    dialogModel: Dialog,
    title: String,
    text: String,
    positiveButton: (@Composable () -> Unit)?,
    negativeButton: (@Composable () -> Unit)?,
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
                positiveButton?.invoke()
            }

            negativeButton?.invoke()

            Button(onClick = { dialogModel.onNegativeButtonPressed() }) {
                Text("x")
            }
        },
    )
}

