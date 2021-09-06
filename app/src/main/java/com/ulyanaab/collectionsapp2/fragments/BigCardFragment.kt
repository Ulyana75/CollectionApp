package com.ulyanaab.collectionsapp2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.ulyanaab.collectionsapp2.R
import com.ulyanaab.collectionsapp2.models.Card
import com.ulyanaab.collectionsapp2.utilities.OnImageReadyCallback
import com.ulyanaab.collectionsapp2.utilities.downloadAndSetPhoto
import com.ulyanaab.collectionsapp2.utilities.key_big_card
import com.ulyanaab.collectionsapp2.utilities.key_callback


class BigCardFragment : Fragment() {

    private var card: Card? = null
    private var callback: OnImageReadyCallback? = null


    companion object {
        fun newInstance(card: Card?, callback: OnImageReadyCallback): BigCardFragment {
            val fragment = BigCardFragment()
            fragment.arguments = bundleOf(
                key_big_card to card,
                key_callback to callback
            )
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        card = arguments?.get(key_big_card) as Card?
        callback = arguments?.get(key_callback) as OnImageReadyCallback?

        return inflater.inflate(R.layout.fragment_big_card, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (card != null) {
            initViews()
        }
    }

    private fun initViews() {
        requireView().findViewById<ImageView>(R.id.big_image).transitionName = card!!.imageUrl
        requireView().findViewById<ImageView>(R.id.big_image).downloadAndSetPhoto(card!!.imageUrl) {
            callback?.onImageReady()
        }
        requireView().findViewById<TextView>(R.id.big_number).text = card!!.number
    }

}