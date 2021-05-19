package com.renatsayf.mycheatsheets.ui.screen1

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.renatsayf.mycheatsheets.R
import com.renatsayf.mycheatsheets.databinding.Screen1FragmentBinding
import com.renatsayf.mycheatsheets.ui.viewmodels.AnimViewModel
import com.renatsayf.mycheatsheets.ui.viewmodels.UiState
import kotlinx.coroutines.flow.collect

class Screen1Fragment : Fragment(R.layout.screen1_fragment) // TODO ViewBinding Step 2
{
    private lateinit var binding: Screen1FragmentBinding // TODO ViewBinding Step 3
    private lateinit var animVM: AnimViewModel

    companion object
    {
        fun newInstance() = Screen1Fragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        animVM = ViewModelProvider(this)[AnimViewModel::class.java]
        return inflater.inflate(R.layout.screen1_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        binding = Screen1FragmentBinding.bind(view) // TODO ViewBinding Step 4

        val navController = requireActivity().findNavController(R.id.nav_host_fragment) // TODO Navigation Step 4

        binding.nextScreenBtn.setOnClickListener {
            navController.navigate(R.id.action_screen1Fragment_to_scree2Fragment) // TODO Navigation Step 5
        }

        lifecycleScope.launchWhenCreated {
            animVM.uiState.collect { state ->
                when(state)
                {
                    is UiState.Created ->
                    {
                        binding.text2View.apply {
                            width = state.width
                            height = state.height
                        }
                    }
                    is UiState.AfterAnim ->
                    {
                        binding.text2View.apply {
                            width = state.width
                            height = state.height
                        }
                    }
                }
            }
        }

    }

}