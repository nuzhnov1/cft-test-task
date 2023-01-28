package com.nuzhnov.bankcard.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nuzhnov.bankcard.R
import com.nuzhnov.bankcard.databinding.SavedCardsFragmentBinding
import com.nuzhnov.bankcard.domain.model.Card
import com.nuzhnov.bankcard.presentation.adapter.CardAdapter
import com.nuzhnov.bankcard.presentation.util.showIndefiniteSnackbar
import com.nuzhnov.bankcard.presentation.viewmodel.CardViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@Suppress("unused_parameter")
@WithFragmentBindings
@AndroidEntryPoint
class SavedCardsFragment : Fragment() {
    private var _binding: SavedCardsFragmentBinding? = null
    private val binding get() = _binding!!

    private var cardAdapter: CardAdapter? = null
    private val viewModel: CardViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SavedCardsFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeSavedCardList()
        binding.removeAllCards.setOnClickListener(::onRemoveCardsButtonClick)
        viewModel.savedCards.observe(requireActivity(), ::onSavedCardsUpdated)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeSavedCardList() = binding.savedCardsList.apply {
        cardAdapter = CardAdapter()
        adapter = cardAdapter
        layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        val itemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        val dividerDrawable = AppCompatResources.getDrawable(
            requireContext(),
            R.drawable.divider_shape
        )

        dividerDrawable?.apply {
            itemDecoration.setDrawable(this)
            addItemDecoration(itemDecoration)
        }
    }

    private fun onRemoveCardsButtonClick(view: View) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.warningTitle)
            .setIcon(R.drawable.ic_baseline_warning_24)
            .setMessage(R.string.warningMessage)
            .setPositiveButton(R.string.confirmRemoving) { dialog, _ ->
                viewModel.removeAllCards().invokeOnCompletion {
                    showIndefiniteSnackbar(
                        context = requireContext(),
                        parent = binding.root,
                        message = R.string.successfullyRemovedAllCards
                    )
                }
                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancelRemoving) { dialog, _ -> dialog.cancel() }
            .show()
    }

    private fun onSavedCardsUpdated(cards: List<Card>) { cardAdapter?.submitList(cards) }
}
