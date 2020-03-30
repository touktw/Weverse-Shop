package com.touktw.weverseshop.viewmodel

import android.app.Application
import com.touktw.weverseshop.base.net.getResult
import com.touktw.weverseshop.base.viewmodel.DBViewModel
import com.touktw.weverseshop.net.WeverseShopService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by taekim on 2020-03-24
 */


class LocaleCurrencyViewModel(application: Application) : DBViewModel(application) {
    val locales = db.localeDao().getAll()
    val currencies = db.currencyDao().getAll()

    fun load() {
        CoroutineScope(Dispatchers.Main).launch {
            val localeResult = withContext(Dispatchers.Default) {
                WeverseShopService.get().getLocales().getResult()
            }
            val currencyResult = withContext(Dispatchers.Default) {
                WeverseShopService.get().getCurrencies().getResult()
            }

            withContext(Dispatchers.IO) {
                localeResult.data?.let { locales ->
                    db.localeDao().insert(*locales.toTypedArray())
                }
                currencyResult.data?.let { currencies ->
                    db.currencyDao().insert(*currencies.toTypedArray())
                }
            }
        }
    }
}