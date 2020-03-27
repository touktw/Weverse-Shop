package com.touktw.weverseshop.view.logo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.touktw.weverseshop.R
import com.touktw.weverseshop.base.BaseActivity
import com.touktw.weverseshop.db.DatabaseManager
import com.touktw.weverseshop.view.home.HomeActivity
import com.touktw.weverseshop.view.wizard.WizardActivity
import com.touktw.weverseshop.viewmodel.LogoViewModel
import kotlinx.android.synthetic.main.activity_logo.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by taekim on 2020-03-24
 */


class LogoActivity : BaseActivity() {
    private var logoViewModel: LogoViewModel? = null
    private val readyToUse = MediatorLiveData<Boolean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)

        val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        logoViewModel = ViewModelProvider(this, factory).get(LogoViewModel::class.java)

        readyToUse.observe(this, Observer {
            if (it) {
                addRemoveSource(false)
                moveToNextActivity()
            }
        })

        addRemoveSource(true)
        logoViewModel?.load()
    }

    private fun addRemoveSource(add: Boolean) {
        logoViewModel?.artistViewModel?.artists?.let { liveData ->
            if (add) {
                readyToUse.addSource(liveData) {
                    readyToUse.value = checkDataLoaded()
                }
            } else {
                readyToUse.removeSource(liveData)
            }
        }
        logoViewModel?.localeViewModel?.locales?.let { liveData ->
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
        val isLoadedArtist = logoViewModel?.artistViewModel?.artists?.value?.isNotEmpty() == true
        val isLoadLocale = logoViewModel?.localeViewModel?.locales?.value?.isNotEmpty() == true
        return isLoadedArtist && isLoadLocale
    }

    private fun moveToNextActivity() {
        progressBar.visibility = View.GONE
        CoroutineScope(Dispatchers.Main).launch {
            if (DatabaseManager.get(application).preferenceDao().getPreferenceSync() != null) {
                startHomeActivity()
            } else {
                startWizardActivity()
            }
        }
    }

    private fun startHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun startWizardActivity() {
        startActivityForResult(Intent(this, WizardActivity::class.java), REQ_WIZARD)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQ_WIZARD -> {
                when (resultCode) {
                    Activity.RESULT_OK -> startHomeActivity()
                    else -> finish()
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        private const val REQ_WIZARD = 1000
    }
}