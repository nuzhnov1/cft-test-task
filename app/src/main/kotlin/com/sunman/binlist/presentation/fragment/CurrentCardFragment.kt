package com.sunman.binlist.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import com.sunman.binlist.R
import com.sunman.binlist.databinding.CurrentCardFragmentBinding
import com.sunman.binlist.domain.model.Card
import com.sunman.binlist.presentation.util.isNumber
import com.sunman.binlist.presentation.util.setCard
import com.sunman.binlist.presentation.util.toString
import com.sunman.binlist.presentation.viewmodel.CardViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@WithFragmentBindings
@AndroidEntryPoint
class CurrentCardFragment : Fragment() {
    private var _binding: CurrentCardFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CardViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CurrentCardFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.binNumberField.editText?.doOnTextChanged(::onBinNumberChanged)
        binding.searchButton.setOnClickListener(::onSearchButtonClick)
        binding.addButton.setOnClickListener(::onSaveButtonClick)

        viewModel.currentCard.observe(requireActivity(), ::onResultReceived)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        binding.binNumberField.editText?.text?.apply {
            val savedBinNumber = savedInstanceState?.getString(BIN_NUMBER_KEY)

            clear()
            savedBinNumber?.apply { append(this) }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val binNumber = binding.binNumberField.editText?.text?.toString()
        binNumber?.apply { outState.putString(BIN_NUMBER_KEY, this) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onBinNumberChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
        val input = text?.toString()

        if (input != null && !input.isNumber()) {
            binding.binNumberField.error = requireContext().getString(R.string.binErrorMessage)
        } else {
            binding.binNumberField.error = null
        }
    }

    private fun onSearchButtonClick(view: View) {
        val binNumber = binding.binNumberField.editText?.text?.toString()
        val isNotError = binding.binNumberField.error != null

        if (isNotError && binNumber != null) {
            viewModel.getCardByBin(binNumber)
        }
    }

    private fun onSaveButtonClick(view: View) { viewModel.saveCurrentCard() }

    private fun onResultReceived(result: Result<Card?>) = result
        .onSuccess { binding.cardInfo.setCard(it) }
        .onFailure {
            val context = requireContext()
            val errorString = it.toString(context)

            Snackbar.make(context, binding.root, errorString, Snackbar.LENGTH_INDEFINITE).show()
        }


    companion object {
        const val BIN_NUMBER_KEY = "BIN_NUMBER_KEY"
    }
}
