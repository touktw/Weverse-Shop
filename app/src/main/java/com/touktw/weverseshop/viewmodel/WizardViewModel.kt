package com.touktw.weverseshop.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.touktw.weverseshop.base.viewmodel.DBViewModel
import com.touktw.weverseshop.db.SharedPreferenceManager
import com.touktw.weverseshop.model.ArtistDto
import com.touktw.weverseshop.model.CurrencyDto
import com.touktw.weverseshop.model.LocaleDto

class WizardViewModel(application: Application) : DBViewModel(application) {
    private val localeViewModel = LocaleCurrencyViewModel(application)
    private val artistViewModel = ArtistViewModel(application)

    val locales: LiveData<List<LocaleDto>> = localeViewModel.locales

    val currencies: LiveData<List<CurrencyDto>> = localeViewModel.currencies

    val artists: LiveData<List<ArtistDto>> = artistViewModel.artists

    var selectedLocale: LocaleDto? = null
    var selectedCurrency: CurrencyDto? = null
    var selectedArtist: ArtistDto? = null

    fun savePreferences(): Boolean {
        return selectedArtist?.let {
            SharedPreferenceManager.setSelectedArtistId(getApplication(), it.id)
            selectedLocale
        }?.let {
            SharedPreferenceManager.setSelectedLocale(getApplication(), it)
            selectedCurrency
        }?.let {
            SharedPreferenceManager.setSelectedCurrency(getApplication(), it)
            SharedPreferenceManager.setIsWizardFinished(getApplication(), true)
            true
        } ?: false
    }
}