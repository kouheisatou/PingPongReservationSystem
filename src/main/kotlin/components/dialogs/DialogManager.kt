package components.dialogs

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

object DialogManager {
    private val _currentDialog: MutableState<Dialog?> = mutableStateOf(null)
    val currentDialog: State<Dialog?> = _currentDialog

    fun showDialog(dialog: Dialog){
        _currentDialog.value = dialog
    }

    fun closeCurrentDialog(){
        _currentDialog.value = null
    }
}