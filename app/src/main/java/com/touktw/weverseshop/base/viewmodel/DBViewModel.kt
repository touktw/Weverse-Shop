package com.touktw.weverseshop.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.touktw.weverseshop.db.DatabaseManager

/**
 * Created by taekim on 2020-03-24
 */


abstract class DBViewModel(application: Application) : AndroidViewModel(application) {
    val db = DatabaseManager.get(application)
}