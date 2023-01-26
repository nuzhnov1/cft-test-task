package com.sunman.binlist.domain.usecase

import com.sunman.binlist.data.repository.IRepository
import javax.inject.Inject

class GetSavedCardsUseCase @Inject constructor(
    private val repository: IRepository
) {
    operator fun invoke() = repository.savedCards
}
