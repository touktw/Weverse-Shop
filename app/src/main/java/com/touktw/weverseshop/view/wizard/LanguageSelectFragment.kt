package com.touktw.weverseshop.view.wizard

import androidx.navigation.NavDirections
import com.touktw.weverseshop.R

/**
 * Created by taekim on 2020-03-24
 */


class LanguageSelectFragment : BaseWizardFragment() {
    override val layoutRes: Int = R.layout.fragment_language_select

    override fun getNextDirenction(): NavDirections? {
        return LanguageSelectFragmentDirections.actionToCurrencySelect()
    }
}