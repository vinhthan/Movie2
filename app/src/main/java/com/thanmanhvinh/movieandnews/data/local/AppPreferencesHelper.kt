package com.thanmanhvinh.movieandnews.data.local

import android.content.Context
import android.text.TextUtils
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.thanmanhvinh.movieandnews.di.Preferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferencesHelper @Inject constructor(
    context: Context,
    @Preferences prefFileName: String
) : PreferencesHelper {

    companion object {
        const val CURRENT_ACCESS_TOKEN = "CURRENT_ACCESS_TOKEN"
        const val USERNAME = "USERNAME"
        const val PASSWORD = "PASSWORD"
    }

    private var mPref = EncryptedSharedPreferences.create(
        prefFileName,
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override val accessToken: String
        get() = mPref.getString(CURRENT_ACCESS_TOKEN, "")!!
    override val username: String
        get() = mPref.getString(USERNAME, "")!!
    override val password: String
        get() = mPref.getString(PASSWORD, "")!!

    override fun savePassword(password: String) {
        mPref.edit {
            if (password == null) remove(PASSWORD) else putString(PASSWORD, password)
        }
    }

    override fun setAccessToken(token: String) {
        mPref.edit {
            if (token == null) remove(CURRENT_ACCESS_TOKEN) else putString(
                CURRENT_ACCESS_TOKEN,
                token
            )
        }
    }

    override fun checkLogin(): Boolean {
        val username = mPref.getString(USERNAME, "")
        val password = mPref.getString(PASSWORD, "")
        val token = mPref.getString(CURRENT_ACCESS_TOKEN, "")
        return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(token)
    }

    override fun setAccount(username: String?, password: String?, token: String?) {
        mPref.edit {
            if (token == null) remove(CURRENT_ACCESS_TOKEN) else putString(CURRENT_ACCESS_TOKEN, token)
            if (username == null) remove(username) else putString(USERNAME, username)
            if (password == null) remove(password) else putString(PASSWORD, password)
        }
    }

    override fun saveAccount(username: String, password: String, token: String) {
        val editor = mPref.edit()
        editor.putString(USERNAME, username)
        editor.putString(PASSWORD, password)
        editor.putString(CURRENT_ACCESS_TOKEN, token)//.apply()
        //editor.apply()
        editor.commit()

    }


}