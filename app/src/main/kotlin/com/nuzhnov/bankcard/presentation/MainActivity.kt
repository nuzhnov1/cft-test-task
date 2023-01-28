package com.nuzhnov.bankcard.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nuzhnov.bankcard.R
import com.nuzhnov.bankcard.databinding.ActivityMainBinding
import com.nuzhnov.bankcard.presentation.fragment.CurrentCardFragment
import com.nuzhnov.bankcard.presentation.fragment.SavedCardsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        viewPager.adapter = FragmentViewPagerAdapter(this)
        TabLayoutMediator(
            tabLayout, viewPager,
            true, true,
            ::onSetTabConfigurationStrategy
        ).attach()
    }

    private fun onSetTabConfigurationStrategy(tab: TabLayout.Tab, position: Int) {
        if (position == 1) {
            tab.text = getString(R.string.savedCardsFragmentTitle)
            tab.icon = AppCompatResources.getDrawable(
                this,
                R.drawable.ic_baseline_card_list_24
            )
        } else {
            tab.text = getString(R.string.currentCardFragmentTitle)
            tab.icon = AppCompatResources.getDrawable(
                this,
                R.drawable.ic_baseline_card_24
            )
        }
    }


    private class FragmentViewPagerAdapter(activity: FragmentActivity)
        : FragmentStateAdapter(activity) {

        override fun getItemCount() = 2

        override fun createFragment(position: Int): Fragment {
            return if (position == 1) {
                SavedCardsFragment()
            } else {
                CurrentCardFragment()
            }
        }
    }
}
