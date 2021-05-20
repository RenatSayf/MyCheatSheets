package com.renatsayf.mycheatsheets.ui.screen1

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.view.animation.AnticipateOvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.renatsayf.mycheatsheets.R
import com.renatsayf.mycheatsheets.databinding.Screen1FragmentBinding
import com.renatsayf.mycheatsheets.ui.viewmodels.AnimViewModel
import com.renatsayf.mycheatsheets.ui.viewmodels.UiState

class Screen1Fragment : Fragment(R.layout.screen1_fragment) // TODO ViewBinding Step 2
{
    private lateinit var binding: Screen1FragmentBinding // TODO ViewBinding Step 3
    private lateinit var animVM: AnimViewModel

    companion object;

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

        //region TODO ViewPropertyAnimation.Scale Step 4
        animVM.uiState.observe(viewLifecycleOwner, { state ->
            when(state)
            {
                // установка начального масштаба
                is UiState.TextViewCreated ->
                {
                    binding.text2View.apply {
                        scaleX = state.scaleX
                        scaleY = state.scaleY
                    }
                }
                // установка масштаба после анимации
                is UiState.TextViewAfterAnim ->
                {
                    binding.text2View.apply {
                        scaleX = state.scaleX
                        scaleY = state.scaleY
                    }
                }
            }
        })
        //endregion

        //region TODO ViewPropertyAnimation.Scale Step 5 запуск анимации
        binding.text1View.setOnClickListener {
            binding.text2View.animate().scaleX(1f).scaleY(1f).apply {
                duration = 500
                interpolator = AnticipateOvershootInterpolator()
                startDelay = 0
                animScaleListener(this)
            }.start()
        }
        //endregion

    }

    // TODO ViewPropertyAnimation.Scale Step 6 слушатель анимации
    private fun animScaleListener(animator: ViewPropertyAnimator)
    {
        animator.setListener(object : Animator.AnimatorListener
        {
            override fun onAnimationStart(p0: Animator?)
            {

            }

            override fun onAnimationEnd(p0: Animator?)
            {
                // сохранение масштаба во viewmodel по завершении анимации
                val scaleX = binding.text2View.scaleX
                val scaleY = binding.text2View.scaleY
                animVM.setTextSize(UiState.TextViewAfterAnim(scaleX, scaleY))
            }

            override fun onAnimationCancel(p0: Animator?)
            {

            }

            override fun onAnimationRepeat(p0: Animator?)
            {

            }
        })
    }

}