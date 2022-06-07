package com.binar.studikasustujuh

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.binar.studikasustujuh.data.ResponseNewsItem
import com.binar.studikasustujuh.datastore.UserManager
import com.binar.studikasustujuh.ui.theme.STUDIKASUSTUJUHTheme
import com.binar.studikasustujuh.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            STUDIKASUSTUJUHTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val newsViewModel  : NewsViewModel = viewModel(modelClass = NewsViewModel::class.java)
                    val datanews by newsViewModel.dataState.collectAsState()
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row {
                            JudulNews("")

                        }

                        LazyColumn{
                            if (datanews.isEmpty()){
                                item {
                                }
                            }

                            items(datanews){

                                AdapterNews(news = it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun JudulNews(name:String) {
    val mcontext = LocalContext.current
    val userManager = UserManager(mcontext)
    val scope = rememberCoroutineScope()
    val username = userManager.userUsername.collectAsState(initial = "")
    val showUsername = username.value
    Row  {


        Column {
            Text(text = "News", Modifier.padding(30.dp), fontSize = 18.sp)
        }
        Text(text = "Hello $showUsername" , Modifier.padding(30.dp), fontSize = 18.sp)

        Column {

        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdapterNews(news : ResponseNewsItem) {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(15.dp)) {
        Card(shape = RoundedCornerShape(10.dp), modifier = Modifier
            .fillMaxWidth()
            .height(80.dp), onClick = {

            val intent = Intent(context, Detail::class.java)
            intent.putExtra("news", news)
            context.startActivity(intent)

        }){
            Row {
                Image(painter = rememberImagePainter(data = news.image), contentDescription = "")
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)) {
                    Text(text = news.title, color = Color.Black, fontWeight = FontWeight.Normal, modifier = Modifier.padding(start= 5.dp) )
                    Text(text = news.author, modifier = Modifier.padding(start = 5.dp))
                    Text(text = news.createdAt, modifier = Modifier.padding(start = 5.dp))
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    STUDIKASUSTUJUHTheme {
        JudulNews("Android")
    }
}