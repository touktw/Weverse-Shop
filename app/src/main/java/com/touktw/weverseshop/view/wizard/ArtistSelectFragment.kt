package com.touktw.weverseshop.view.wizard

import androidx.navigation.NavDirections
import com.touktw.weverseshop.R

/**
 * Created by taekim on 2020-03-24
 */


class ArtistSelectFragment : BaseWizardFragment() {
    override fun getNextDirenction(): NavDirections? {
        return null
    }

    override val layoutRes: Int = R.layout.fragment_artist_select
}