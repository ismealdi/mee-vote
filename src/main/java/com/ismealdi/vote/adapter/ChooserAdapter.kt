package com.ismealdi.vote.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.ismealdi.meepopup.schema.Place
import com.ismealdi.meepopup.schema.User
import com.ismealdi.meepopup.util.binding.DataBoundBindAdapter
import com.ismealdi.meepopup.util.binding.DataBoundViewHolder
import com.ismealdi.meepopup.util.common.Logs
import com.ismealdi.vote.R
import com.ismealdi.vote.databinding.ItemChooserBinding
import com.ismealdi.meepopup.R as MeePopUpR

class ChooserAdapter(
    private val context: Context,
    private val itemClick: ((Int, User) -> Unit)? = null
) : DataBoundBindAdapter<User, ViewDataBinding>(
    diffCallback = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.name == newItem.name
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return DataBindingUtil.inflate<ItemChooserBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_chooser,
            parent,
            false
        )
    }

    override fun bind(
        binding: ViewDataBinding,
        item: User,
        view: View,
        position: Int,
        holder: DataBoundViewHolder<ViewDataBinding>
    ) {

        with((holder.binding as ItemChooserBinding)) {
            user = item

            holder.itemView.setOnClickListener {
                itemClick?.invoke(position, item)
                Logs.i("Clicked position, $position ${item.name}")
            }
        }

    }


}