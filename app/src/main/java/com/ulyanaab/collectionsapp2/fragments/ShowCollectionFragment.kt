package com.ulyanaab.collectionsapp2.fragments

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.app.SharedElementCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.ulyanaab.collectionsapp2.R
import com.ulyanaab.collectionsapp2.controllers.CollectionController
import com.ulyanaab.collectionsapp2.models.Card
import com.ulyanaab.collectionsapp2.recyclerView.CardsAdapter
import com.ulyanaab.collectionsapp2.utilities.*


class ShowCollectionFragment : Fragment() {

    private var cardsList: List<Card>? = null
    private var controller: CollectionController? = null
    private var upText: String? = null

    private lateinit var recyclerView: RecyclerView
    private var wasChanged = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cardsList = arguments?.get(key_card_list) as List<Card>?
        upText = arguments?.getString(key_up_text)
        controller = arguments?.get(key_controller) as CollectionController?

        if(cardsList?.size != 0) {
            prepareTransition()
            postponeEnterTransition()
        }

        return inflater.inflate(R.layout.fragment_show_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view_cards)
        scrollToPosition()
    }

    override fun onStart() {
        super.onStart()
        if (cardsList != null) {
            initViews()
        }
    }

    override fun onStop() {
        super.onStop()
        if (wasChanged) {
            controller?.saveCollection()
        }
    }

    private fun prepareTransition() {

        exitTransition = TransitionInflater.from(context).inflateTransition(R.transition.grid_exit_transition)
        //enterTransition = Fade()

        setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>,
                sharedElements: MutableMap<String, View>
            ) {
                val position = APP_ACTIVITY.currentGalleryPosition
                val selectedViewHolder =
                    recyclerView.findViewHolderForAdapterPosition(position) ?: return

                sharedElements[names[0]] = selectedViewHolder.itemView.findViewById(R.id.image_card)
            }
        })
    }

    private fun scrollToPosition() {
        recyclerView.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(
                v: View?,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int
            ) {
                recyclerView.removeOnLayoutChangeListener(this)

                val view =
                    recyclerView.layoutManager?.findViewByPosition(APP_ACTIVITY.currentGalleryPosition)

                if (view == null || recyclerView.layoutManager?.isViewPartiallyVisible(
                        view, false, true
                    ) == true
                ) {
                    recyclerView.post {
                        recyclerView.layoutManager?.scrollToPosition(APP_ACTIVITY.currentGalleryPosition)
                    }
                }
            }

        })
    }

    private fun initViews() {
        recyclerView.adapter = CardsAdapter(
            cardsList!!,
            this::cardClickListener,
            this::cardLongClickListener,
            false,
            object : OnImageReadyCallback {
                override fun onImageReady() {
                    startPostponedEnterTransition()
                }
            }
        )

        requireView().findViewById<TextView>(R.id.title_text).text = upText
    }

    private fun cardClickListener(position: Int, view: View) {
        APP_ACTIVITY.currentGalleryPosition = position

        val extras = FragmentNavigatorExtras(
            view.findViewById<ImageView>(R.id.image_card) to cardsList!![position].imageUrl
        )

        APP_ACTIVITY.navController.navigate(
            R.id.pagerFragment,
            bundleOf(
                key_card_list to cardsList,
            ),
            null,
            extras
        )
    }

    private fun cardLongClickListener(position: Int, view: View) {
        val popup = PopupMenu(requireContext(), view)
        popup.inflate(R.menu.card_option_menu)
        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete -> {
                    cardsList!![position].isChecked = !cardsList!![position].isChecked
                    wasChanged = true
                    (view as ImageView).setColorFilter(colorFilter)
                    showToast("Данные обновлены")
                    true
                }
                else -> {
                    false
                }
            }
        }
        popup.show()
    }

}