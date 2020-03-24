package com.touktw.weverseshop.view.wizard

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.touktw.weverseshop.R
import com.touktw.weverseshop.base.BaseActivity
import kotlinx.android.synthetic.main.activity_wizard.*

/**
 * Created by taekim on 2020-03-24
 */

class WizardActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wizard)
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
                    finish()
                } else {
                    controller.navigate(next)
                }
            }
        }
    }
}