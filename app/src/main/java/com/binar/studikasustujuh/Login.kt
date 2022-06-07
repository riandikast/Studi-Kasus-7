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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.binar.studikasustujuh.datastore.UserManager
import com.binar.studikasustujuh.ui.theme.STUDIKASUSTUJUHTheme
import com.binar.studikasustujuh.viewmodel.ViewModelUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Login : ComponentActivity() {

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
    val mcontext = LocalContext.current
    val userViewModel = viewModel(modelClass = ViewModelUser::class.java)
    val dataUser by userViewModel.dataUserState.collectAsState()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val userManager = UserManager(mcontext)
    var salah : Boolean
    salah = false

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Login", Modifier.padding(30.dp))
        Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "icon", Modifier.padding(bottom = 20.dp) )
        TextField(value = username, onValueChange = { username = it }, Modifier.padding(10.dp),label = { Text("Masukan Username") } )
        TextField(value = password, onValueChange = { password = it },label = { Text(" Masukan Password") })
        Button(modifier = Modifier.padding(30.dp) ,onClick = {
            if (username.isNotEmpty() && password.isNotEmpty()) {
                for (i in dataUser.indices) {
                    if (username == dataUser[i].username && password == dataUser[i].password) {
                        scope.launch {
                            userManager.saveDataUser(
                                dataUser[i].id,
                                dataUser[i].name,
                                dataUser[i].password,
                                dataUser[i].username,
                                dataUser[i].umur.toString(),
                                dataUser[i].address,
                                dataUser[i].image,
                            )
                        }
                        salah = false
                        mcontext.startActivity(Intent(mcontext, MainActivity::class.java))
                        break
                    }else{
                        salah = true
                    }

                }
                if (salah){
                    android.widget.Toast.makeText(
                        mcontext,
                        "Username atau Password Salah",
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                }
            }



        }){
            Text(text = "Login")
        }
        Row {

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



