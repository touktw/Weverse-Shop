package com.touktw.weverseshop.view.wizard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
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


class ArtistSelectFragment : BaseWizardFragment() {
    private var wizardViewModel: WizardViewModel? = null

    override val layoutRes: Int = R.layout.fragment_preference_select

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            wizardViewModel =
                    ViewModelProvider(it, ViewModelProvider.AndroidViewModelFactory(it.application))
                            .get(WizardViewModel::class.java)
        }
    }

    override fun getNextDirenction(): NavDirections? {
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textTitle.text = "아티스트를 선택해 주세요."
        textDescription.text = "설정하신 아티스트 기준으로 Shop 화면을 구성합니다.\n설정 정보는 More 메뉴에서 언제든 수정할 수 있습니다."
        initRadio()
    }

    private fun updateNextButton() {
        val exists = wizardViewModel?.selectedArtist != null
        if (activity is WizardActivity) {
            (activity as WizardActivity).enableNextButton(exists)
        }
    }

    private val radioButtonMap = mutableMapOf<Int, RadioButton>()
    private fun initRadio() {
        wizardViewModel?.artists?.observe(viewLifecycleOwner, Observer { artists ->
            CoroutineScope(Dispatchers.Main).launch {
                radioGroup.removeAllViews()
                radioButtonMap.clear()
                artists.groupBy { it.groupId }.let { grouped ->
                    grouped.forEach { entry ->
                        val textView = inflateTextView()
                        textView.text = entry.value[0].group
                        radioGroup.addView(textView)
                        entry.value.forEachIndexed { index, artistDto ->
                            val container = inflateRadioButton()
                            container.containerView.id = artistDto.id
                            container.getRadioButton().id = artistDto.id
                            radioButtonMap[artistDto.id] = container.getRadioButton()
                            radioGroup.addView(container.containerView)
                            container.setData(artistDto)
                        }
                    }
                }

                radioButtonMap.forEach { entry ->
                    entry.value.setOnCheckedChangeListener { buttonView, isChecked ->
                        if (!isChecked) {
                            return@setOnCheckedChangeListener
                        }
                        radioButtonMap.forEach { entry ->
                            when (entry.key) {
                                buttonView.id -> {
                                    entry.value.isChecked = isChecked
                                    wizardViewModel?.artists?.value?.find { buttonView.id == it.id }?.let {
                                        wizardViewModel?.selectedArtist = it
                                        updateNextButton()
                                    }
                                }
                                else -> entry.value.isChecked = false
                            }
                        }
                    }
                }

                wizardViewModel?.selectedArtist?.let { artist ->
                    radioButtonMap[artist.id]?.isChecked = true
                }
            }
        })

        radioGroup.setOnClickListener {
            Log.d("###", "click, id:${it.id}")
        }

        updateNextButton()
    }

    private fun inflateRadioButton(): ArtistRadioButtonContainer =
            ArtistRadioButtonContainer(LayoutInflater.from(context!!).inflate(R.layout.view_image_radio_button, radioGroup, false))

    private fun inflateTextView(): TextView = TextView(context).apply {
        TextViewCompat.setTextAppearance(this, R.style.TextStyle_Normal)
        setTextColor(ContextCompat.getColor(context, R.color.grey))
    }
}