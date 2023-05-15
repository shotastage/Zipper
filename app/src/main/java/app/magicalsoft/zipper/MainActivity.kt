package app.magicalsoft.zipper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.magicalsoft.zipper.ui.theme.ZipperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ZipperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text(text = "Zipper", fontSize = 40.sp, fontWeight = FontWeight.Bold)
                        MenuButton("Open File")
                    }
                }
            }
        }
    }
}

@Composable
fun MenuButton(name: String, modifier: Modifier = Modifier) {
    Button(onClick = { /*TODO*/ }) {
        Text(text = name)
    }
}

@Preview(showBackground = true)
@Composable
fun MenuButtonPreview() {
    ZipperTheme {
        MenuButton("Android")
    }
}
