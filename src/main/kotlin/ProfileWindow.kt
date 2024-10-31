import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import components.SelectMenu
import controlers.Versions

@Composable
fun show(screen: Screen, onClose: () ->  Unit) {

    Window(
        onCloseRequest = { onClose() },
        state = rememberWindowState(width = 430.dp, height = 600.dp),
        title = "Formulário",
    ) {
        MaterialTheme {

            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(0, 12, 55,210))) {

                if (screen = Screen.PROFILE_SPIGOT) {
                    SelectMenu(
                        listOf(Versions.Server)
                    )

                }


            }

        }
    }

}
