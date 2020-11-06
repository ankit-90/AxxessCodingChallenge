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
/**
 * @author Ankit Chandel
 * @since 06/11/20
 * <h1>SearchAdapter</h1>
 * <p>Adapter class is showing images <p>
 * */
class SearchAdapter(private val images: ArrayList<Image>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var itemClickListener : ItemClickListener? = null

    fun setItemClickListener(itemClickListener : ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_search, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        holder.bindItem(images[position])
    }

    override fun getItemCount() = images.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(image: Image) {
            if(image.imageSrc != null){
                if(image.imageSrc?.size > 0){
                    itemView.img_search.loadUrl(image.imageSrc_)
                }
                itemView.setOnClickListener { itemClickListener?.onItemCick(image) }
            }
            itemView.setOnClickListener { itemClickListener?.onItemCick(image) }
        }
    }

    fun updateAdapter( images: ArrayList<Image>){
        this.images.addAll(images)
        notifyDataSetChanged()
    }

    interface ItemClickListener{

        fun onItemCick(image:Image)

    }
}