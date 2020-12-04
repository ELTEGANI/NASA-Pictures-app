package com.example.nasapicturesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nasapicturesapp.data.ImagesProperties
import com.example.nasapicturesapp.databinding.GridViewItemBinding
import kotlinx.android.synthetic.main.grid_view_item.view.*
import javax.inject.Inject


class PhotoGridAdapter @Inject constructor() : ListAdapter<ImagesProperties, PhotoGridAdapter.ImagesPropertyViewHolder>(DiffCallback) {

    private var onClickListener:OnClickListener? = null

    fun setOnClickListener(onClickListener:OnClickListener) {
        this.onClickListener = onClickListener
    }

    class ImagesPropertyViewHolder(private var binding: GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imagesProperties: ImagesProperties,onClickListener:OnClickListener) {
            binding.images = imagesProperties
            binding.clickListener = onClickListener
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesPropertyViewHolder {
        return ImagesPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ImagesPropertyViewHolder, position: Int) {
        onClickListener?.let { holder.bind(getItem(position)!!, it) }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<ImagesProperties>() {
        override fun areItemsTheSame(oldItem: ImagesProperties, newItem: ImagesProperties): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ImagesProperties, newItem: ImagesProperties): Boolean {
            return oldItem.title == newItem.title
        }
    }

    class OnClickListener(val clickListener: (imagesProperties:ImagesProperties) -> Unit) {
        fun onClick(imagesProperties:ImagesProperties) = clickListener(imagesProperties)
    }
}

