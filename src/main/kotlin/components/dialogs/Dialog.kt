package components.dialogs

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


abstract class Dialog(
    private val dialogManager: DialogManager,
    private val positiveButtonAction: (() -> Unit)?,
    private val negativeButtonAction: (() -> Unit)?,
) {

    val isEnabledNegativeButton = negativeButtonAction != null
    val isEnabledPositiveButton = positiveButtonAction != null

    fun show() {
        dialogManager.showDialog(this@Dialog)
    }

    open fun onPositiveButtonPressed(){
        positiveButtonAction?.invoke()
        dialogManager.closeCurrentDialog()
    }

    open fun onNegativeButtonPressed(){
        negativeButtonAction?.invoke()
        dialogManager.closeCurrentDialog()
    }
}