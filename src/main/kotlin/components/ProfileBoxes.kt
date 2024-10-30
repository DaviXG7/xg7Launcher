package components

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import controlers.Profile
import java.util.Arrays

@Composable
fun MinecraftBootProfile(modifier: Modifier = Modifier) {

    var expanded by remember { mutableStateOf(false) }

    Column (
        modifier = modifier
            .width(310.dp)
            .fillMaxHeight(0.63f)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(24, 71, 99, 247), Color(1, 8, 78, 247))
                )
            )
            .border(1.dp, Color(89, 222, 212, 247), shape = RoundedCornerShape(10.dp))
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource("images/caixa.png"),
            contentDescription = "Profile",
            Modifier.size(115.dp)
        )

        Text("Selecione o perfil do Minecraft", color = Color.White)

        SelectMenu(Arrays.asList("Perfil 1", "Perfil 2", "Perfil 3"), 0) { selectedOption ->
            println("Perfil selecionado: $selectedOption")
        }

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
            modifier = Modifier.size(180.dp, 35.dp)
        ) {
            Text("Iniciar")
        }
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
            modifier = Modifier.size(180.dp, 35.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = "Reiniciar"
            )
            Text("Reiniciar")
        }
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = Modifier.size(180.dp, 35.dp)
        ) {
            Text("Ir à pasta")
        }

        Text("RAM: 2GB", color = Color.White)

    }
}
@Composable
fun ServerBootProfile(modifier: Modifier = Modifier) {

    var expanded by remember { mutableStateOf(false) }

    Column (
        modifier = modifier
            .width(310.dp)
            .fillMaxHeight(0.63f)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(24, 71, 99, 247), Color(1, 8, 78, 247))
                )
            )
            .border(1.dp, Color(89, 222, 212, 247), shape = RoundedCornerShape(10.dp))
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource("images/caixa.png"),
            contentDescription = "Profile",
            Modifier.size(115.dp)
        )

        Text("Selecione o perfil Spigot", color = Color.White)

        SelectMenu(Arrays.asList("Perfil 1", "Perfil 2", "Perfil 3"), 0) { selectedOption ->
            println("Perfil selecionado: $selectedOption")
        }

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
            modifier = Modifier.size(180.dp, 35.dp)
        ) {
            Text("Iniciar")
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.size(180.dp, 35.dp)
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
                modifier = Modifier.size(85.dp, 35.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Reiniciar"
                )
            }
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                modifier = Modifier.size(85.dp, 35.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Reiniciar"
                )
                Text("/rl")

            }
        }

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = Modifier.size(180.dp, 35.dp)
        ) {
            Text("Ir à pasta")
        }


        Text("Players: 0 RAM: 2GB", color = Color.White)

    }
}

@Composable
fun SelectMenu(options: List<String>, selectedOption: Int, onOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selectedOption) }

    Box {
        // Botão para abrir o menu
        Button(onClick = { expanded = true }) {
            Text(options[selectedOption])
        }

        // DropdownMenu que mostra as opções
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selectedText = options.indexOf(option)
                        onOptionSelected(option)
                        expanded = false
                    }
                ) {
                    Text(option)
                }
            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Profiles(modifier: Modifier, profileType: Profile.Type) {

    val stateVertical = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxHeight(0.63f)
            .fillMaxWidth(0.75f)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .border(1.dp, Color.White, RoundedCornerShape(10.dp))
                .verticalScroll(stateVertical)
        ) {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (i in 0..35) {
                    Column(
                        modifier = Modifier
                            .width(150.dp)
                            .height(190.dp)
                            .padding(8.dp)
                            .border(1.dp, Color(89, 222, 212, 247), shape = RoundedCornerShape(10.dp))
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(24, 71, 99, 247), Color(1, 8, 78, 247))
                                )
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Image(
                            painter = painterResource("images/caixa.png"),
                            contentDescription = "Profile",
                            Modifier.size(80.dp)
                        )
                        Text(
                            text = "$profileType item",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                        Row(
                            modifier = Modifier.fillMaxSize(0.7f),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            FloatingActionButton(
                                modifier = Modifier.size(45.dp),
                                backgroundColor = Color(0,224,13,255),
                                onClick = { /* ação de navegação */ },
                            ) {
                                Icon(Icons.Default.PlayArrow, "Floating action button.", tint = Color.White)
                            }
                            FloatingActionButton(
                                modifier = Modifier.size(45.dp),
                                backgroundColor = Color.LightGray,
                                onClick = { /* ação de navegação */ },
                            ) {
                                Icon(Icons.Default.Add, "Floating action button.", tint = Color.Black)
                            }
                        }
                    }
                }
            }
        }
    }

}
