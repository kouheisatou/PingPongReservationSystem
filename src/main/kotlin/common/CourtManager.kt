package common

import androidx.compose.runtime.mutableStateListOf
import components.Court


object CourtManager {
    private const val COURT_COUNT = 2
    val courts: MutableList<Court>

    init {
        courts = mutableStateListOf()
        for(i in 0 until 2){
            courts.add(Court("コート$i"))
        }
    }

}
