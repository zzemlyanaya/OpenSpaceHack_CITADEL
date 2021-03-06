package ru.zzemlyanaya.openbagtrecker.data.local

import com.kryptoprefs.context.KryptoContext
import com.kryptoprefs.preferences.KryptoPrefs

object PrefsConst {
    const val PREFS_NAME = "OpenSpacePrefs"
    const val PREF_KEEP_LOGGIN = "keep sign in"
    const val PREF_VERSION = "app version"
    const val PREF_USER_AUTH = "user auth"
}

class Prefs(prefs: KryptoPrefs): KryptoContext(prefs) {
    private val isKeepLoggin = boolean(PrefsConst.PREF_KEEP_LOGGIN, false)
    private val appVersion = string(PrefsConst.PREF_VERSION, "1.1.1", true)
    private val userAuth = string(PrefsConst.PREF_USER_AUTH, "1|1")

    fun setPref(key: String, value: Any){
        when(key){
            PrefsConst.PREF_KEEP_LOGGIN -> isKeepLoggin.put(value as Boolean)
            PrefsConst.PREF_VERSION -> appVersion.put(value as String)
            PrefsConst.PREF_USER_AUTH -> userAuth.put(value as String)
            else -> throw Exception("Unknown key!")
        }
    }

    fun getPref(key: String): Any = when(key) {
        PrefsConst.PREF_KEEP_LOGGIN -> isKeepLoggin.get()
        PrefsConst.PREF_VERSION -> appVersion.get()
        PrefsConst.PREF_USER_AUTH -> userAuth.get()
        else -> throw Exception("Unknown key!")
    }
}