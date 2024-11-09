import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import components.SelectMenu
import controlers.JavaVersion
import controlers.Software
import controlers.Version

data class ProfileState(
    var name: String = "",
    var image: String = "",
    var version: Version? = null,
    var javaVersion: JavaVersion? = null,
    var software: Software? = null
)


@Composable
fun show(screen: Screen, onClose: () ->  Unit) {

    var profileState by remember { mutableStateOf(ProfileState()) }

    var test by remember { mutableStateOf("a") }

    Window(
        onCloseRequest = { onClose() },
        state = rememberWindowState(width = 430.dp, height = 600.dp),
        title = "Formulário",
    ) {
        MaterialTheme {

            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color(0, 12, 55,210))) {

                TextField(
                    onValueChange = {profileState = profileState.copy(name = it)},
                    value = profileState.name,
                    label = { Text("Nome:")},

                )

                if (screen == Screen.PROFILE_SPIGOT) {

                    SelectMenu(
                        listOf(Software.PAPER.name, Software.SPIGOT.name),
                        0,
                        onOptionSelected = {selectedOp ->
                            profileState.software = Software.valueOf(selectedOp)
                        }
                    )

                }

                SelectMenu(
                    Version.entries.map { it.name }.toList(),
                    0,
                    onOptionSelected = {selectedOp ->
                        profileState.version = Version.valueOf(selectedOp)
                    }
                )
                SelectMenu(
                    JavaVersion.entries.map { it.name }.toList(),
                    0,
                    onOptionSelected = {selectedOp ->
                        profileState.javaVersion = JavaVersion.valueOf(selectedOp)
                    }
                )


            }

        }
    }

}
