package components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import common.CourtManager
import common.ReservationManager
import components.dialogs.*
import java.util.Calendar

@Composable
fun PingPongReservationSystem() {

    Row {
        Column {
            LazyRow {
                items(CourtManager.courts.size) { index ->
                    CourtComponent(CourtManager.courts[index])
                }
            }
            Button(onClick = {
                EquipmentDamageFormDialog(
                    DialogManager, positiveButtonAction = {}, negativeButtonAction = {},
                ).show()
            }) {
                Text("備品の破損")
            }
            Button(onClick = {}) {
                Text("お問い合わせ")
            }
        }
        Column {
            Button(onClick = {
                CardReaderDialog(
                    DialogManager,
                    positiveButtonAction = {
                        ReservationManager.reserve(Calendar.getInstance().timeInMillis.toString())
                    },
                    negativeButtonAction = {},
                ).show()
            }) {
                Text("予約する")
            }
            Text("予約リスト")
            ReservationListComponent(ReservationManager.reservations)
        }
    }

    // showing dialog
    when (DialogManager.currentDialog.value) {
        is CardReaderDialog -> {
            CardReaderDialogComponent(DialogManager.currentDialog.value as CardReaderDialog)
        }
        is QuitConfirmationDialog -> {
            QuitConfirmationDialogComponent(DialogManager.currentDialog.value as QuitConfirmationDialog)
        }
        is EquipmentDamageFormDialog -> {
            EquipmentDamageFormDialogComponent(DialogManager.currentDialog.value as EquipmentDamageFormDialog)
        }
        is EquipmentDamageQuestionDialog -> {
            EquipmentDamageQuestionDialogComponent(DialogManager.currentDialog.value as EquipmentDamageQuestionDialog)
        }
        is ReserveConfirmationDialog -> {
            ReserveConfirmationDialogComponent(DialogManager.currentDialog.value as ReserveConfirmationDialog)
        }
        else -> {}
    }
}
