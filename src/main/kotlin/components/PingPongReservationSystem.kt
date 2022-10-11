package components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import common.CourtManager
import common.Reservation
import common.ReservationManager
import java.util.Calendar

val dialogState = mutableStateOf(DialogState.CardReader)
@Composable
fun PingPongReservationSystem() {

    Row {
        Column {
            LazyRow {
                items(CourtManager.courts.size) { index ->
                    CourtComponent(CourtManager.courts[index])
                }
            }
            Button(onClick = {}) {
                Text("備品の破損")
            }
            Button(onClick = {}) {
                Text("お問い合わせ")
            }
        }
        Column {
            Button(onClick = {
                // TODO 学籍番号をカードリーダーから取得
                ReservationManager.reserve(Calendar.getInstance().timeInMillis.toString())
            }) {
                Text("予約する")
            }
            Text("予約リスト")
            ReservationListComponent(ReservationManager.reservations)
        }
    }
}

@Preview
@Composable
fun PingPongReservationSystemPreview() {
    PingPongReservationSystem()
}

enum class DialogState{
    CardReader,
    EquipmentDamageForm,
    EndConfirmation,
    ReserveConfirmation,
}

fun endFlow(){

}

fun reservationFlow(){

}
