package com.nuzhnov.bankcard.domain.usecase

import com.nuzhnov.bankcard.data.repository.IRepository
import javax.inject.Inject

class RemoveAllCardsUseCase @Inject constructor(
    private val repository: IRepository
) {
    suspend operator fun invoke() = repository.removeAllCards()
}
