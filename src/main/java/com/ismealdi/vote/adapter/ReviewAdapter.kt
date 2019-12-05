package com.ismealdi.vote.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.ismealdi.meepopup.schema.Review
import com.ismealdi.meepopup.util.binding.DataBoundBindAdapter
import com.ismealdi.meepopup.util.binding.DataBoundViewHolder
import com.ismealdi.meepopup.util.common.Logs
import com.ismealdi.vote.R
import com.ismealdi.vote.databinding.ItemReviewBinding

class ReviewAdapter(
    private val context: Context,
    private val uid: String,
    private val itemClick: ((Int, Review) -> Unit)? = null
) : DataBoundBindAdapter<Review, ViewDataBinding>(
    diffCallback = object : DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.uid == newItem.uid
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem == newItem
        }

    }) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return DataBindingUtil.inflate<ItemReviewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_review,
            parent,
            false
        )
    }

    override fun bind(
        binding: ViewDataBinding,
        item: Review,
        view: View,
        position: Int,
        holder: DataBoundViewHolder<ViewDataBinding>
    ) {

        with((holder.binding as ItemReviewBinding)) {
            review = item

            buttonMore.visibility = if(item.uid == uid) VISIBLE else GONE

            holder.itemView.setOnClickListener {
                itemClick?.invoke(position, item)
                Logs.i("Clicked position, $position ${item.review}")
            }
        }

    }


}

