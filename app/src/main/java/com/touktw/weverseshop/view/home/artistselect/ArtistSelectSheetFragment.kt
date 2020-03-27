package com.touktw.weverseshop.view.home.artistselect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.touktw.weverseshop.R
import com.touktw.weverseshop.model.ArtistDto
import com.touktw.weverseshop.view.wizard.ArtistRadioButtonContainer
import com.touktw.weverseshop.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.bottom_sheet_artist_select.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistSelectSheetFragment : BottomSheetDialogFragment() {
    private var homeViewModel: HomeViewModel? = null
    private var newSelectedArtist: ArtistDto? = null
    private val radioButtonMap = mutableMapOf<Int, RadioButton>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            homeViewModel = ViewModelProvider(it, ViewModelProvider.AndroidViewModelFactory.getInstance(it.application))
                    .get(HomeViewModel::class.java)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_sheet_artist_select, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRadio()
        buttonConfirm.setOnClickListener {
            newSelectedArtist?.let {
                if (it != homeViewModel?.selectedArtist?.value) {
                    CoroutineScope(Dispatchers.Main).launch {
                        val update = homeViewModel?.artistViewModel?.updateSelectedArtist(it) ?: 0
                        if (update > 0) {
                            dismissAllowingStateLoss()
                        }
                    }
                } else {
                    dismissAllowingStateLoss()
                }
            }
        }
    }

    private fun initRadio() {
        homeViewModel?.artistViewModel?.artists?.observe(viewLifecycleOwner, Observer { artists ->
            CoroutineScope(Dispatchers.Main).launch {
                radioGroup.removeAllViews()
                radioButtonMap.clear()
                artists?.groupBy { it.groupId }?.let { grouped ->
                    grouped.forEach { entry ->
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
                                    homeViewModel?.artistViewModel?.artists?.value?.find { buttonView.id == it.id }?.let {
                                        newSelectedArtist = it
                                    }
                                }
                                else -> entry.value.isChecked = false
                            }
                        }
                    }
                }

                homeViewModel?.selectedArtist?.value?.let { artist ->
                    radioButtonMap[artist.id]?.isChecked = true
                }
            }
        })
    }

    private fun inflateRadioButton(): ArtistRadioButtonContainer =
            ArtistRadioButtonContainer(LayoutInflater.from(context!!).inflate(R.layout.view_image_radio_button, radioGroup, false))
}