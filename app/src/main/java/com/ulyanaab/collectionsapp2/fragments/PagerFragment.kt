package com.ulyanaab.collectionsapp2.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.SharedElementCallback
import androidx.core.view.doOnAttach
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ulyanaab.collectionsapp2.R
import com.ulyanaab.collectionsapp2.models.Card
import com.ulyanaab.collectionsapp2.recyclerView.CardPagerFragmentStateAdapter
import com.ulyanaab.collectionsapp2.utilities.APP_ACTIVITY
import com.ulyanaab.collectionsapp2.utilities.OnImageReadyCallback
import com.ulyanaab.collectionsapp2.utilities.key_card_list


class PagerFragment : Fragment() {

    private var cardList: List<Card>? = null
    private var startPosition: Int = 0
    private lateinit var viewPager: ViewPager2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cardList = arguments?.get(key_card_list) as List<Card>?
        startPosition = APP_ACTIVITY.currentGalleryPosition

        val view = inflater.inflate(R.layout.fragment_pager, container, false)

        if(cardList != null) {
            initViews(view)

            if(savedInstanceState == null) {
                postponeEnterTransition()
            }
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.drawer.disableDrawer()
    }

    private fun initViews(view: View) {
        viewPager = view.findViewById(R.id.viewPager2)

        with(viewPager) {
            adapter = CardPagerFragmentStateAdapter(
                requireActivity(),
                cardList!!,
                object : OnImageReadyCallback {
                    override fun onImageReady() {
                        startPostponedEnterTransition()
                    }
                })

            doOnAttach {
                setCurrentItem(startPosition, false)
            }

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    APP_ACTIVITY.currentGalleryPosition = position
                }
            })

            prepareTransitions()
        }
    }

    private fun prepareTransitions() {
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.shared_element_transition)

        //postponeEnterTransition()

        setEnterSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>,
                sharedElements: MutableMap<String, View>
            ) {
                val position = APP_ACTIVITY.currentGalleryPosition
                val currentFragment =
                    requireActivity().supportFragmentManager.findFragmentByTag("f$position")
                val view = currentFragment?.view ?: return
                view.findViewById<ImageView>(R.id.big_image)?.let { sharedElements[names[0]] = it }
            }
        })
    }

}