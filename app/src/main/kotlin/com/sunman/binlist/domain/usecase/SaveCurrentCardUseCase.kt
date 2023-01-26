package com.sunman.binlist.domain.usecase

import com.sunman.binlist.data.repository.IRepository
import javax.inject.Inject

class SaveCurrentCardUseCase @Inject constructor(
    private val repository: IRepository
) {
    suspend operator fun invoke() = repository.saveCurrentCard()
}
