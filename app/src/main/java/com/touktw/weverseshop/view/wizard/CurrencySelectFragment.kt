package com.touktw.weverseshop.view.wizard

import androidx.navigation.NavDirections
import com.touktw.weverseshop.BaseFragment
import com.touktw.weverseshop.R

/**
 * Created by taekim on 2020-03-24
 */

class CurrencySelectFragment : BaseWizardFragment() {
    override val layoutRes: Int = R.layout.fragment_currency_select

    override fun getNextDirenction(): NavDirections? {
        return CurrencySelectFragmentDirections.actionToArtistSelect()
    }
}