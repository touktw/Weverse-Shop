package com.touktw.weverseshop.view.wizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import com.touktw.weverseshop.R
import com.touktw.weverseshop.viewmodel.WizardViewModel
import kotlinx.android.synthetic.main.fragment_preference_select.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by taekim on 2020-03-24
 */

class CurrencySelectFragment : BaseWizardFragment() {
    override val layoutRes: Int = R.layout.fragment_preference_select
    private var wizardViewModel: WizardViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            wizardViewModel =
                    ViewModelProvider(it, ViewModelProvider.AndroidViewModelFactory(it.application))
                            .get(WizardViewModel::class.java)
        }
    }

    override fun getNextDirections(): NavDirections? {
        return CurrencySelectFragmentDirections.actionToArtistSelect()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textTitle.text = "통화를 선택해 주세요."
        textDescription.text = "사용하실 통화 선택해 주세요.\n선택한 통화는 More 메뉴에서 언제든 수정할 수 있습니다."
        initRadio()
    }

    private fun updateNextButton() {
        val exists = wizardViewModel?.selectedCurrency != null
        if (activity is WizardActivity) {
            (activity as WizardActivity).enableNextButton(exists)
        }
    }

    private fun initRadio() {
        wizardViewModel?.currencies?.observe(viewLifecycleOwner, Observer { currencies ->
            CoroutineScope(Dispatchers.Main).launch {
                radioGroup.removeAllViews()
                currencies?.forEachIndexed { index, currency ->
                    val button = inflateRadioButton()
                    button.id = index
                    button.text = "${currency.symbol}(${currency.code}) - ${currency.name}"
                    radioGroup.addView(button)
                }
                wizardViewModel?.selectedCurrency?.let { currency ->
                    wizardViewModel?.currencies?.value?.indexOf(currency)?.let {
                        radioGroup.check(it)
                    }
                }
            }
        })

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            wizardViewModel?.currencies?.value?.get(checkedId)?.let {
                wizardViewModel?.selectedCurrency = it
                updateNextButton()
            }
        }
        updateNextButton()
    }

    private fun inflateRadioButton(): RadioButton =
            LayoutInflater.from(context!!).inflate(R.layout.view_common_radio_button, radioGroup, false) as RadioButton
}