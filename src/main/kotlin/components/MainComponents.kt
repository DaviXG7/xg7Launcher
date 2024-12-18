package components

import Newspaper
import Screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectMenu(options: List<String>, selectedOption: Int, onOptionSelected: (String) -> Unit, modifier: Modifier?) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selectedOption) }

    Box {
        // Botão para abrir o menu
        Button(modifier = modifier?: Modifier, onClick = { expanded = true }) {
            Text(options[selectedText])
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

@Composable
fun header(modifier: Modifier = Modifier, onPageChange: (Screen) -> Unit) {

    Row(
        modifier = modifier
            .fillMaxWidth(0.75f)
            .height(110.dp)
            .offset(0.dp, 10.dp)
            .background(Color(18, 28, 94, 247))
            .border(3.dp, Color(89, 222, 212, 247), shape = RoundedCornerShape(10.dp))
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            painter = painterResource("assets/images/logo.png"),
            contentDescription = "Logo",
            modifier = Modifier
                .clickable { onPageChange(Screen.MAIN) }
        )

        Row(
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .offset(0.dp, 0.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            TextButton(
                onClick = { onPageChange(Screen.PROFILE_MINECRAFT) },
                modifier = Modifier
                    .border(1.dp, Color.White, shape = RoundedCornerShape(10.dp))
                    .background(Color(0, 0, 0, 0))
            ) {

                Row {
                    Image(
                        painter = painterResource("assets/images/jogo.png"),
                        contentDescription = "Minecraft Icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(color = Color.White, text = "Perfis Minecraft")
                }
            }
            TextButton(
                onClick = { onPageChange(Screen.PROFILE_SPIGOT) },
                modifier = Modifier
                    .border(1.dp, Color.White, shape = RoundedCornerShape(10.dp))
                    .background(Color(0, 0, 0, 0))
            ) {
                Row {
                    Image(
                        painter = painterResource("assets/images/spigot.png"),
                        contentDescription = "Minecraft Icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(color = Color.White, text = "Perfis Spigot")
                }
            }
            TextButton(
                onClick = { /* ação de navegação */ },
                modifier = Modifier
                    .border(1.dp, Color.White, shape = RoundedCornerShape(10.dp))
                    .background(Color(0, 0, 0, 0))
            ) {
                Row {
                    Image(
                        painter = painterResource("assets/images/db.png"),
                        contentDescription = "Minecraft Icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(color = Color.White, text = "Banco de dados")
                }
            }

        }
        Image(
            painter = painterResource("assets/images/foto.png"),
            contentDescription = "Sua foto"
        )
    }

}

@Composable
fun footer(modifier: Modifier = Modifier) {

    Row(modifier = modifier
        .fillMaxWidth(0.35f)
        .height(110.dp)
        .offset(0.dp, (-10).dp)
        .background(Color(18, 28, 94, 247))
        .border(3.dp, Color(89, 222, 212, 247), shape = RoundedCornerShape(10.dp))
        .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            FloatingActionButton(
                modifier = Modifier.size(45.dp),
                onClick = { /* ação de navegação */ },
            ) {
                Icon(Newspaper, "Floating action button.")
            }
            Text("Logs Minecraft", color = Color.White,fontSize = 13.sp)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            FloatingActionButton(
                modifier = Modifier.size(55.dp),
                backgroundColor = Color(0,224,13,255),
                onClick = { /* ação de navegação */ },
            ) {
                Image(
                    painter = painterResource("assets/images/jogo.png"),
                    contentDescription = "Minecraft Icon",
                    modifier = Modifier.size(20.dp)
                )            }
            Text("Iniciar Minecraft", color = Color.White,fontSize = 14.sp)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            FloatingActionButton(
                modifier = Modifier.size(55.dp),
                backgroundColor = Color(0,224,13,255),
                onClick = { /* ação de navegação */ },
            ) {
                Icon(Icons.Default.PlayArrow, "Floating action button.", tint = Color.White)
            }
            Text("Iniciar Spigot", color = Color.White,fontSize = 14.sp)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            FloatingActionButton(
                modifier = Modifier.size(45.dp),
                onClick = { /* ação de navegação */ },
            ) {
                Icon(Newspaper, "Floating action button.")
            }
            Text("Logs Servidor", color = Color.White,fontSize = 13.sp)
        }



    }

}