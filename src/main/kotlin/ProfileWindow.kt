import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
    var version: Version = Version.entries.first(),
    var javaVersion: JavaVersion = JavaVersion.entries.first(),
    var software: Software = Software.entries.first()
)




@Composable
fun show(screen: Screen, onClose: () -> Unit) {

    var profileState by remember { mutableStateOf(ProfileState()) }
    var imageBitmap by remember { mutableStateOf(ImageIO.read(getFile("assets\\images\\logo.png")).toPainter()) }
    var isWindowOpen by remember { mutableStateOf(true) }
    var error by remember {(mutableStateOf(""))}
    var ready by remember { mutableStateOf(false) }

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
                                profileState = profileState.copy(image = fileName)
                                imageBitmap = ImageIO.read(getFile("imageUploads\\$fileName")).toPainter()
                            }) {
                                Text(if (profileState.image == "assets/images/logo.png") "Selecionar imagem" else profileState.image)
                            }
                        }
                        TextField(
                            onValueChange = { profileState = profileState.copy(name = it) },
                            value = profileState.name,
                            label = { Text("Nome:") },
                            modifier = Modifier.fillMaxWidth(0.5f)
                                .background(Color.White)
                        )

                        if (screen == Screen.PROFILE_SPIGOT) {
                            Column(
                                modifier = Modifier.fillMaxWidth(0.5f)
                            ) {
                                Text(text = "Software do servidor", color = Color.White)
                                SelectMenu(
                                    listOf(Software.SPIGOT.name, Software.PAPER.name),
                                    0,
                                    onOptionSelected = { selectedOp ->
                                        profileState.software = Software.valueOf(selectedOp)
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    )
                            }
                        }

                        Column(
                            modifier = Modifier.fillMaxWidth(0.5f)
                        ) {
                            Text(text = "Versão do servidor", color = Color.White)
                            SelectMenu(
                                modifier = Modifier.fillMaxWidth(),
                                options = Version.entries.map { it.versionName }.toList(),
                                selectedOption = 0,
                                onOptionSelected = { selectedOp ->
                                    profileState.version = Version.getByName(selectedOp)
                                },
                            )
                        }
                        Column(
                            modifier = Modifier.fillMaxWidth(0.5f)
                        ) {
                            Text(text = "Versão do Java", color = Color.White)
                            SelectMenu(
                                modifier = Modifier.fillMaxWidth(),
                                options = JavaVersion.entries.map { it.versionName }.toList(),
                                selectedOption = 0,
                                onOptionSelected = { selectedOp ->
                                    profileState.javaVersion = JavaVersion.getByName(selectedOp)
                                },
                                )
                        }


                        Row(
                            modifier = Modifier.fillMaxWidth(0.5f),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                                onClick = {
                                    isWindowOpen = false
                                    onClose()
                                }
                            ) {
                                Text(text = "Cancelar")
                            }
                            Button(
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                                onClick = {
                                    if (profileState.name.isBlank()) {
                                        error = "Insira um nome"
                                        return@Button
                                    }

                                    ready = true
                                    isWindowOpen = false
                                    onClose()
                                }
                            ) {
                                Text(text = "Criar")
                            }
                        }
                        if (error.isNotBlank()) {
                            Text(text = error, color = Color.Red)
                        }
                    }
                }
            }
        }
    }
    if (ready) {
        createProfile(screen, profileState.name, profileState.image, profileState.javaVersion, profileState.version, profileState.software)
    }
}


fun openFileChooser(): String {
    val dialog = FileDialog(Frame(), "Escolha um arquivo", FileDialog.LOAD)
    dialog.isVisible = true
    if (dialog.files.isEmpty()) {
        copyFile(jarFolder.path + "\\assets\\images\\logo.png", "imageUploads\\logo.png")
        return "logo.png"
    }
    val path = dialog.files.first().path
    val fileName = path.split("\\").last()


    copyFile(path, "imageUploads\\$fileName")

    return fileName
}

fun main() = application {
    assets()
    show(Screen.PROFILE_SPIGOT, {})
}
