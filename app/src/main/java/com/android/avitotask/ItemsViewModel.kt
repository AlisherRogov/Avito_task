package com.android.avitotask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentLinkedQueue

class ItemsViewModel(application: Application) : AndroidViewModel(application) {
    private val items: MutableLiveData<MutableList<Item>> = MutableLiveData()
    private val itemPull = ConcurrentLinkedQueue<Int>()

    init {
        items.value = Item.getInitialList()
        add()
    }

    fun getItems(): LiveData<MutableList<Item>> {
        return items
    }

    private fun add() {
        viewModelScope.launch {
            while (true) {
                delay(ADD_DURATION)
                if (itemPull.isEmpty()) {
                    items.addNewItem(Item(LIST_LARGEST_ITEM_ID++))
                } else {
                    items.addNewItem(Item(itemPull.remove()))
                }
            }
        }
    }

    fun delete(item: Item) {
        items.removeItem(item)
        itemPull.add(item.id)
    }

    companion object {
        const val ADD_DURATION: Long = 5000
        var LIST_LARGEST_ITEM_ID = 15
    }
}



