package com.abhat.howdoyouwantyoursoftware

import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Anirudh Uppunda on 20,May,2021
 */
class MainViewModel: ViewModel() {

    private val viewState = MutableLiveData<ViewState>()
    val viewStateData: LiveData<ViewState> = viewState

    init {
        viewState.value = ViewState.NothingSelectedState()
    }

    fun onActionClicked(@IdRes id: Int, isChecked: Boolean) {
        when (id) {
            R.id.good -> onGoodSelected(isChecked)
            R.id.cheap -> onCheapSelected(isChecked)
            R.id.fast -> onFastSelected(isChecked)
        }
    }

    fun setNothingSelectedStateIfApplicable() {
        if (viewState.value?.isFastSelected == false
            && viewState.value?.isGoodSelected == false
            && viewState.value?.isCheapSelected == false) {
            viewState.value = ViewState.NothingSelectedState()
        }
    }

    private fun onGoodSelected(isChecked: Boolean) {
        if (viewState.value?.isFastSelected == true
            && viewState.value?.isCheapSelected == true) {
            viewState.value = ViewState.GoodSelectedState()
        } else if (isChecked) {
            viewState.value = ViewState.GoodSelectedState(
                isGoodSelected = true,
                isCheapSelected = viewState.value?.isCheapSelected ?: false,
                isFastSelected = viewState.value?.isFastSelected ?: false
            )
        } else {
            viewState.value = ViewState.GoodSelectedState(
                isGoodSelected = false,
                isCheapSelected = viewState.value?.isCheapSelected ?: false,
                isFastSelected = viewState.value?.isFastSelected ?: false
            )
        }
    }

    private fun onCheapSelected(isChecked: Boolean) {
        if (viewState.value?.isFastSelected == true
            && viewState.value?.isGoodSelected == true) {
            viewState.value = ViewState.CheapSelectedState()
        } else if (isChecked) {
            viewState.value = ViewState.CheapSelectedState(
                isGoodSelected = viewState.value?.isGoodSelected ?: false,
                isCheapSelected = true,
                isFastSelected = viewState.value?.isFastSelected ?: false
            )
        } else {
            viewState.value = ViewState.CheapSelectedState(
                isGoodSelected = viewState.value?.isGoodSelected ?: false,
                isCheapSelected = false,
                isFastSelected = viewState.value?.isFastSelected ?: false
            )
        }
    }

    private fun onFastSelected(isChecked: Boolean) {
        if (viewState.value?.isGoodSelected == true
            && viewState.value?.isCheapSelected == true) {
            viewState.value = ViewState.FastSelectedState()
        } else if (isChecked) {
            viewState.value = ViewState.FastSelectedState(
                isGoodSelected = viewState.value?.isGoodSelected ?: false,
                isCheapSelected = viewState.value?.isCheapSelected ?: false,
                isFastSelected = true
            )
        } else {
            viewState.value = ViewState.FastSelectedState(
                isGoodSelected = viewState.value?.isGoodSelected ?: false,
                isCheapSelected = viewState.value?.isCheapSelected ?: false,
                isFastSelected = false
            )
        }
    }
    sealed class ViewState(
        open val isGoodSelected: Boolean = false,
        open val isCheapSelected: Boolean = false,
        open val isFastSelected: Boolean = false
    ) {

        data class NothingSelectedState(
            override val isGoodSelected: Boolean = false,
            override val isCheapSelected: Boolean = false,
            override val isFastSelected: Boolean = false
        ): ViewState()

        data class GoodSelectedState(
            override val isGoodSelected: Boolean = true,
            override val isCheapSelected: Boolean = true,
            override val isFastSelected: Boolean = false
        ): ViewState()

        data class CheapSelectedState(
            override val isGoodSelected: Boolean = false,
            override val isCheapSelected: Boolean = true,
            override val isFastSelected: Boolean = true
        ): ViewState()

        data class FastSelectedState(
            override val isGoodSelected: Boolean = true,
            override val isCheapSelected: Boolean = false,
            override val isFastSelected: Boolean = true
        ): ViewState()
    }
}