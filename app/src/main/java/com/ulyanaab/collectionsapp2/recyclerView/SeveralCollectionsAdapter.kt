package com.ulyanaab.collectionsapp2.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulyanaab.collectionsapp2.R
import com.ulyanaab.collectionsapp2.models.CollectionSeries
import com.ulyanaab.collectionsapp2.utilities.calculateImageSizeInPX
import com.ulyanaab.collectionsapp2.utilities.downloadAndSetPhoto

class SeveralCollectionsAdapter(
    private val dataList: List<CollectionSeries>,
    private val listener: (item: CollectionSeries, view: View) -> Unit
) :
    RecyclerView.Adapter<SeveralCollectionsAdapter.SeveralCollectionsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeveralCollectionsHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.several_collection_item, parent, false)
        val holder = SeveralCollectionsHolder(view)
        holder.image.clipToOutline = true

        val imgMetrics = calculateImageSizeInPX(
            parent.context,
            2,
            20,
            150,
            216
        )

        val layoutParams = holder.image.layoutParams
        layoutParams.width = imgMetrics.first
        layoutParams.height = imgMetrics.second
        holder.image.layoutParams = layoutParams

        return holder
    }

    override fun onBindViewHolder(holder: SeveralCollectionsHolder, position: Int) {
        val item = dataList[position]
        holder.image.downloadAndSetPhoto(item.imageUrl)
        holder.image.transitionName = item.imageUrl
        holder.name.text = item.name
        holder.itemView.setOnClickListener {
            listener(item, it)
        }
    }

    override fun getItemCount(): Int = dataList.size


    class SeveralCollectionsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image_several)
        val name: TextView = view.findViewById(R.id.text_several)
    }

}