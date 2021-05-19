package com.renatsayf.mycheatsheets.ui.screen1

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.renatsayf.mycheatsheets.R
import com.renatsayf.mycheatsheets.databinding.Screen1FragmentBinding

class Screen1Fragment : Fragment(R.layout.screen1_fragment) // TODO ViewBinding Step 2
{
    private lateinit var binding: Screen1FragmentBinding // TODO ViewBinding Step 3

    companion object
    {
        fun newInstance() = Screen1Fragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        return inflater.inflate(R.layout.screen1_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        binding = Screen1FragmentBinding.bind(view) // TODO ViewBinding Step 4

    }

}