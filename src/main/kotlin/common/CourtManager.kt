package common


object CourtManager {
    private const val COURT_COUNT = 2

    val courts: Array<Court> = Array(COURT_COUNT){ Court("コート${it}") }
}
