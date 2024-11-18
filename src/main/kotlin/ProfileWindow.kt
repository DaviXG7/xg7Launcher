import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import components.SelectMenu
import controlers.*
import java.awt.FileDialog
import java.awt.Frame
import javax.imageio.ImageIO

data class ProfileState(
    var name: String = "",
    var image: String = "assets/images/logo.png",
    var version: Version? = null,
    var javaVersion: JavaVersion? = null,
    var software: Software? = null
)




@Composable
fun show(screen: Screen, onClose: () -> Unit) {

    var profileState by remember { mutableStateOf(ProfileState()) }
    var imageBitmap by remember { mutableStateOf(ImageIO.read(getFile("assets\\images\\logo.png")).toPainter()) }
    var isWindowOpen by remember { mutableStateOf(true) }

    if (isWindowOpen) {
        Window(
            onCloseRequest = {
                isWindowOpen = false
                onClose()
            },
            state = rememberWindowState(width = 430.dp, height = 600.dp, position = WindowPosition.Aligned(Alignment.Center)),
            undecorated = true,
            title = "Formulário",
        ) {
            MaterialTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0, 12, 55))
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = imageBitmap,
                                contentDescription = "Download",
                                modifier = Modifier
                                    .height(135.dp)
                                    .width(135.dp)
                            )
                            Button(onClick = {
                                getFolder("imageUploads")
                                val fileName = openFileChooser()
                                profileState = profileState.copy(image = fileName ?: "Erro")
                                imageBitmap = ImageIO.read(getFile("imageUploads\\$fileName")).toPainter()
                            }) {
                                Text(if (profileState.image == "assets/images/logo.png") "Selecionar imagem" else profileState.image)
                            }
                        }
                        TextField(
                            onValueChange = { profileState = profileState.copy(name = it) },
                            value = profileState.name,
                            label = { Text("Nome:") },
                            modifier = Modifier.background(Color.White)
                        )

                        if (screen == Screen.PROFILE_SPIGOT) {
                            SelectMenu(
                                listOf(Software.PAPER.name, Software.SPIGOT.name),
                                0,
                                onOptionSelected = { selectedOp ->
                                    profileState.software = Software.valueOf(selectedOp)
                                },
                                Modifier.width(135.dp)
                            )
                        }

                        SelectMenu(
                            Version.entries.map { it.versionName }.toList(),
                            0,
                            onOptionSelected = { selectedOp ->
                                profileState.version = Version.valueOf(selectedOp)
                            },
                            Modifier.width(135.dp)
                        )
                        SelectMenu(
                            JavaVersion.entries.map { it.versionName }.toList(),
                            0,
                            onOptionSelected = { selectedOp ->
                                profileState.javaVersion = JavaVersion.valueOf(selectedOp)
                            },
                            Modifier.width(135.dp)
                        )

                        Row {
                            Button(
                                onClick = {
                                    isWindowOpen = false
                                    onClose()
                                }
                            ) {
                                Text(text = "Cancelar")
                            }
                            Button(onClick = {}) {
                                Text(text = "Criar")
                            }
                        }
                    }
                }
            }
        }
    }
}


fun openFileChooser(): String? {
    val dialog = FileDialog(Frame(), "Escolha um arquivo", FileDialog.LOAD)
    dialog.isVisible = true
    val path = dialog.files.firstOrNull()?.path
    val fileName = path?.split("\\")?.last()


    copyFile(path?: "assets\\images\\logo.png", "imageUploads\\$fileName")

    return fileName
}

fun main() = application {
    assets()
    show(Screen.PROFILE_SPIGOT, {})
}
