import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import components.PingPongReservationSystem

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        PingPongReservationSystem()
    }
}
