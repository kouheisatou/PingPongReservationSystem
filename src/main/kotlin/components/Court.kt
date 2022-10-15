package components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import common.ReservationManager
import components.dialogs.DialogManager
import components.dialogs.QuitConfirmationDialog

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

@Composable
fun CourtComponent(court: Court) {
    Column {
        Text(court.name)
        if (court.playingUser.value == null) {
            Text("空き")
        } else {
            Text("利用中")
            Text(court.playingUser.value!!.studentId)
            Text("${court.playingUser.value!!.startTime?.time} ~ ${court.playingUser.value!!.playLimitTime?.time}")
            Button(
                onClick = {
                    court.finishPlaying()
                    QuitConfirmationDialog(
                        DialogManager, positiveButtonAction = {}, negativeButtonAction = {},
                    ).show()
                },
            ) {
                Text("利用終了")
            }
        }
    }
}