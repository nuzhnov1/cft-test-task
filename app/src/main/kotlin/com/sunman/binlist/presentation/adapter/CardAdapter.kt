package com.sunman.binlist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sunman.binlist.databinding.SavedCardBinding
import com.sunman.binlist.domain.model.Card
import com.sunman.binlist.presentation.util.setCard

class CardAdapter : ListAdapter<Card, CardAdapter.ViewHolder>(CardDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SavedCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(private val savedCardBinding: SavedCardBinding) :
        RecyclerView.ViewHolder(savedCardBinding.root) {

        fun bind(card: Card) = savedCardBinding.cardInfo.setCard(card)
    }

    private object CardDiffCallback : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.bin == newItem.bin
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return (oldItem.number == newItem.number &&
                    oldItem.scheme == newItem.scheme &&
                    oldItem.type == newItem.type &&
                    oldItem.brand == newItem.brand &&
                    oldItem.prepaid == newItem.prepaid &&
                    oldItem.country == newItem.country &&
                    oldItem.bank == newItem.bank)
        }
    }
}
