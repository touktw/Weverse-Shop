package com.touktw.weverseshop.viewmodel

import android.app.Application
import com.touktw.weverseshop.base.viewmodel.DBViewModel

/**
 * Created by taekim on 2020-03-26
 */


class HomeViewModel(application: Application) : DBViewModel(application) {
    val artistViewModel = ArtistViewModel(application)
    val selectedArtist = artistViewModel.selectedArtist
}