import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.DragData
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import components.SelectMenu
import controlers.JavaVersion
import controlers.Software
import controlers.Version

@Composable
fun downloadWindow(initDownload: suspend ((Float) -> Unit) -> Unit, text: String, onClose: () -> Unit) {
    var progress by remember { mutableStateOf(0f) }

    val window = Window(
        title = "Download",
        onCloseRequest = {onClose()},
        state = rememberWindowState(width = 520.dp, height = 300.dp, position = WindowPosition.Aligned(Alignment.Center)),
        undecorated = true,


    ) {
        LaunchedEffect(Unit) {
            initDownload { newProgress ->
                progress = newProgress
                if (progress == 1f) {
                    onClose()
                }
            }
        }

        MaterialTheme {
            Box(modifier = Modifier.fillMaxSize().background(Color(0, 12, 55))) {
                Column(modifier = Modifier.fillMaxSize().padding(16.dp)
                    .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {

                    Image(
                        painter = painterResource("assets/images/logo.png"),
                        contentDescription = "Download",
                        modifier = Modifier.size(75.dp)
                    )

                    LinearProgressIndicator(progress = progress, modifier = Modifier.fillMaxWidth().height(10.dp), color = Color.Cyan)

                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Text(text, color = Color.White)
                        Text("${(progress * 100).toInt()}% concluído",color = Color.White)
                    }

                }
            }
        }
    }
}