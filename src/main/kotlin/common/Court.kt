package common

class Court(val name: String) {
    var playingUser: Reservation? = null
        get() {return field}
        private set(value) {
            field = value
        }

    fun startPlaying(user: Reservation){
        this.playingUser = user
    }

    fun finishPlaying(){

    }
}