package uz.gita.lesson40.data.settings

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SettingsImpl @Inject constructor(@ApplicationContext context: Context,
) : Settings {
    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    override var temporaryToken: String?
        get() = preferences.getString("temp_token", null)
        set(value) = preferences.edit().putString("temp_token", value).apply()
    override var code: String?
        get() = preferences.getString("code", null)
        set(value) = preferences.edit().putString("code", value).apply()

    override var sigInToken: String?
        get() = preferences.getString("signIn", null)
        set(value) = preferences.edit().putString("signIn", value).apply()
    override var screenPassword: String?
        get() = preferences.getString("password",null)
        set(value) =preferences.edit().putString("password",value).apply()


    override var policy: Int
        get() = preferences.getInt("policy",-1)
        set(value) =preferences.edit().putInt("policy",value).apply()

    override var auth: Int
        get() = preferences.getInt("auth",-1)
        set(value) =preferences.edit().putInt("auth",value).apply()
    override var phone_number: String?
        get() = preferences.getString("phone", null)
        set(value) = preferences.edit().putString("phone", value).apply()
}