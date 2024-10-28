import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

@Composable
@Preview
fun App() {
    var set by remember { mutableStateOf(1) }

    MaterialTheme(typography = Typography(defaultFontFamily = FontFamily.SansSerif)) {
        Box (modifier = Modifier.fillMaxSize()
            .background(Color(0, 12, 55))) {
            header(Modifier.align(Alignment.TopCenter))
            footer(Modifier.align(Alignment.BottomCenter))
        }

    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication,
        state = rememberWindowState(width = 1372.dp, height = 727.dp),
        undecorated = true
        ) {
        App()
    }
}
