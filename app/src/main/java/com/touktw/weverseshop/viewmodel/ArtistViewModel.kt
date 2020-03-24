package com.touktw.weverseshop.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.touktw.weverseshop.base.viewmodel.DBViewModel
import com.touktw.weverseshop.db.SharedPreferenceManager
import com.touktw.weverseshop.db.dao.ArtistDao
import com.touktw.weverseshop.model.ArtistDto
import com.touktw.weverseshop.net.WeverseShopService
import com.touktw.weverseshop.net.callback.ArtistCallback
import com.touktw.weverseshop.net.response.ArtistResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by taekim on 2020-03-24
 */

class ArtistViewModel(application: Application) : DBViewModel(application) {
    val artists = db.artistDao().getAll()

    val selectedArtist =
        db.artistDao().getById(SharedPreferenceManager.getSelectedArtistId(application))

    fun load() {
        WeverseShopService.get().getArtists()
            .enqueue(object : ArtistCallback() {
                override fun onSuccess(response: ArtistResponse) {
                    Log.d("###", "onSuccess size:${response.data.size}")
                    CoroutineScope(Dispatchers.IO).launch {
                        db.artistDao().insert(*response.data.toTypedArray())
                    }
                }

                override fun onFailed(code: Int, t: Throwable?) {
                    //TODO: 실패 처리는???
                }
            })
    }
}