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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import components.*
import controlers.Profile

enum class Screen {
    MAIN, PROFILE_MINECRAFT, PROFILE_SPIGOT
}

@Composable
@Preview
fun App() {
    var currentPage by remember { mutableStateOf(Screen.MAIN) }
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
                        Profiles(Modifier.align(Alignment.Center), Profile.Type.MINECRAFT)
                    }
                    Screen.PROFILE_SPIGOT -> {
                        Profiles(Modifier.align(Alignment.Center), Profile.Type.SPIGOT)
                    }
            }
            footer(Modifier.align(Alignment.BottomCenter))
            if (currentPage == Screen.PROFILE_MINECRAFT || currentPage == Screen.PROFILE_SPIGOT) {
                FloatingActionButton(
                    backgroundColor = Color.Green,
                    onClick = { /* Ação do botão */ },
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
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication,
        state = rememberWindowState(width = 1372.dp, height = 727.dp),
        ) {
        App()
    }
}
