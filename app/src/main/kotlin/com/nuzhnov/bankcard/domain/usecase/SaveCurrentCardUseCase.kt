package com.nuzhnov.bankcard.domain.usecase

import com.nuzhnov.bankcard.data.repository.IRepository
import javax.inject.Inject

class SaveCurrentCardUseCase @Inject constructor(
    private val repository: IRepository
) {
    suspend operator fun invoke() = repository.saveCurrentCard()
}
