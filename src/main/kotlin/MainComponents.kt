import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun header(modifier: Modifier = Modifier) {

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
            painter = painterResource("images/logo.png"),
            contentDescription = "Logo"
        )

        Row(
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .offset(0.dp, 0.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            TextButton(
                onClick = { /* ação de navegação */ },
                modifier = Styles.HEADER_BUTTON.style
            ) {

                Row {
                    Image(
                        painter = painterResource("images/jogo.png"),
                        contentDescription = "Minecraft Icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(color = Color.White, text = "Perfis Minecraft")
                }
            }
            TextButton(
                onClick = { /* ação de navegação */ },
                modifier = Styles.HEADER_BUTTON.style
            ) {
                Row {
                    Image(
                        painter = painterResource("images/spigot.png"),
                        contentDescription = "Minecraft Icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(color = Color.White, text = "Perfis Spigot")
                }
            }
            TextButton(
                onClick = { /* ação de navegação */ },
                modifier = Styles.HEADER_BUTTON.style
            ) {
                Row {
                    Image(
                        painter = painterResource("images/db.png"),
                        contentDescription = "Minecraft Icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(color = Color.White, text = "Banco de dados")
                }
            }

        }
        Image(
            painter = painterResource("images/foto.png"),
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
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(Modifier.align(Alignment.CenterVertically)) {
            FloatingActionButton(
                onClick = { /* ação de navegação */ },
            ) {
                Icon(Newspaper, "Floating action button.")
            }
            Text("Logs")
        }



    }

}