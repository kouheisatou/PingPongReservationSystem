package common

import java.util.Calendar

class Reservation(val studentId: String) {
    val reservationTime: Calendar = Calendar.getInstance()
    val playStartTime: Calendar? = null
    val playEndTime: Calendar? = null
    val playLimitTime: Calendar? = null
    var canceled = false
}