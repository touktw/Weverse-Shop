package com.touktw.weverseshop.viewmodel

import android.app.Application
import com.touktw.weverseshop.base.viewmodel.DBViewModel

class LogoViewModel(application: Application) : DBViewModel(application) {
    val artistViewModel = ArtistViewModel(application)
    val localeViewModel = LocaleCurrencyViewModel(application)

    fun load() {
        artistViewModel.load()
        localeViewModel.load()
    }
}