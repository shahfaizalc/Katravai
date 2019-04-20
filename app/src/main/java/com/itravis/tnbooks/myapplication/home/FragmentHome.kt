package com.itravis.tnbooks.myapplication.home

import android.databinding.DataBindingUtil
import android.databinding.DataBindingUtil.inflate
import android.os.Bundle
import com.itravis.tnbooks.myapplication.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itravis.tnbooks.myapplication.setup.fragmentSetup.FragmentBase

class FragmentHome : FragmentBase() {

    private lateinit var areaViewModel: HomeViewModel;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return bindView(inflater, container)
    }

    private fun bindView(inflater: LayoutInflater, container: ViewGroup?): View {
        var binding: com.itravis.tnbooks.myapplication.databinding.ActivityAreaBinding = inflate(inflater, R.layout.fragment_home, container, false)
        areaViewModel = HomeViewModel(this.getContext()!!, this)
        binding.homeData = areaViewModel

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        areaViewModel.registerListeners()
    }

    override fun onStop() {
        super.onStop()
        areaViewModel.unRegisterListeners()
    }
}