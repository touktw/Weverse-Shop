package com.touktw.weverseshop.db

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.touktw.weverseshop.model.CurrencyDto
import com.touktw.weverseshop.model.LocaleDto

/**
 * Created by taekim on 2020-03-24
 */

object SharedPreferenceManager {
    private const val PREF_NAME = "WeversePreference"
    private const val KEY_IS_FINISH_WIZARD = "IS_FINISH_WIZARD"
    private const val KEY_SELECTED_ARTIST_ID = "SELECTED_ARTIST_ID"
    private const val KEY_SELECTED_LOCALE = "SELECTED_LOCALE"
    private const val KEY_SELECTED_CURRENCY = "SELECTED_CURRENCY"

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

    fun getSelectedLocale(context: Context): LocaleDto? {
        val localeString = getPreference(context).getString(KEY_SELECTED_LOCALE, "")
        return Gson().fromJson(localeString, LocaleDto::class.java)
    }

    fun setSelectedLocale(context: Context, locale: LocaleDto) {
        getPreference(context)
                .edit()
                .putString(KEY_SELECTED_LOCALE, Gson().toJson(locale))
                .apply()
    }

    fun getSelectedCurrency(context: Context): CurrencyDto {
        val currencyString = getPreference(context).getString(KEY_SELECTED_CURRENCY, "")
        return Gson().fromJson(currencyString, CurrencyDto::class.java)
    }

    fun setSelectedCurrency(context: Context, currency: CurrencyDto) {
        getPreference(context)
                .edit()
                .putString(KEY_SELECTED_CURRENCY, Gson().toJson(currency))
                .apply()
    }


    private fun getPreference(context: Context): SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
}