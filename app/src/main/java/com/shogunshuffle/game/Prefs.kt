package com.shogunshuffle.game

object Prefs {
    var level: Int
        get() = sharedPrefs.getInt("level", 1)
        set(value) = sharedPrefs.edit().putInt("level", value).apply()

    fun passLevel() = sharedPrefs.edit().putInt("level", level + 1).apply()

    private lateinit var sharedPrefs: android.content.SharedPreferences

    fun init(context: android.content.Context) {
        sharedPrefs = context.getSharedPreferences(context.packageName, android.content.Context.MODE_PRIVATE)
    }

    var musicVolume: Float
        get() = sharedPrefs.getFloat("musicVolume", 0.5f)
        set(value) = sharedPrefs.edit().putFloat("musicVolume", value).apply()
    var soundVolume: Float
        get() = sharedPrefs.getFloat("soundVolume", 0.5f)
        set(value) = sharedPrefs.edit().putFloat("soundVolume", value).apply()
}