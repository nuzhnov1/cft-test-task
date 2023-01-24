package com.sunman.binlist.domain.usecase

import com.sunman.binlist.data.repository.IRepository

class SaveCurrentCardUseCase(
    private val repository: IRepository
) {
    suspend operator fun invoke() = repository.saveCurrentCard()
}
