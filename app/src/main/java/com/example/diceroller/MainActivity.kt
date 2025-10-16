package com.example.diceroller
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.diceroller.ui.theme.DiceRollerTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }

    @Composable
    fun DiceWithButtonAndImage(modifier: Modifier = Modifier)

     {
        // Estados de los dados
        var result1 by remember { mutableStateOf(1) }
         var result2 by remember { mutableStateOf(0) }
         var total by remember { mutableStateOf(result1 + result2) }

         // Recursos de imagen
        val imageResource1 = when (result1) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

         val imageResource2 = when (result2) {
             1 -> R.drawable.dice_1
             2 -> R.drawable.dice_2
             3 -> R.drawable.dice_3
             4 -> R.drawable.dice_4
             5 -> R.drawable.dice_5
             else -> R.drawable.dice_6
         }
        Column (
            modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Dos columnas (una por dado) dentro de una fila
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                // Primer dado + botón
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(imageResource1),
                contentDescription = result1.toString(),
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                result1 = (1..6).random()
                result2 = 0 // “ocultar” segundo dado
                total = result1
            }) {
                Text(stringResource(R.string.roll))
            }
            }
                // Segundo dado + botón
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if (result2 == 0) {
                        Spacer(modifier = Modifier.size(120.dp))
                    } else {
                        Image(
                            painter = painterResource(imageResource2),
                            contentDescription = result2.toString(),
                            modifier = Modifier.size(120.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        result1 = (1..6).random()
                        result2 = (1..6).random()
                        total = result1 + result2
                    }) {
                        Text(stringResource(R.string.roll))
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Puntuación: $total")
        }
    }



    @Preview
    @Composable
    fun DiceRollerApp() {
        DiceWithButtonAndImage()
    }
}

