package components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import common.CourtManager
import common.ReservationManager
import components.dialogs.*

class Court(val name: String) {
    var playingUser = mutableStateOf<Reservation?>(null)

    fun startPlaying(user: Reservation) {
        this.playingUser.value = user
    }

    fun finishPlaying() {
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
                    CardReaderDialog(
                        nextDialog = EquipmentDamageQuestionDialog(
                            nextDialogOnSelectedPositive = EquipmentDamageFormDialog(
                                nextDialog = QuitConfirmationDialog(),
                                positiveButtonAction = {
                                    court.finishPlaying()
                                },
                                null,
                                null,
                            ),
                            nextDialogOnSelectedNegative = QuitConfirmationDialog(),
                            positiveButtonAction = {},
                            negativeButtonAction = {
                                court.finishPlaying()
                            },
                            null,
                        ),
                        onCardScanned = { studentId ->
                            if(court.playingUser.value?.studentId == studentId){
                                Result.Succeeded
                            }else{
                                Result.Failed
                            }
                        }
                    ).show()
                },
            ) {
                Text("利用終了")
            }
        }
    }
}