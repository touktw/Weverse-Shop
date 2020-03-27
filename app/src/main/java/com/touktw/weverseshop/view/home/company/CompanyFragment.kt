package com.touktw.weverseshop.view.home.company

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.core.widget.TextViewCompat
import com.touktw.weverseshop.BaseFragment
import com.touktw.weverseshop.R
import kotlinx.android.synthetic.main.fragment_company.*


class CompanyFragment : BaseFragment() {
    override val layoutRes: Int
        get() = R.layout.fragment_company

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textCompanyInfoDetail.movementMethod = LinkMovementMethod.getInstance()
        textCompanyInfoDetail.text = HtmlCompat.fromHtml(getString(R.string.company_info), HtmlCompat.FROM_HTML_MODE_LEGACY)
        textTermsOfUse.movementMethod = LinkMovementMethod.getInstance()
        textTermsOfUse.text = HtmlCompat.fromHtml(getString(R.string.terms_of_use), HtmlCompat.FROM_HTML_MODE_LEGACY)

        TextViewCompat.setCompoundDrawableTintList(textCompanyInfo, ContextCompat.getColorStateList(context!!, R.color.grey))


        textCompanyInfo.setOnClickListener {
            val state = when (textCompanyInfoDetail.visibility) {
                View.VISIBLE -> View.GONE to R.drawable.ic_keyboard_arrow_up_black_24dp
                else -> View.VISIBLE to R.drawable.ic_expand_more_black_24dp
            }
            textCompanyInfoDetail.visibility = state.first
            textCompanyInfo.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    ContextCompat.getDrawable(context!!, state.second), null)
        }
    }
}