package app.magicalsoft.zipper

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.core.view.WindowCompat
import java.io.FileDescriptor
import java.io.IOException


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MainContent()
        }
    }

    private fun openFile(resultData: Intent) {
        var pfDescriptor: ParcelFileDescriptor? = null

        try {
            val uri: Uri? = resultData.data
            // Uriを表示
            pfDescriptor = uri?.let { contentResolver.openFileDescriptor(it, "r") }
            if (pfDescriptor != null) {
                val fileDescriptor: FileDescriptor = pfDescriptor.fileDescriptor
                pfDescriptor.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                pfDescriptor?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

@Composable
fun MainContent() {
    ZipperTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Spacer(modifier = Modifier.safeDrawingPadding())
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(text = "Zipper", fontSize = 40.sp, fontWeight = FontWeight.Bold)
                    MenuButton("Open File")

                }
                Spacer(Modifier.weight(1f))
                BottomBar()
            }
        }
    }
}

@Composable
fun BottomBar() {
    BottomAppBar(
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Check, contentDescription = "Localized description")
            }
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "Localized description",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* do something */ },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        }
    )
}

@Composable
fun MenuButton(name: String, modifier: Modifier = Modifier) {
    Button(onClick = {
        println("HELLO")
    }) {
        Text(text = name)
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    MainContent()
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    ZipperTheme {
        BottomBar()
    }
}

@Preview(showBackground = true)
@Composable
fun MenuButtonPreview() {
    ZipperTheme {
        MenuButton("Android")
    }
}
