package components.dialogs

class DialogFlow(firstDialog: Dialog) {

    private val firstDialog: DialogFlowItem = DialogFlowItem(firstDialog)
    private var currentSettingDialog: DialogFlowItem = this.firstDialog
    private var currentDisplayingDialog: DialogFlowItem = this.firstDialog

    fun addDialog(nextDialog: Dialog): DialogFlow{
        currentSettingDialog.addNextDialog(nextDialog)
        return this
    }

    fun addDialog(
        nextDialogOnSelectedPositive: Dialog,
        nextDialogOnSelectedNegative: Dialog,
    ): DialogFlow{
        currentSettingDialog.addNextDialog(nextDialogOnSelectedPositive, nextDialogOnSelectedNegative)
        return this
    }

    fun start(){

    }
}

class DialogFlowItem(val currentDialog: Dialog) {
    private val _nextDialogs = mutableListOf<DialogFlowItem>()
    val nextDialogs: List<DialogFlowItem> = _nextDialogs
    var selection: Selection? = null

//    constructor(currentDialog: Dialog, nextDialog: Dialog) : this(currentDialog) {
//        this._nextDialogs.add(DialogFlowItem(nextDialog))
//    }

//    constructor(
//        currentDialog: Dialog,
//        selection: Selection,
//        nextDialogOnSelectedPositive: Dialog,
//        nextDialogOnSelectedNegative: Dialog,
//    ) : this(
//        currentDialog
//    ) {
//        this.selection = selection
//        this._nextDialogs.add(DialogFlowItem(nextDialogOnSelectedPositive))
//        this._nextDialogs.add(DialogFlowItem(nextDialogOnSelectedNegative))
//    }

    fun addNextDialog(nextDialog: Dialog){
        _nextDialogs.add(DialogFlowItem(nextDialog))
    }

    fun addNextDialog(
        nextDialogOnSelectedPositive: Dialog,
        nextDialogOnSelectedNegative: Dialog,
    ){
        _nextDialogs.add(DialogFlowItem(nextDialogOnSelectedPositive))
        _nextDialogs.add(DialogFlowItem(nextDialogOnSelectedNegative))
    }
}

enum class Selection {
    Positive, Negative
}