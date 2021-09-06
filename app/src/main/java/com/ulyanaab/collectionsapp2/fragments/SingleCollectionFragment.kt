package com.ulyanaab.collectionsapp2.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.ulyanaab.collectionsapp2.R
import com.ulyanaab.collectionsapp2.controllers.CollectionController
import com.ulyanaab.collectionsapp2.utilities.*


class SingleCollectionFragment : Fragment() {

    private var controller: CollectionController? = null
    private var imageUrl: String? = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        prepareTransitions()

        controller = arguments?.get(key_controller) as CollectionController?
        imageUrl = arguments?.getString(key_main_img_url)

        return inflater.inflate(R.layout.fragment_single_collection, container, false)
    }

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.currentGalleryPosition = 0
        if (controller != null) {
            initViews()
        }
    }

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.drawer.disableDrawer()
    }

    private fun prepareTransitions() {
        postponeEnterTransition()
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        exitTransition = Fade()
    }

    @SuppressLint("CutPasteId")
    private fun initViews() {
        with(requireView()) {
            findViewById<ImageView>(R.id.main_image).transitionName = imageUrl
            findViewById<ImageView>(R.id.main_image).downloadAndSetPhoto(imageUrl!!) {
                startPostponedEnterTransition()
            }
            findViewById<ImageView>(R.id.main_image).clipToOutline = true

            initMainText()
            initButtons()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun View.initMainText() {
        if (controller?.getLeftCardQuantity() == 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                findViewById<TextView>(R.id.main_text).setTextColor(APP_ACTIVITY.getColor(R.color.colorGreen))
            }
            findViewById<TextView>(R.id.main_text).text =
                APP_ACTIVITY.getString(R.string.collection_done)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                findViewById<TextView>(R.id.main_text).setTextColor(APP_ACTIVITY.getColor(R.color.colorRed))
            }
            findViewById<TextView>(R.id.main_text).text =
                "Карт не хватает: ${controller?.getLeftCardQuantity()}"
        }
    }

    private fun View.initButtons() {
        findViewById<ConstraintLayout>(R.id.edit_collection_button).setOnClickListener {
            APP_ACTIVITY.navController.navigate(
                R.id.editCollectionFragment,
                bundleOf(
                    key_controller to controller
                )
            )
        }

        findViewById<ConstraintLayout>(R.id.my_collection_button).setOnClickListener {
            APP_ACTIVITY.navController.navigate(
                R.id.showCollectionFragment,
                bundleOf(
                    key_card_list to controller?.getHaveCardList(),
                    key_up_text to APP_ACTIVITY.getString(R.string.text_your_cards),
                    key_controller to controller
                )
            )
        }

        findViewById<ConstraintLayout>(R.id.whats_not_enough_button).setOnClickListener {
            APP_ACTIVITY.navController.navigate(
                R.id.showCollectionFragment,
                bundleOf(
                    key_card_list to controller?.getNoCardList(),
                    key_up_text to APP_ACTIVITY.getString(R.string.text_not_enough_cards),
                    key_controller to controller
                )
            )
        }
    }

}