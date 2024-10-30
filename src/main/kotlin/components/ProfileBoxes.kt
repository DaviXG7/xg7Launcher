package components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
fun Profiles(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxHeight(0.63f)
            .fillMaxWidth(0.75f)
            .padding(16.dp)
    ) {
        FlowRow (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.LightGray)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

                for (i in 0..10) {
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .background(Color.Blue)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "item $i",
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
        }
    }
}
