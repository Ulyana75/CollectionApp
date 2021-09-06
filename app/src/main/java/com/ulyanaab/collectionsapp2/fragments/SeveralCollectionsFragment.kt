package com.ulyanaab.collectionsapp2.fragments

import android.os.Bundle
import android.transition.Fade
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.ulyanaab.collectionsapp2.R
import com.ulyanaab.collectionsapp2.controllers.CollectionController
import com.ulyanaab.collectionsapp2.models.CollectionSeries
import com.ulyanaab.collectionsapp2.recyclerView.SeveralCollectionsAdapter
import com.ulyanaab.collectionsapp2.utilities.APP_ACTIVITY
import com.ulyanaab.collectionsapp2.utilities.key_controller
import com.ulyanaab.collectionsapp2.utilities.key_main_img_url


class SeveralCollectionsFragment : Fragment() {

    private var controller: CollectionController? = null
    private var collectionsList: List<CollectionSeries>? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        prepareTransitions()

        controller = arguments?.get(key_controller) as CollectionController?
        collectionsList = controller?.getCollectionsList()

        return inflater.inflate(R.layout.fragment_several_collections, container, false)
    }

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.drawer.enableDrawer()
        if (collectionsList != null) {
            initViews()
        }
    }

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = controller?.getTitle()
    }

    private fun prepareTransitions() {
        postponeEnterTransition()
        exitTransition = Fade()
        enterTransition = Fade()
    }

    private fun initViews() {
        recyclerView = requireView().findViewById(R.id.recycler_view_several)
        recyclerView.adapter =
            SeveralCollectionsAdapter(collectionsList!!, this::recyclerViewItemListener)

        recyclerView.apply {
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
    }

    private fun recyclerViewItemListener(item: CollectionSeries, view: View) {
        val extras = FragmentNavigatorExtras(
            view.findViewById<ImageView>(R.id.image_several) to item.imageUrl
        )

        APP_ACTIVITY.navController.navigate(
            R.id.singleCollectionFragment,
            bundleOf(
                key_controller to controller?.setCollectionId(item.id),
                key_main_img_url to item.imageUrl
            ),
            null,
            extras
        )
    }

}