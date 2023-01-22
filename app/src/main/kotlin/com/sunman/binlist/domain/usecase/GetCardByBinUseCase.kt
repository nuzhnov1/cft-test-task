package com.sunman.binlist.domain.usecase

import com.sunman.binlist.domain.repository.IRepository

class GetCardByBinUseCase(
    private val repository: IRepository
) {
    suspend operator fun invoke(bin: Int) = repository.getCardByBin(bin)
}
