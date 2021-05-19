package com.renatsayf.mycheatsheets.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimViewModel(app: Application) : AndroidViewModel(app)
{
    private var _uiState = MutableStateFlow(UiState.Created(0, 0))
    val uiState: StateFlow<UiState> = _uiState

    init
    {
        viewModelScope.launch {
            _uiState.emit(UiState.Created(0, 0))
        }
    }
}