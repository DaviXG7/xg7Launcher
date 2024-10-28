import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class Styles(val style: Modifier) {

    HEADER_BUTTON(Modifier
        .border(1.dp, Color.White, shape = RoundedCornerShape(10.dp))
        .background(Color(0, 0, 0, 0))

    )

}