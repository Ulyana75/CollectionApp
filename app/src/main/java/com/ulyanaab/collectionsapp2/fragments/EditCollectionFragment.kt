package com.ulyanaab.collectionsapp2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.ulyanaab.collectionsapp2.R
import com.ulyanaab.collectionsapp2.controllers.CollectionController
import com.ulyanaab.collectionsapp2.models.Card
import com.ulyanaab.collectionsapp2.recyclerView.CardsAdapter
import com.ulyanaab.collectionsapp2.utilities.APP_ACTIVITY
import com.ulyanaab.collectionsapp2.utilities.key_card_list
import com.ulyanaab.collectionsapp2.utilities.key_controller


class EditCollectionFragment : Fragment() {

    private var cardList: List<Card>? = null
    private var controller: CollectionController? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CardsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        controller = arguments?.get(key_controller) as CollectionController?
        cardList = controller?.getEditCardList()

        return inflater.inflate(R.layout.fragment_edit_collection, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (cardList != null) {
            initViews()
        }
    }

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.drawer.disableDrawer()
    }

    override fun onStop() {
        super.onStop()
        cardList?.forEach {
            it.isCheckedTemp = null
        }
    }

    private fun initViews() {
        recyclerView = requireView().findViewById(R.id.recycler_view_edit_collection)
        adapter = CardsAdapter(
            cardList!!,
            this::cardListener,
            { _, _ -> },
            true
        )
        recyclerView.adapter = adapter

        requireView().findViewById<CheckBox>(R.id.choose_all)
            .setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    cardList?.forEach {
                        it.isCheckedTemp = true
                    }
                } else {
                    cardList?.forEach {
                        it.isCheckedTemp = false
                    }
                }
                adapter.setNewData(cardList!!)
            }

        requireView().findViewById<CheckBox>(R.id.choose_all).isChecked = controller?.getLeftCardQuantity() == 0

        requireView().findViewById<Button>(R.id.button_save).setOnClickListener {
            controller?.saveCollection()
            APP_ACTIVITY.navController.popBackStack()
        }
    }

    private fun cardListener(position: Int, view: View) {
        val item = cardList!![position]

        if (item.isCheckedTemp == null) {
            item.isCheckedTemp = !item.isChecked
        } else {
            item.isCheckedTemp = !item.isCheckedTemp!!
        }
        view.findViewById<CheckBox>(R.id.checkbox_card).isChecked = item.isCheckedTemp!!
    }

}