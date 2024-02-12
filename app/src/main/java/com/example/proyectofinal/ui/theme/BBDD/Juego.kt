package InfoArray

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import java.util.Date

data class Juego @OptIn(ExperimentalMaterial3Api::class) constructor(var nombre : String,var imagen : Int , var plastaformas : String, var fecha : Date) {
}
