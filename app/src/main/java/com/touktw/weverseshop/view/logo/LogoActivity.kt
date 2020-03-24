package com.touktw.weverseshop.view.logo

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.touktw.weverseshop.R
import com.touktw.weverseshop.base.BaseActivity
import com.touktw.weverseshop.db.SharedPreferenceManager
import com.touktw.weverseshop.view.home.HomeActivity
import com.touktw.weverseshop.view.wizard.WizardActivity
import com.touktw.weverseshop.viewmodel.ArtistViewModel
import com.touktw.weverseshop.viewmodel.LocaleCurrencyViewModel

/**
 * Created by taekim on 2020-03-24
 */


class LogoActivity : BaseActivity() {
    private var artistViewModel: ArtistViewModel? = null
    private var localeViewModel: LocaleCurrencyViewModel? = null

    private val readyToUse = MediatorLiveData<Boolean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)
        val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        artistViewModel = ViewModelProvider(this, factory).get(ArtistViewModel::class.java)
        localeViewModel = ViewModelProvider(this, factory).get(LocaleCurrencyViewModel::class.java)

        readyToUse.observe(this, Observer {
            if (it) {
                addRemoveSource(false)
                moveToNextActivity()
            }
        })

        addRemoveSource(true)

        artistViewModel?.load()
        localeViewModel?.load()
    }

    private fun addRemoveSource(add: Boolean) {
        artistViewModel?.artists?.let { liveData ->
            if (add) {
                readyToUse.addSource(liveData) {
                    readyToUse.value = checkDataLoaded()
                }
            } else {
                readyToUse.removeSource(liveData)
            }
        }
        localeViewModel?.localeCurrencies?.let { liveData ->
            if (add) {
                readyToUse.addSource(liveData) {
                    readyToUse.value = checkDataLoaded()
                }
            } else {
                readyToUse.removeSource(liveData)
            }
        }
    }

    private fun checkDataLoaded(): Boolean {
        val isLoadedArtist = artistViewModel?.artists?.value?.isNotEmpty() == true
        val isLoadLocale = localeViewModel?.localeCurrencies?.value?.isNotEmpty() == true
        return isLoadedArtist && isLoadLocale
    }

    private fun moveToNextActivity() {
        if (SharedPreferenceManager.isWizardFinished(this)) {
            startHomeActivity()
        } else {
            startWizardActivity()
        }
    }

    private fun startHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun startWizardActivity() {
        startActivityForResult(Intent(this, WizardActivity::class.java), REQ_WIZARD)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQ_WIZARD -> {

            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        private const val REQ_WIZARD = 1000
    }
}