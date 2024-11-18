import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import components.*
import controlers.assets

enum class Screen {
    MAIN, PROFILE_MINECRAFT, PROFILE_SPIGOT
}

@Composable
@Preview
fun App() {
    var currentPage by remember { mutableStateOf(Screen.MAIN) }
    var showCreateWindow by remember { mutableStateOf(false) }


    MaterialTheme(typography = Typography(defaultFontFamily = FontFamily.SansSerif)) {
        Box (modifier = Modifier.fillMaxSize()
            .background(Color(0, 12, 55))) {
            header(Modifier.align(Alignment.TopCenter), onPageChange = { page -> currentPage = page })

                when (currentPage) {
                    Screen.MAIN -> {
                        Row (Modifier.align(Alignment.Center), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            MinecraftBootProfile(Modifier)
                            ServerBootProfile(Modifier)
                        }
                    }

                    Screen.PROFILE_MINECRAFT -> {
                        Profiles(Modifier.align(Alignment.Center))
                    }
                    Screen.PROFILE_SPIGOT -> {
                        Profiles(Modifier.align(Alignment.Center))
                    }
            }
            footer(Modifier.align(Alignment.BottomCenter))
            if (currentPage == Screen.PROFILE_MINECRAFT || currentPage == Screen.PROFILE_SPIGOT) {
                FloatingActionButton(
                    backgroundColor = Color.Green,
                    onClick = { showCreateWindow = true },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                        .offset((-40).dp, (-60).dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Adicionar perfil")
                }
            }
        }
    }
    if (showCreateWindow) {
        show(currentPage, onClose = { showCreateWindow = false })
    }

}


fun main() = application {
    assets()
    Window(onCloseRequest = ::exitApplication,
        state = rememberWindowState(width = 1372.dp, height = 727.dp, position = WindowPosition.Aligned(Alignment.Center)),
        icon = painterResource("assets/images/logo.png"),
        title = "XG7Launcher"
    ) {
        App()
    }

}
