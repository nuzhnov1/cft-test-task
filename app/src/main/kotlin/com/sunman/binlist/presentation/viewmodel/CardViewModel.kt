package com.sunman.binlist.presentation.viewmodel

import androidx.lifecycle.*
import com.sunman.binlist.domain.model.Card
import com.sunman.binlist.domain.usecase.GetCardByBinUseCase
import com.sunman.binlist.domain.usecase.GetSavedCardsUseCase
import com.sunman.binlist.domain.usecase.SaveCurrentCardUseCase
import com.sunman.binlist.domain.usecase.RemoveAllCardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val getCardByBinUseCase: GetCardByBinUseCase,
    private val removeAllCardsUseCase: RemoveAllCardsUseCase,
    private val saveCurrentCardUseCase: SaveCurrentCardUseCase,
    getSavedCardsUseCase: GetSavedCardsUseCase
) : ViewModel() {

    private val _currentCard = MutableLiveData<Result<Card?>>()
    val currentCard: LiveData<Result<Card?>> = _currentCard

    val savedCards = getSavedCardsUseCase().asLiveData()


    fun getCardByBin(bin: String) = viewModelScope.launch {
        val resultCard = getCardByBinUseCase(bin)
        _currentCard.postValue(resultCard)
    }

    fun saveCurrentCard() = viewModelScope.launch { saveCurrentCardUseCase() }
    fun removeAllCards() = viewModelScope.launch { removeAllCardsUseCase() }
}
