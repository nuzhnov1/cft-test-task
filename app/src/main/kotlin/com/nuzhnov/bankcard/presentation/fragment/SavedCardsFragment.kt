package com.nuzhnov.bankcard.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nuzhnov.bankcard.R
import com.nuzhnov.bankcard.databinding.SavedCardsFragmentBinding
import com.nuzhnov.bankcard.domain.model.Card
import com.nuzhnov.bankcard.presentation.adapter.CardAdapter
import com.nuzhnov.bankcard.presentation.viewmodel.CardViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

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
        cardAdapter = CardAdapter()

        initializeSavedCardList()
        binding.removeAllCards.setOnClickListener(::onRemoveCardsButtonClick)
        viewModel.savedCards.observe(requireActivity(), ::onSavedCardsUpdated)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeSavedCardList() = binding.savedCardsList.apply {
        adapter = cardAdapter
        layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )

        val itemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        val dividerDrawable = ResourcesCompat.getDrawable(
            resources, R.drawable.divider_drawable, context.theme
        )

        dividerDrawable?.apply { itemDecoration.setDrawable(this) }
        addItemDecoration(itemDecoration)
    }

    private fun onRemoveCardsButtonClick(view: View) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.warningTitle)
            .setIcon(R.drawable.ic_baseline_warning_24)
            .setMessage(R.string.warningMessage)
            .setPositiveButton(R.string.confirmRemoving) { dialog, _ ->
                viewModel.removeAllCards()
                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancelRemoving) { dialog, _ -> dialog.cancel() }
            .show()
    }

    private fun onSavedCardsUpdated(cards: List<Card>) { cardAdapter?.submitList(cards) }
}
