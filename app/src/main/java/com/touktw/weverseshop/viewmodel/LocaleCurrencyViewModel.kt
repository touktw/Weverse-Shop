package com.touktw.weverseshop.viewmodel

import android.app.Application
import com.touktw.weverseshop.base.viewmodel.DBViewModel
import com.touktw.weverseshop.net.WeverseShopService
import com.touktw.weverseshop.net.callback.LocaleCallback
import com.touktw.weverseshop.net.response.LocaleResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by taekim on 2020-03-24
 */


class LocaleCurrencyViewModel(application: Application) : DBViewModel(application) {
    val locales = db.localeDao().getAll()
    val currencies = db.currencyDao().getAll()

    fun load() {
        WeverseShopService.get().getLocales()
                .enqueue(object : LocaleCallback() {
                    override fun onSuccess(response: LocaleResponse) {
                        CoroutineScope(Dispatchers.IO).launch {
                            val locales = response.data
                            val currencies = locales.mapNotNull { it.currency }

                            db.localeDao().insert(*locales.toTypedArray())
                            db.currencyDao().insert(*currencies.toTypedArray())
                        }
                    }

                    override fun onFailed(code: Int, t: Throwable?) {
                        //TODO: 실패 처리는???
                    }
                })
    }
}