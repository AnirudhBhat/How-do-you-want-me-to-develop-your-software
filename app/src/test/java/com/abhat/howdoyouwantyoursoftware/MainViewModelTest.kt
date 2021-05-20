package com.abhat.howdoyouwantyoursoftware

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Anirudh Uppunda on 20,May,2021
 */

class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var observer: Observer<MainViewModel.ViewState>

    @Before
    fun setup() {
        observer = mock()
    }

    @Test
    fun `on app open nothing is selected`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)

        // When
        // viewModel started when initialized

        // Then
        Assert.assertTrue(viewState.value is MainViewModel.ViewState.NothingSelectedState)
    }

    @Test
    fun `given the there are NO options selected, on selecting GOOD should end up in proper state`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)

        // When
        viewModel.onActionClicked(R.id.good, isChecked = true)

        // Then
        Assert.assertTrue(viewState.value is MainViewModel.ViewState.GoodSelectedState)
    }

    @Test
    fun `given the there are already 2 options selected, on selecting GOOD should end up in proper state`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)

        // When
        viewModel.onActionClicked(R.id.cheap, isChecked = true)
        viewModel.onActionClicked(R.id.fast, isChecked = true)
        viewModel.onActionClicked(R.id.good, isChecked = true)

        // Then
        Assert.assertTrue(viewState.value is MainViewModel.ViewState.GoodSelectedState)
    }

    @Test
    fun `un-selecting GOOD should end up in proper state`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)

        // When
        viewModel.onActionClicked(R.id.good, isChecked = false)

        // Then
        Assert.assertTrue(viewState.value is MainViewModel.ViewState.GoodSelectedState)
    }

    @Test
    fun `un-selecting GOOD should end up in proper state with values`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)
        val expectedState = MainViewModel.ViewState.GoodSelectedState(
            isGoodSelected = false,
            isCheapSelected = false,
            isFastSelected = false
        )

        // When
        viewModel.onActionClicked(R.id.good, isChecked = false)

        // Then
        Assert.assertEquals(expectedState, viewState.value)
    }

    @Test
    fun `given 2 options selected, un-selecting GOOD should end up in proper state with values`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)
        val expectedState = MainViewModel.ViewState.GoodSelectedState(
            isGoodSelected = false,
            isCheapSelected = true,
            isFastSelected = false
        )

        // When
        viewModel.onActionClicked(R.id.cheap, isChecked = true)
        viewModel.onActionClicked(R.id.good, isChecked = true)
        viewModel.onActionClicked(R.id.good, isChecked = false)

        // Then
        Assert.assertEquals(expectedState, viewState.value)
    }

    @Test
    fun `given the there are NO options selected, on selecting GOOD should end up in proper state with proper values`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)
        val expectedState = MainViewModel.ViewState.GoodSelectedState(
            isGoodSelected = true,
            isCheapSelected = false,
            isFastSelected = false
        )

        // When
        viewModel.onActionClicked(R.id.good, isChecked = true)

        // Then
        Assert.assertEquals(expectedState, viewState.value)
    }

    @Test
    fun `given the there are 2 options selected, on selecting GOOD should end up in proper state with proper values`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)
        val expectedState = MainViewModel.ViewState.GoodSelectedState(
            isGoodSelected = true,
            isCheapSelected = true,
            isFastSelected = false
        )

        // When
        viewModel.onActionClicked(R.id.cheap, isChecked = true)
        viewModel.onActionClicked(R.id.fast, isChecked = true)
        viewModel.onActionClicked(R.id.good, isChecked = true)

        // Then
        Assert.assertEquals(expectedState, viewState.value)
    }




    @Test
    fun `given the there are NO options selected, on selecting CHEAP should end up in proper state`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)

        // When
        viewModel.onActionClicked(R.id.cheap, isChecked = true)

        // Then
        Assert.assertTrue(viewState.value is MainViewModel.ViewState.CheapSelectedState)
    }

    @Test
    fun `given the there are already 2 options selected, on selecting CHEAP should end up in proper state`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)

        // When
        viewModel.onActionClicked(R.id.fast, isChecked = true)
        viewModel.onActionClicked(R.id.good, isChecked = true)
        viewModel.onActionClicked(R.id.cheap, isChecked = true)

        // Then
        Assert.assertTrue(viewState.value is MainViewModel.ViewState.CheapSelectedState)
    }

    @Test
    fun `un-selecting CHEAP should end up in proper state`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)

        // When
        viewModel.onActionClicked(R.id.cheap, isChecked = false)

        // Then
        Assert.assertTrue(viewState.value is MainViewModel.ViewState.CheapSelectedState)
    }

    @Test
    fun `un-selecting CHEAP should end up in proper state with values`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)
        val expectedState = MainViewModel.ViewState.CheapSelectedState(
            isGoodSelected = false,
            isCheapSelected = false,
            isFastSelected = false
        )

        // When
        viewModel.onActionClicked(R.id.cheap, isChecked = false)

        // Then
        Assert.assertEquals(expectedState, viewState.value)
    }

    @Test
    fun `given 2 options selected, un-selecting CHEAP should end up in proper state with values`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)
        val expectedState = MainViewModel.ViewState.CheapSelectedState(
            isGoodSelected = true,
            isCheapSelected = false,
            isFastSelected = false
        )

        // When
        viewModel.onActionClicked(R.id.good, isChecked = true)
        viewModel.onActionClicked(R.id.cheap, isChecked = true)
        viewModel.onActionClicked(R.id.cheap, isChecked = false)

        // Then
        Assert.assertEquals(expectedState, viewState.value)
    }

    @Test
    fun `given the there are NO options selected, on selecting CHEAP should end up in proper state with proper values`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)
        val expectedState = MainViewModel.ViewState.CheapSelectedState(
            isGoodSelected = false,
            isCheapSelected = true,
            isFastSelected = false
        )

        // When
        viewModel.onActionClicked(R.id.cheap, isChecked = true)

        // Then
        Assert.assertEquals(expectedState, viewState.value)
    }

    @Test
    fun `given the there are 2 options selected, on selecting CHEAP should end up in proper state with proper values`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)
        val expectedState = MainViewModel.ViewState.CheapSelectedState(
            isGoodSelected = false,
            isCheapSelected = true,
            isFastSelected = true
        )

        // When
        viewModel.onActionClicked(R.id.good, isChecked = true)
        viewModel.onActionClicked(R.id.fast, isChecked = true)
        viewModel.onActionClicked(R.id.cheap, isChecked = true)

        // Then
        Assert.assertEquals(expectedState, viewState.value)
    }

    @Test
    fun `given the there are 1 options selected, on selecting CHEAP should end up in proper state with proper values`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)
        val expectedState = MainViewModel.ViewState.CheapSelectedState(
            isGoodSelected = true,
            isCheapSelected = true,
            isFastSelected = false
        )

        // When
        viewModel.onActionClicked(R.id.good, isChecked = true)
        viewModel.onActionClicked(R.id.cheap, isChecked = true)

        // Then
        Assert.assertEquals(expectedState, viewState.value)
    }





    @Test
    fun `given the there are NO options selected, on selecting FAST should end up in proper state`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)

        // When
        viewModel.onActionClicked(R.id.fast, isChecked = true)

        // Then
        Assert.assertTrue(viewState.value is MainViewModel.ViewState.FastSelectedState)
    }

    @Test
    fun `given the there are already 2 options selected, on selecting FAST should end up in proper state`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)

        // When
        viewModel.onActionClicked(R.id.good, isChecked = true)
        viewModel.onActionClicked(R.id.cheap, isChecked = true)
        viewModel.onActionClicked(R.id.fast, isChecked = true)

        // Then
        Assert.assertTrue(viewState.value is MainViewModel.ViewState.FastSelectedState)
    }

    @Test
    fun `un-selecting FAST should end up in proper state`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)

        // When
        viewModel.onActionClicked(R.id.fast, isChecked = false)

        // Then
        Assert.assertTrue(viewState.value is MainViewModel.ViewState.FastSelectedState)
    }

    @Test
    fun `un-selecting FAST should end up in proper state with values`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)
        val expectedState = MainViewModel.ViewState.FastSelectedState(
            isGoodSelected = false,
            isCheapSelected = false,
            isFastSelected = false
        )

        // When
        viewModel.onActionClicked(R.id.fast, isChecked = false)

        // Then
        Assert.assertEquals(expectedState, viewState.value)
    }

    @Test
    fun `given 2 options selected, un-selecting FAST should end up in proper state with values`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)
        val expectedState = MainViewModel.ViewState.FastSelectedState(
            isGoodSelected = false,
            isCheapSelected = true,
            isFastSelected = false
        )

        // When
        viewModel.onActionClicked(R.id.cheap, isChecked = true)
        viewModel.onActionClicked(R.id.fast, isChecked = true)
        viewModel.onActionClicked(R.id.fast, isChecked = false)

        // Then
        Assert.assertEquals(expectedState, viewState.value)
    }

    @Test
    fun `given the there are NO options selected, on selecting FAST should end up in proper state with proper values`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)
        val expectedState = MainViewModel.ViewState.FastSelectedState(
            isGoodSelected = false,
            isCheapSelected = false,
            isFastSelected = true
        )

        // When
        viewModel.onActionClicked(R.id.fast, isChecked = true)

        // Then
        Assert.assertEquals(expectedState, viewState.value)
    }

    @Test
    fun `given the there are 2 options selected, on selecting FAST should end up in proper state with proper values`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)
        val expectedState = MainViewModel.ViewState.FastSelectedState(
            isGoodSelected = true,
            isCheapSelected = false,
            isFastSelected = true
        )

        // When
        viewModel.onActionClicked(R.id.good, isChecked = true)
        viewModel.onActionClicked(R.id.cheap, isChecked = true)
        viewModel.onActionClicked(R.id.fast, isChecked = true)

        // Then
        Assert.assertEquals(expectedState, viewState.value)
    }

    @Test
    fun `given the there are 1 options selected, on selecting FAST should end up in proper state with proper values`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)
        val expectedState = MainViewModel.ViewState.FastSelectedState(
            isGoodSelected = false,
            isCheapSelected = true,
            isFastSelected = true
        )

        // When
        viewModel.onActionClicked(R.id.cheap, isChecked = true)
        viewModel.onActionClicked(R.id.fast, isChecked = true)

        // Then
        Assert.assertEquals(expectedState, viewState.value)
    }


    @Test
    fun `given 3 options are selected, on un-selecting all of them should end up in proper state`() {
        // Given
        val viewModel = MainViewModel()
        val viewState = viewModel.viewStateData
        viewState.observeForever(observer)
        val expectedState = MainViewModel.ViewState.NothingSelectedState()

        // When
        viewModel.onActionClicked(R.id.good, isChecked = false)
        viewModel.onActionClicked(R.id.cheap, isChecked = false)
        viewModel.onActionClicked(R.id.fast, isChecked = false)
        viewModel.setNothingSelectedStateIfApplicable()

        // Then
        Assert.assertEquals(expectedState, viewState.value)
    }
}