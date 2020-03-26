package com.touktw.weverseshop.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.touktw.weverseshop.base.viewmodel.DBViewModel
import com.touktw.weverseshop.model.ArtistDto
import com.touktw.weverseshop.model.ProductDto
import com.touktw.weverseshop.model.PromotionDto
import com.touktw.weverseshop.net.WeverseShopService
import com.touktw.weverseshop.net.callback.ProductCallback
import com.touktw.weverseshop.net.callback.PromotionCallback
import com.touktw.weverseshop.net.response.ProductResponse
import com.touktw.weverseshop.net.response.PromotionResponse

/**
 * Created by taekim on 2020-03-26
 */


class HomeViewModel(application: Application) : DBViewModel(application) {
    val artistViewModel = ArtistViewModel(application)
    val selectedArtist = artistViewModel.selectedArtist

    private val _promotions = MutableLiveData<List<PromotionDto>>()
    val promotions: LiveData<List<PromotionDto>> = _promotions

    private val _products = MutableLiveData<List<ProductDto>>()
    val products: LiveData<List<ProductDto>> = _products

    val productsByCategory = Transformations.map(_products) { list ->
        list.groupBy { it.category }
    }

    private val artistObserver = Observer<ArtistDto> {
        loadPromotionInternal(it.id.toString())
        loadProductInternal(it.id.toString())
    }

    init {
        selectedArtist.observeForever(artistObserver)
    }

    override fun onCleared() {
        super.onCleared()
        selectedArtist.removeObserver(artistObserver)
    }

    private fun loadProductInternal(artistId: String) {
        WeverseShopService.get()
                .getProduct(artistId)
                .enqueue(object : ProductCallback() {
                    override fun onSuccess(response: ProductResponse) {
                        _products.value = response.data
                    }

                    override fun onFailed(code: Int, t: Throwable?) {
                        //TODO 에러
                    }

                })
    }

    private fun loadPromotionInternal(artistId: String) {
        WeverseShopService.get()
                .getPromotion(artistId)
                .enqueue(object : PromotionCallback() {
                    override fun onSuccess(response: PromotionResponse) {
                        _promotions.value = response.data
                    }

                    override fun onFailed(code: Int, t: Throwable?) {
                        //TODO 에러
                    }

                })
    }
}