package com.renatsayf.mycheatsheets.ui.viewmodels


// TODO ViewPropertyAnimation.Scale Step 2 - этот класс будет хранить состояние анимируемого объекта
sealed class UiState
{
    data class TextViewCreated(
        val scaleX: Float,
        val scaleY: Float) : UiState()

    data class TextViewAfterAnim(
        val scaleX: Float,
        val scaleY: Float) : UiState()
}