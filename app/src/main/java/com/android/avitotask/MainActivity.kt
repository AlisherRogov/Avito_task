package com.android.avitotask

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ItemListener {
    private lateinit var itemsViewModel: ItemsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemsViewModel = ViewModelProvider(this).get(ItemsViewModel::class.java)
        val itemsAdapter = ItemsAdapter(this as ItemListener)

        itemsViewModel.getItems().observe(this) { items ->
            items.let { itemsAdapter.data = it }
        }

        spanCount = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LAND_SPAN_NUMBER
        } else {
            PORTRAIT_SPAN_NUMBER
        }

        findViewById<RecyclerView>(R.id.recyclerview).apply {
            layoutManager = GridLayoutManager(context, spanCount)
            adapter = itemsAdapter
        }

    }

    override fun delete(item: Item) {
        itemsViewModel.delete(item)
    }

    companion object {
        private const val PORTRAIT_SPAN_NUMBER: Int = 2
        private const val LAND_SPAN_NUMBER: Int = 4
        private var spanCount: Int = 0
    }
}

interface ItemListener {
    fun delete(item: Item)
}