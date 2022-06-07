package com.binar.studikasustujuh

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.binar.studikasustujuh.data.GetAllUserItem
import com.binar.studikasustujuh.datastore.UserManager
import com.binar.studikasustujuh.ui.theme.STUDIKASUSTUJUHTheme
import com.binar.studikasustujuh.viewmodel.ViewModelUser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class Login : ComponentActivity() {
    lateinit var dataUser : List<GetAllUserItem>
    lateinit var viewModel : ViewModelUser
    lateinit var email: String
    lateinit var password: String
    lateinit var toast : String
    var salah by Delegates.notNull<Boolean>()
    lateinit var userManager : UserManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            STUDIKASUSTUJUHTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")

                }
            }

        }






    }
}

@Composable
fun Greeting(name: String) {
    val  viewModelUser = viewModel(modelClass = ViewModelUser::class.java)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }



    val mcontext = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Login", Modifier.padding(30.dp))
        Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "icon", Modifier.padding(bottom = 20.dp) )
        TextField(value = email, onValueChange = { email = it }, Modifier.padding(10.dp),label = { Text("Masukan Email") } )
        TextField(value = password, onValueChange = { password = it },label = { Text(" Masukan Password") })
        Button(modifier = Modifier.padding(30.dp) ,onClick = {

            mcontext.startActivity(Intent(mcontext, MainActivity::class.java))

        }){
            Text(text = "Login", )
        }
        Row() {

            Text(text = "Belum Punya Akun? ")
            val text =  stringResource(R.string.daftar)
            ClickableText(
                text = AnnotatedString(text),
                onClick ={
                    mcontext.startActivity(Intent(mcontext, Register::class.java))
                }
            )

        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    STUDIKASUSTUJUHTheme {
        Greeting("Android")
    }
}



