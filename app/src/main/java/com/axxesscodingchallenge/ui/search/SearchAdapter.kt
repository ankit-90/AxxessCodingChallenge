package com.axxesscodingchallenge.ui.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axxesscodingchallenge.R
import com.axxesscodingchallenge.data.model.Image
import com.axxesscodingchallenge.utils.loadUrl
import kotlinx.android.synthetic.main.item_search.view.*
import timber.log.Timber

class SearchAdapter(private val images: ArrayList<Image>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        holder.bindItem(images[position])
    }

    override fun getItemCount() = images.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(image: Image) {
            image?.let {
                if(it.imageSrc?.size > 0){
                    itemView.img_search.loadUrl(image.imageSrc[0].image)
                }
            }


        }
    }

    fun updateAdapter( images: ArrayList<Image>){
        this.images.addAll(images)
        notifyDataSetChanged()
    }
}