package ru.zzemlyanaya.openbagtrecker.login.signin

data class SignInFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)