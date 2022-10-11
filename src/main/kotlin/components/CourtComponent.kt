package components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import common.Court
import common.Reservation

@Composable
fun CourtComponent(court: Court) {
    Column {
        Text(court.name)
        if (court.playingUser == null) {
            Text("空き")
        } else {
            Text("利用中")
            Text(court.playingUser!!.studentId)
            Text("${court.playingUser!!.playStartTime?.time} ~ ${court.playingUser!!.playLimitTime?.time}")
            Button(
                onClick = {
                    court.finishPlaying()
                },
            ) {
                Text("利用終了")
            }
        }
    }
}

@Preview
@Composable
fun CourtComponentPreview() {
    val c = Court("コートA")
    val r = Reservation("AA00000")
    c.startPlaying(r)
    CourtComponent(c)
}