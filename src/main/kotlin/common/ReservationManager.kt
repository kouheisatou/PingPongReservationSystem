package common

import androidx.compose.runtime.mutableStateListOf
import components.Reservation

object ReservationManager {
    val reservations = mutableStateListOf<Reservation>()

    fun reserve(studentId: String){
        val newReservation = Reservation(studentId)

        allocate(newReservation)
    }

    fun cancel(reservation: Reservation){
        reservations.remove(reservation)
    }

    // コートに割り当て
    fun allocate(reservation: Reservation){

        // コートに空きがない
        if(CourtManager.courts.all { it.playingUser.value != null }){
            reservations.add(reservation)
        }
        // コートに空きがある
        else{
            for (it in CourtManager.courts) {
                if(it.playingUser.value == null){
                    it.startPlaying(reservation)
                    break
                }
            }
        }
    }

    fun allocateFirst(){
        if(reservations.isEmpty()) return

        allocate(reservations.first())
        reservations.remove(reservations.first())
    }
}