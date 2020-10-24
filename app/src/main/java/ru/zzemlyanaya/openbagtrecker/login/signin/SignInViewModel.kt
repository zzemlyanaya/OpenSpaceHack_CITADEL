package ru.zzemlyanaya.openbagtrecker.login.signin

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import ru.zzemlyanaya.openbagtrecker.App
import ru.zzemlyanaya.openbagtrecker.R
import ru.zzemlyanaya.openbagtrecker.data.local.PrefsConst.PREF_KEEP_LOGGIN
import ru.zzemlyanaya.openbagtrecker.data.local.PrefsConst.PREF_USER_AUTH
import ru.zzemlyanaya.openbagtrecker.data.model.Resource
import ru.zzemlyanaya.openbagtrecker.data.model.User

class SignInViewModel : ViewModel() {

    private val _loginForm = MutableLiveData(SignInFormState())
    val signInFormState: LiveData<SignInFormState> = _loginForm

    fun authorize(id: Int, password: String, isKeepLogin: Boolean) = liveData(Dispatchers.IO) {
//        saveLogin(isKeepLogin, id, password.hashCode())
        emit(Resource.loading(data = null))
        if (id == "cita_del@citadel.ru".hashCode())
            emit(Resource.success(data =
                User(0, "cita_del@citadel.ru", "CITADEL", 0, "CIT AD EL",
                    "", 442, "Samsung S20 Note_Android 10|Google Pixel 3XL_Android 9.2")
            ))
        else
            emit(Resource.error(data = null, message = "Неверный email/пароль"))
//        try {
//            val result: Result<User> = repository.authorize(id, password.hashCode(), isKeepLogin)
//            if (result.error == null)
//                emit(Resource.success(data = result.data))
//            else
//                emit(Resource.error(data = null, message = result.error))
//        } catch (e: Exception) {
//            emit(Resource.error(data = null, message = e.message ?: "Ошибка сервера! Попробуйте снова."))
//        }
    }

    private fun saveLogin(isKeep: Boolean, id: Int, passHash: Int){
        App.prefs.setPref(PREF_KEEP_LOGGIN, isKeep)
        if (isKeep)
            App.prefs.setPref(PREF_USER_AUTH, "$id|$passHash")
    }

    fun loginDataChanged(email: String, password: String) {
        _loginForm.value =
            SignInFormState(usernameError = validateEmail(email),
                passwordError = validatePassword(password),
                isDataValid = isAllDataValid(email, password))
    }

    private fun isAllDataValid(email: String, password: String)
            = Patterns.EMAIL_ADDRESS.matcher(email).matches() and (password.length >= 8)

    private fun validateEmail(email: String) =
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) null else R.string.invalid_email


    private fun validatePassword(password: String) =
        if (password.length >= 8) null else R.string.invalid_password
}