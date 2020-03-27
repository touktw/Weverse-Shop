package com.touktw.weverseshop.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.touktw.weverseshop.base.net.getResult
import com.touktw.weverseshop.base.viewmodel.DBViewModel
import com.touktw.weverseshop.model.CurrencyDto
import com.touktw.weverseshop.model.NoticeDto
import com.touktw.weverseshop.model.ProductDto
import com.touktw.weverseshop.model.PromotionDto
import com.touktw.weverseshop.net.WeverseShopService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by taekim on 2020-03-26
 */


class HomeViewModel(application: Application) : DBViewModel(application) {
    val artistViewModel = ArtistViewModel(application)
    val selectedArtist = artistViewModel.selectedArtist
    val currency: LiveData<CurrencyDto?> = Transformations.switchMap(preference) {
        val preference = it ?: return@switchMap MutableLiveData<CurrencyDto?>()
        db.currencyDao().get(preference.currencyCode)
    }

    private val _promotions = MutableLiveData<List<PromotionDto>?>()
    val promotions: LiveData<List<PromotionDto>?> = _promotions

    private val _products = MutableLiveData<List<ProductDto>?>()
    val products: LiveData<List<ProductDto>?> = _products

    private val _notice = MutableLiveData<List<NoticeDto>?>()
    val notice: LiveData<List<NoticeDto>?> = _notice

    private val _recent = MutableLiveData<List<ProductDto>?>()
    val recent: LiveData<List<ProductDto>?> = _recent

    val productsByCategory = Transformations.map(_products) { list ->
        list?.groupBy { it.category }
    }

//    private val artistObserver = Observer<ArtistDto?> {
//        val artist = it ?: return@Observer
//        loadPromotionInternal(artist.id.toString())
//        loadProductInternal(artist.id.toString())
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val products = db.productDao().getByArtistIdSync(artist.id)
//            val recent = products?.filter { it.lastSelectTime != 0L }
//                    ?.sortedByDescending { it.lastSelectTime }?.chunked(RECENT_ITEM_SIZE)?.getOrNull(0)
//            withContext(Dispatchers.Main) {
//                _recent.value = recent
//            }
//        }
//        loadNotice()
//    }
//
//    init {
//        selectedArtist.observeForever(artistObserver)
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        selectedArtist.removeObserver(artistObserver)
//    }

//    private fun loadProductInternal(artistId: String) {
//        WeverseShopService.get()
//                .getProduct(artistId)
//                .enqueue(object : ProductCallback() {
//                    override fun onSuccess(response: ProductResponse) {
//                        _products.value = response.data
//                    }
//
//                    override fun onFailed(code: Int, t: Throwable?) {
//                        //TODO 에러
//                    }
//
//                })
//    }

//    private fun loadPromotionInternal(artistId: String) {
//        WeverseShopService.get()
//                .getPromotion(artistId)
//                .enqueue(object : PromotionCallback() {
//                    override fun onSuccess(response: PromotionResponse) {
//                        _promotions.value = response.data
//                    }
//
//                    override fun onFailed(code: Int, t: Throwable?) {
//                        //TODO 에러
//                    }
//                })
//    }
//
//    private fun loadNotice() {
//        WeverseShopService.get()
//                .getNotice()
//                .enqueue(object : NoticeCallback() {
//                    override fun onSuccess(response: NoticeResponse) {
//                        _notice.value = response.data
//                    }
//
//                    override fun onFailed(code: Int, t: Throwable?) {
//                        //TODO 에러
//                    }
//                })
//    }


    suspend fun refresh(): Boolean =
            withContext(Dispatchers.Default) {
                selectedArtist.value?.let {
                    val promotionResult = WeverseShopService.get().getPromotion(it.id.toString()).getResult()
                    val productResult = WeverseShopService.get().getProduct(it.id.toString()).getResult()
                    val noticeResult = WeverseShopService.get().getNotice().getResult()


                    val recentProducts = withContext(Dispatchers.IO) {
                        val recentProducts = db.productDao().getByArtistIdSync(it.id)
                        recentProducts?.filter { it.lastSelectTime != 0L }
                                ?.sortedByDescending { it.lastSelectTime }?.chunked(RECENT_ITEM_SIZE)?.getOrNull(0)
                    }

                    withContext(Dispatchers.Main) {
                        _products.value = productResult.data
                        _promotions.value = promotionResult.data
                        _notice.value = noticeResult.data
                        _recent.value = recentProducts
                    }
                }

                true
            }

    companion object {
        private const val RECENT_ITEM_SIZE = 5
    }
}