package com.renatsayf.mycheatsheets.ui.screen2

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.renatsayf.mycheatsheets.R

class Scree2Fragment : Fragment()
{

    companion object
    {
        fun newInstance() = Scree2Fragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        return inflater.inflate(R.layout.scree2_fragment, container, false)
    }

}