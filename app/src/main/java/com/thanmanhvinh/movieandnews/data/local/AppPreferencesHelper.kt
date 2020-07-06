package com.thanmanhvinh.movieandnews.data.local

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.thanmanhvinh.movieandnews.di.Preferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferencesHelper @Inject constructor(context: Context, @Preferences prefFileName: String) : PreferencesHelper{

    companion object{
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
            if (token == null) remove(CURRENT_ACCESS_TOKEN) else putString(CURRENT_ACCESS_TOKEN, token)
        }
    }
}