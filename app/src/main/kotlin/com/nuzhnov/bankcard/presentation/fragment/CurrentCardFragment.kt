package com.nuzhnov.bankcard.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.nuzhnov.bankcard.R
import com.nuzhnov.bankcard.databinding.CurrentCardFragmentBinding
import com.nuzhnov.bankcard.domain.model.Card
import com.nuzhnov.bankcard.presentation.util.isBankCardNumber
import com.nuzhnov.bankcard.presentation.util.setCard
import com.nuzhnov.bankcard.presentation.util.showIndefiniteSnackbar
import com.nuzhnov.bankcard.presentation.util.toString
import com.nuzhnov.bankcard.presentation.viewmodel.CardViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@Suppress("unused_parameter")
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
        binding.binNumberField.editText?.doAfterTextChanged(::onBinNumberChanged)
        binding.searchButton.setOnClickListener(::onSearchButtonClick)
        binding.addButton.setOnClickListener(::onSaveButtonClick)

        viewModel.currentCard.observe(requireActivity(), ::onResultReceived)
        binding.cardInfo.setCard(viewModel.currentCard.value?.getOrNull())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        binding.binNumberField.editText?.text?.apply {
            val savedBinNumber = savedInstanceState?.getString(BIN_NUMBER_KEY)
            savedBinNumber?.apply {
                clear()
                append(this)
            }
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

    private fun onBinNumberChanged(text: Editable?) {
        val input = text?.toString()

        if (!input.isNullOrBlank() && !input.isBankCardNumber()) {
            binding.binNumberField.error = requireContext().getString(R.string.binErrorMessage)
        } else {
            binding.binNumberField.error = null
        }
    }

    private fun hideSoftInputFromWindow() {
        val activity = requireActivity()
        activity.currentFocus?.apply {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(windowToken, 0)
        }
    }

    private fun onSearchButtonClick(view: View) {
        val input = binding.binNumberField.editText?.text?.toString()
        val isNotError = binding.binNumberField.error == null

        if (isNotError && !input.isNullOrBlank()) {
            hideSoftInputFromWindow()
            viewModel.getCardByBin(input)
        }
    }

    private fun onSaveButtonClick(view: View) {
        hideSoftInputFromWindow()
        viewModel.saveCurrentCard().invokeOnCompletion {
            showIndefiniteSnackbar(
                context = requireContext(),
                parent = binding.root,
                message = R.string.successfullySavedStatus
            )
        }
    }

    private fun onResultReceived(result: Result<Card?>) = result
        .onSuccess { card ->
            binding.cardInfo.setCard(card)

            if (card == null) {
                showIndefiniteSnackbar(
                    context = requireContext(),
                    parent = binding.root,
                    message = R.string.notFoundedStatus
                )
            } else {
                showIndefiniteSnackbar(
                    context = requireContext(),
                    parent = binding.root,
                    message = R.string.successfullyFoundedStatus
                )
            }
        }
        .onFailure { error ->
            val context = requireContext()
            val errorString = error.toString(context)

            showIndefiniteSnackbar(
                context = context,
                parent = binding.root,
                message = errorString
            )
        }


    companion object {
        const val BIN_NUMBER_KEY = "BIN_NUMBER_KEY"
    }
}
