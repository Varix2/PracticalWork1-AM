import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import pt.isec.a2023108361.practicalwork1.FirebaseViewModel

@Composable
fun MainScreen(viewModel : FirebaseViewModel, modifier : Modifier = Modifier, onLogOut: () -> Unit){
    val user by remember {viewModel.user}
    LaunchedEffect(key1 = user){
        if(user == null ){
            onLogOut()
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment =  Alignment.CenterHorizontally
    ) {
        Text(text = "MAIN SCREEN")
        Button(
            onClick = {
                viewModel.signOut();
            }
        ) {
            Text(text = "LOG OUT")
        }
    }
}