package com.touktw.weverseshop.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.touktw.weverseshop.base.net.getResult
import com.touktw.weverseshop.base.viewmodel.DBViewModel
import com.touktw.weverseshop.model.ArtistDto
import com.touktw.weverseshop.net.WeverseShopService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by taekim on 2020-03-24
 */

class ArtistViewModel(application: Application) : DBViewModel(application) {
    val artists = db.artistDao().getAll()
    val selectedArtist: LiveData<ArtistDto?> = Transformations.switchMap(preference) {
        val preference = it ?: return@switchMap MutableLiveData<ArtistDto?>()
        db.artistDao().getById(preference.artistId)
    }

    suspend fun updateSelectedArtist(artistDto: ArtistDto): Int =
            withContext(Dispatchers.IO) {
                db.preferenceDao().updateArtistId(artistDto.id)
            }

    fun load() {
        CoroutineScope(Dispatchers.Main).launch {
            val artistResult = withContext(Dispatchers.Default) {
                WeverseShopService.get().getArtists().getResult()
            }

            withContext(Dispatchers.IO) {
                artistResult.data?.let { artists ->
                    db.artistDao().insert(*artists.toTypedArray())
                }
            }
        }
    }
}