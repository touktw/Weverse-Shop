package com.touktw.weverseshop.view.wizard

import androidx.navigation.NavDirections
import com.touktw.weverseshop.BaseFragment

/**
 * Created by taekim on 2020-03-24
 */


abstract class BaseWizardFragment : BaseFragment() {
    abstract fun getNextDirections(): NavDirections?
}