package com.nuzhnov.bankcard.domain.usecase

import com.nuzhnov.bankcard.data.repository.IRepository
import javax.inject.Inject

class GetSavedCardsUseCase @Inject constructor(
    private val repository: IRepository
) {
    operator fun invoke() = repository.savedCards
}
