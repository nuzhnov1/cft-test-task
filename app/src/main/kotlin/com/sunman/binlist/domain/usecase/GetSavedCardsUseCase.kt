package com.sunman.binlist.domain.usecase

import com.sunman.binlist.domain.repository.IRepository

class GetSavedCardsUseCase(
    private val repository: IRepository
) {
    operator fun invoke() = repository.savedCards
}