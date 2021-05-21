package com.renatsayf.mycheatsheets.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AnimViewModel(app: Application) : AndroidViewModel(app)
{
    //region // TODO ViewPropertyAnimation.Scale Step 3
    private var _uiState = MutableLiveData<UiState>(UiState.TextViewCreated(0f, 0f)) // установка начального масштаба TextView
    val uiState: LiveData<UiState> = _uiState

    fun setTextSize(state: UiState)
    {
        _uiState.value = state
    }
    //endregion

}