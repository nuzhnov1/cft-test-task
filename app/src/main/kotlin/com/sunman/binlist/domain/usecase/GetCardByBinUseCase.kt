package com.sunman.binlist.domain.usecase

import com.sunman.binlist.data.repository.IRepository

class GetCardByBinUseCase(
    private val repository: IRepository
) {
    suspend operator fun invoke(bin: String) = repository.getCardByBin(bin)
}
