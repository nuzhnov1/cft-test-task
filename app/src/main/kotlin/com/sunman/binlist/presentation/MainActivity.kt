package com.sunman.binlist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sunman.binlist.R
import com.sunman.binlist.databinding.ActivityMainBinding
import com.sunman.binlist.presentation.fragment.CurrentCardFragment
import com.sunman.binlist.presentation.fragment.SavedCardsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayout = binding.tabLayout
        val viewPager = binding.pager

        viewPager.adapter = FragmentViewPagerAdapter(this)
        TabLayoutMediator(
            tabLayout, viewPager,
            true, true,
            ::onSetTabConfigurationStrategy
        )
    }

    private fun onSetTabConfigurationStrategy(tab: TabLayout.Tab, position: Int) {
        when (position) {
            1 -> {
                tab.text = getString(R.string.savedCardsTitle)
                tab.icon = AppCompatResources.getDrawable(
                    this, R.drawable.ic_baseline_card_list_24
                )
            }

            else -> {
                tab.text = getString(R.string.currentCardFragmentTitle)
                tab.icon = AppCompatResources.getDrawable(
                    this, R.drawable.ic_baseline_card_24
                )
            }
        }
    }


    private class FragmentViewPagerAdapter(activity: FragmentActivity)
        : FragmentStateAdapter(activity) {

        override fun getItemCount() = 2

        override fun createFragment(position: Int) = when (position) {
            1 -> SavedCardsFragment()
            else -> CurrentCardFragment()
        }
    }
}
