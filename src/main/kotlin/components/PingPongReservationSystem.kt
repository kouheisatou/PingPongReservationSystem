package components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import common.CourtManager
import common.ReservationManager

@Composable
fun PingPongReservationSystem(){
    Row {
        Column {
            Row {
                CourtManager.courts.forEach {
                    CourtComponent(it)
                }
            }
            Button(onClick = {}){
                Text("備品の破損")
            }
            Button(onClick = {}){
                Text("お問い合わせ")
            }
        }
        Column {
            Button(onClick = {}){
                Text("予約する")
            }
            Text("予約リスト")
            ReservationListComponent(ReservationManager.reservations)
        }
    }
}

@Preview
@Composable
fun PingPongReservationSystemPreview(){
    PingPongReservationSystem()
}