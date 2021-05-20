package com.abhat.howdoyouwantyoursoftware

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.abhat.howdoyouwantyoursoftware.databinding.ActivityMainBinding

/**
 * Created by Anirudh Uppunda on 20,May,2021
 */
class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        MainViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.viewStateData.observe(this, Observer { viewState ->
            when (viewState) {
                is MainViewModel.ViewState.NothingSelectedState -> {
                    nothingSelectedState()
                }

                is MainViewModel.ViewState.GoodSelectedState -> {
                    setView(viewState)
                }

                is MainViewModel.ViewState.CheapSelectedState -> {
                    setView(viewState)
                }

                is MainViewModel.ViewState.FastSelectedState -> {
                    setView(viewState)
                }
            }
        })
    }

    private fun setView(viewState: MainViewModel.ViewState) {
        binding.good.setOnCheckedChangeListener(null)
        binding.cheap.setOnCheckedChangeListener(null)
        binding.fast.setOnCheckedChangeListener(null)

        binding.good.isChecked = viewState.isGoodSelected
        binding.cheap.isChecked = viewState.isCheapSelected
        binding.fast.isChecked = viewState.isFastSelected

        binding.good.setOnCheckedChangeListener(this)
        binding.cheap.setOnCheckedChangeListener(this)
        binding.fast.setOnCheckedChangeListener(this)
        viewModel.setNothingSelectedStateIfApplicable()

    }

    private fun nothingSelectedState() {
        binding.good.setOnCheckedChangeListener(null)
        binding.good.isChecked = false
        binding.good.setOnCheckedChangeListener(this)

        binding.cheap.setOnCheckedChangeListener(null)
        binding.cheap.isChecked = false
        binding.cheap.setOnCheckedChangeListener(this)

        binding.fast.setOnCheckedChangeListener(null)
        binding.fast.isChecked = false
        binding.fast.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(compoundButton: CompoundButton?, checked: Boolean) {
        when (compoundButton?.id) {
            R.id.good -> {
                viewModel.onActionClicked(R.id.good, checked)
            }

            R.id.cheap -> {
                viewModel.onActionClicked(R.id.cheap, checked)
            }

            R.id.fast -> {
                viewModel.onActionClicked(R.id.fast, checked)
            }
        }
    }
}