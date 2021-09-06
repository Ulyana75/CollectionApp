package com.ulyanaab.collectionsapp2.recyclerView

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ulyanaab.collectionsapp2.fragments.BigCardFragment
import com.ulyanaab.collectionsapp2.models.Card
import com.ulyanaab.collectionsapp2.utilities.OnImageReadyCallback

class CardPagerFragmentStateAdapter(
    activity: FragmentActivity,
    private val dataList: List<Card>,
    private val callback: OnImageReadyCallback
) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun createFragment(position: Int): Fragment {
        return BigCardFragment.newInstance(dataList[position], callback)
    }
}