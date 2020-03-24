package com.touktw.weverseshop.db

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by taekim on 2020-03-24
 */

object SharedPreferenceManager {
    const val PREF_NAME = "WeversePreference"
    const val KEY_IS_FINISH_WIZARD = "IS_FINISH_WIZARD"
    const val KEY_SELECTED_ARTIST_ID = "SELECTED_ARTIST_ID"

    fun isWizardFinished(context: Context): Boolean =
        getPreference(context).getBoolean(KEY_IS_FINISH_WIZARD, false)

    fun setIsWizardFinished(context: Context, finished: Boolean) {
        getPreference(context)
            .edit()
            .putBoolean(KEY_IS_FINISH_WIZARD, finished)
            .apply()
    }

    fun getSelectedArtistId(context: Context): Int =
        getPreference(context).getInt(KEY_SELECTED_ARTIST_ID, 0)

    fun setSelectedArtistId(context: Context, artistId: Int) {
        getPreference(context)
            .edit()
            .putInt(KEY_SELECTED_ARTIST_ID, artistId)
            .apply()
    }


    private fun getPreference(context: Context): SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
}