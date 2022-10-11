package components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import common.Reservation
import common.ReservationManager

@Composable
fun ReservationListComponent(reservations: List<Reservation>){
    LazyColumn {
        items(reservations.size){ index ->
            ReservationRowComponent(reservations[index])
        }
    }
}

@Composable
fun ReservationRowComponent(reservation: Reservation){
    Row{
        Text(reservation.studentId)
        Text(reservation.reservationTime.time.toString())
        Button(onClick = {
            ReservationManager.cancel(reservation)
        }){
            Text("キャンセル")
        }
    }
}