package com.renatsayf.mycheatsheets.ui.viewmodels

import android.app.ActionBar
import android.widget.LinearLayout
import android.widget.TextView

sealed class UiState
{
    data class Created(val width: Int, val height: Int) : UiState()
    data class AfterAnim(val width: Int , val height: Int) : UiState()
}