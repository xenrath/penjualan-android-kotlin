package com.xenrath.penjualan.data.database

import android.content.Context
import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.booleanPref
import hu.autsoft.krate.stringPref

class PrefManager(context: Context): Krate {

    private val PREF_IS_LOGIN: String = "pref_is_login"
    private val PREF_USERNAME: String = "pref_username"
    private val PREF_PASSWORD: String = "pref_password"
    private val PREF_NAME: String = "pref_name"
    private val PREF_GENDER: String = "pref_gender"
    private val PREF_ADDRESS: String = "pref_address"
    private val PREF_IS_ACTIVE: String = "pref_is_active"

    override val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.applicationContext.getSharedPreferences(
            "penjualan", Context.MODE_PRIVATE
        )
    }

    var prefIsLogin by booleanPref(PREF_IS_LOGIN, false)
    var prefUsername by stringPref(PREF_USERNAME, "")
    var prefPassword by stringPref(PREF_PASSWORD, "")
    var prefName by stringPref(PREF_NAME, "")
    var prefGender by stringPref(PREF_GENDER, "")
    var prefAddress by stringPref(PREF_ADDRESS, "")
    var prefIsActive by stringPref(PREF_IS_ACTIVE, "")

    fun logout(){
        sharedPreferences.edit().clear().apply()
    }

}