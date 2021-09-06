package com.ulyanaab.collectionsapp2.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulyanaab.collectionsapp2.R
import com.ulyanaab.collectionsapp2.models.Card
import com.ulyanaab.collectionsapp2.utilities.*

class CardsAdapter(
    private var dataList: List<Card>,
    private val clickListener: (position: Int, view: View) -> Unit,
    private val longClickListener: (position: Int, view: View) -> Unit,
    private val visibleCheckBox: Boolean,
    private val callback: OnImageReadyCallback? = null
) : RecyclerView.Adapter<CardsAdapter.CardsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        val holder = CardsHolder(view)
        holder.image.clipToOutline = true

        if (visibleCheckBox) {
            holder.checkBox.visibility = View.VISIBLE
        } else {
            holder.checkBox.visibility = View.GONE
            holder.image.setColorFilter(null)
        }

        val imgMetrics = calculateImageSizeInPX(
            parent.context,
            3,
            20,
            85,
            120
        )

        val layoutParams = holder.image.layoutParams
        layoutParams.width = imgMetrics.first
        layoutParams.height = imgMetrics.second
        holder.image.layoutParams = layoutParams

        return holder
    }

    override fun onBindViewHolder(holder: CardsHolder, position: Int) {
        val item = dataList[position]
        holder.image.downloadAndSetPhoto(item.imageUrl) {
            if (callback != null && position == APP_ACTIVITY.currentGalleryPosition) {
                callback.onImageReady()
            }
        }
        holder.image.transitionName = item.imageUrl
        holder.number.text = item.number

        if (visibleCheckBox) {
            holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    holder.image.setColorFilter(colorFilter)
                } else {
                    holder.image.setColorFilter(null)
                }
                item.isCheckedTemp = isChecked
            }

            if (item.isCheckedTemp != null) {
                holder.checkBox.isChecked = item.isCheckedTemp!!
            } else {
                holder.checkBox.isChecked = item.isChecked
            }
        }

        holder.image.setOnClickListener {
            clickListener(position, holder.itemView)
        }

        holder.image.setOnLongClickListener {
            longClickListener(position, it)
            true
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun setNewData(newData: List<Card>) {
        dataList = newData
        notifyDataSetChanged()
    }


    class CardsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image_card)
        val number: TextView = view.findViewById(R.id.number_of_card)
        val checkBox: CheckBox = view.findViewById(R.id.checkbox_card)
    }

}