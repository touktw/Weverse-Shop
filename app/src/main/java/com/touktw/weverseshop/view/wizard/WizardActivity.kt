package com.touktw.weverseshop.view.wizard

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.touktw.weverseshop.R
import com.touktw.weverseshop.base.BaseActivity
import com.touktw.weverseshop.viewmodel.WizardViewModel
import kotlinx.android.synthetic.main.activity_wizard.*

/**
 * Created by taekim on 2020-03-24
 */

class WizardActivity : BaseActivity() {
    var viewModel: WizardViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wizard)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
                .get(WizardViewModel::class.java)

        buttonBack.setOnClickListener { onBackPressed() }

        val controller = Navigation.findNavController(this, R.id.navHost)
        controller.addOnDestinationChangedListener { controller, destination, arguments ->
            arguments?.getInt("progress")?.let {
                progressStage.progress = it
                buttonBack.visibility = if (it == 1) View.GONE else View.VISIBLE
            }
        }
        buttonNext.setOnClickListener {
            val fragment = navHost.childFragmentManager.fragments.getOrNull(0)
            if (fragment is BaseWizardFragment) {
                val next = fragment.getNextDirenction()
                if (next == null) {
                    if (viewModel?.savePreferences() == true) {
                        setResult(Activity.RESULT_OK)
                    }
                    finish()
                } else {
                    controller.navigate(next)
                }
            }
        }
    }

    fun enableNextButton(enable: Boolean) {
        buttonNext.isEnabled = enable
    }
}