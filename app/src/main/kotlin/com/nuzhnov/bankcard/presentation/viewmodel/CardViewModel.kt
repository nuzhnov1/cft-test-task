package com.nuzhnov.bankcard.presentation.viewmodel

import androidx.lifecycle.*
import com.nuzhnov.bankcard.domain.model.Card
import com.nuzhnov.bankcard.domain.usecase.GetCardByBinUseCase
import com.nuzhnov.bankcard.domain.usecase.GetSavedCardsUseCase
import com.nuzhnov.bankcard.domain.usecase.RemoveAllCardsUseCase
import com.nuzhnov.bankcard.domain.usecase.SaveCurrentCardUseCase
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
