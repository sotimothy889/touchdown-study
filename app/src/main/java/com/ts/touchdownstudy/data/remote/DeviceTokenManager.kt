package com.ts.touchdownstudy.data.remote

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceTokenManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences("touchdown_device_prefs", Context.MODE_PRIVATE)
    }

    fun getOrCreateDeviceToken(): String {
        var token = prefs.getString("device_token", null)
        if (token.isNullOrBlank()) {
            token = UUID.randomUUID().toString()
            prefs.edit().putString("device_token", token).apply()
        }
        return token
    }
}
