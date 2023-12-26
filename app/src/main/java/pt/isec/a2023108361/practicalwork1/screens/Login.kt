import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.isec.a2023108361.practicalwork1.FirebaseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    viewModel : FirebaseViewModel,
    modifier : Modifier = Modifier,
    onSuccess : () -> Unit){

    val email = remember{ mutableStateOf("") }
    val password = remember{ mutableStateOf("") }
    val error by remember {viewModel.error}
    val user by remember {viewModel.user}

    LaunchedEffect(key1 = user){
        if(user != null && error == null){
            onSuccess()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(if (error != null) Color(255, 0, 0) else Color.Transparent),
        horizontalAlignment =  Alignment.CenterHorizontally
    ) {
        if(error != null){
            Text(text = "Error: $error", color = Color.White) // Set text color to white for better visibility
        }
        Spacer(Modifier.height(10.dp))
        Text(
            text = "LOG IN",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = email.value,
            onValueChange = {email.value = it},
            label = { Text(text = "Email") },
            //modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password.value,
            onValueChange = {password.value = it},
            label = { Text(text = "Password") },
            //modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.signIn(email.value, password.value)

            }
        ) {
            Text(text = "Sign in")
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.createUserWithEmail(email.value, password.value)
            }
        ) {
            Text(text = "Create account")
        }
    }

}