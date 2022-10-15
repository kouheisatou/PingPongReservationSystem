package components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import common.ReservationManager
import java.util.Calendar

class Reservation(val studentId: String) {
    val reservationTime: Calendar = Calendar.getInstance()
    val startTime: Calendar? = null
    val quitTime: Calendar? = null
    val playLimitTime: Calendar? = null
    var canceled = false
}

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