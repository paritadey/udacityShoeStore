package com.parita.shoestore.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.parita.shoestore.SharedPreference
import com.parita.shoestore.SharedPreference.clearValues
import com.parita.shoestore.SharedPreference.email
import com.parita.shoestore.SharedPreference.isLogin
import com.parita.shoestore.SharedPreference.password
import com.parita.shoestore.model.User

class LoginViewModel : ViewModel() {
    private lateinit var defaultPrefs: SharedPreferences
    private var userWelcomeData: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchUserDetails(context: Context, user: User): MutableLiveData<Boolean> {
        defaultPrefs = SharedPreference.defaultPreference(context)
        if (defaultPrefs.contains("email") && defaultPrefs.email!="no_data") {
            if (user.email.equals(defaultPrefs.email) && user.password.equals(defaultPrefs.password)) {
                Toast.makeText(context, "User is already logged in.", Toast.LENGTH_SHORT)
                    .show()
                userWelcomeData.value = true
                return userWelcomeData
            } else {
                defaultPrefs.clearValues
                defaultPrefs.email = user.email.trim()
                defaultPrefs.password = user.password.trim()
                defaultPrefs.isLogin = true
                userWelcomeData.value = true
                return userWelcomeData
            }
        } else {
            defaultPrefs.email = user.email.trim()
            defaultPrefs.password = user.password.trim()
            defaultPrefs.isLogin = true
            userWelcomeData.value = true
            return userWelcomeData
        }
    }

}
