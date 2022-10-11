package common

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class Court(val name: String) {
    var playingUser = mutableStateOf<Reservation?>(null)

    fun startPlaying(user: Reservation){
        this.playingUser.value = user
    }

    fun finishPlaying(){
        this.playingUser.value = null
        ReservationManager.allocateFirst()
    }
}