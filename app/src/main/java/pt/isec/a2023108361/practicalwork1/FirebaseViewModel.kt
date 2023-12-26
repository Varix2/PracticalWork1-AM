package pt.isec.a2023108361.practicalwork1

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import pt.isec.a2023108361.practicalwork1.util.Firebase.Auth

data class User(val name : String, val email : String, val picture : String?)

fun FirebaseUser.toUser() : User{
    val username = this.displayName ?: ""
    val str_email = this.email ?: ""
    val photoUrl = this.photoUrl.toString()
    return User(username,str_email,photoUrl)
}

class FirebaseViewModel : ViewModel() {

    private val _user = mutableStateOf(Auth.user?.toUser())
    val user: MutableState<User?>
        get() = _user

    private val _error = mutableStateOf<String?>(null)
    val error: MutableState<String?>
        get() = _error

    fun signIn(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            return
        }
        viewModelScope.launch {
            Auth.signInWithEmail(email, password) { exception ->
                if (exception == null) {
                    _user.value = Auth.user?.toUser()
                }
                //_error.value = exception?.message
            }
        }
    }

    fun createUserWithEmail(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            return
        }
        viewModelScope.launch {
            Auth.createUserWithEmail(email, password) { exception ->
                if (exception == null) {
                    _user.value = Auth.user?.toUser()
                }
                _error.value = exception?.message
            }
        }
    }

    fun signOut(){
        Auth.signOut()
        _user.value = null
        _error.value = null
    }
}