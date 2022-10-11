package common

object ReservationManager {
    private val reservations: MutableList<Reservation> = mutableListOf()

    fun reserve(reservation: Reservation){
        reservations.add(reservation)
    }

    fun cancel(reservation: Reservation){
        reservations.remove(reservation)
    }

    // コートに割り当て
    fun allocation(){

    }
}