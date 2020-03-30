package com.touktw.weverseshop.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.touktw.weverseshop.base.viewmodel.DBViewModel
import com.touktw.weverseshop.model.ArtistDto
import com.touktw.weverseshop.model.CurrencyDto
import com.touktw.weverseshop.model.LocaleDto
import com.touktw.weverseshop.model.PreferenceDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WizardViewModel(application: Application) : DBViewModel(application) {
    private val localeViewModel = LocaleCurrencyViewModel(application)
    private val artistViewModel = ArtistViewModel(application)

    val locales: LiveData<List<LocaleDto>?> = localeViewModel.locales

    val currencies: LiveData<List<CurrencyDto>?> = localeViewModel.currencies

    val artists: LiveData<List<ArtistDto>?> = artistViewModel.artists

    var selectedLocale: LocaleDto? = null
    var selectedCurrency: CurrencyDto? = null
    var selectedArtist: ArtistDto? = null

    suspend fun savePreferences() = withContext(Dispatchers.Default) {
        if (selectedArtist != null && selectedCurrency != null && selectedLocale != null) {
            val preference = PreferenceDto(selectedArtist!!.id, selectedLocale!!.getLocale(), selectedCurrency!!.code)
            val result = withContext(Dispatchers.IO) {
                db.preferenceDao().insert(preference)
            }
            if (result > -1) {
                return@withContext true
            }
        }
        false
    }
}