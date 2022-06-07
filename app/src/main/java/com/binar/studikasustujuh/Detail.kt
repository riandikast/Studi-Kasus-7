package com.binar.studikasustujuh

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.binar.studikasustujuh.data.ResponseNewsItem
import com.binar.studikasustujuh.ui.theme.STUDIKASUSTUJUHTheme

class Detail : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            STUDIKASUSTUJUHTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val getnews =  intent?.getParcelableExtra<ResponseNewsItem>("news")
                    if (getnews != null) {
                        DetailNews(getnews)
                    }
                    Toast()
                }
            }
        }
    }
}

@Composable
fun DetailNews(getnews : ResponseNewsItem) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(text = "Detail", Modifier.padding(30.dp), fontSize = 24.sp)
        Text(text = getnews.title, Modifier.padding(10.dp), fontSize = 24.sp)
        Image(painter = rememberImagePainter(data = getnews.image), contentDescription = "icon", Modifier.padding(bottom = 20.dp).fillMaxSize().height(200.dp).width(100.dp))
        Text(text = getnews.description, Modifier.padding(30.dp), fontSize = 20.sp)

    }
}

@Composable
fun Toast() {
    val context = LocalContext.current
    Toast.makeText(
        context,
        "Detail News",
        Toast.LENGTH_SHORT
    ).show()

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    STUDIKASUSTUJUHTheme {

    }
}