package components.dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardReaderDialog(
    onReadSuccseeded: () -> Unit,
) {
    AlertDialog(

        title = { Text("") },
        text = { Text("学生証をカードリーダーにかざしてください")},
        onDismissRequest = {},
        buttons = {},
    )
}