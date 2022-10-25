import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import components.PingPongReservationSystemClient

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        PingPongReservationSystemClient()
    }
}
