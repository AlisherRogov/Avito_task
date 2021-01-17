package com.android.avitotask

import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

fun <T> MutableLiveData<MutableList<T>>.addNewItem(item: T) {
    val oldValue = this.value ?: mutableListOf()
    var oldValueSize = oldValue.size
    if (oldValueSize == 0) oldValueSize = 1
    val index = Random.nextInt(oldValueSize)
    oldValue.add(index, item)
    this.value = oldValue
}


fun <T> MutableLiveData<MutableList<T>>.removeItem(item: Item) {
    if (!this.value.isNullOrEmpty()) {
        val oldValue = this.value
        oldValue?.remove(item)
        this.value = oldValue
    } else {
        this.value = mutableListOf()
    }
}
