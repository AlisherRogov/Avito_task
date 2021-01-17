package com.android.avitotask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter(private val listener: ItemListener) : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {
    var data = listOf<Item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val current = data[position]
        holder.bind(current.id)
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, android.R.anim.slide_in_left)
        holder.itemView.animation.duration = ANIMATION_DURATION
        holder.deleteButton.setOnClickListener {
            listener.delete(current)
        }
    }

    class ItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.item_id)
        val deleteButton: Button = itemView.findViewById(R.id.delete_item)

        fun bind(text: Int) {
            wordItemView.text = text.toString()
        }

        companion object {
            fun create(parent: ViewGroup): ItemsViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recyclerview_item, parent, false)
                return ItemsViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    companion object {
        const val ANIMATION_DURATION: Long = 500
    }
}