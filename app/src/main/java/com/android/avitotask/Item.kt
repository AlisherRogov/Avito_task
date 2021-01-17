package com.android.avitotask

import java.util.ArrayList

class Item(val id: Int) {
    companion object {
        private var list: ArrayList<Item>? = null
        fun getInitialList(): MutableList<Item> {
            if (list == null) {
                list = ArrayList()
                for (i in 0 until 15) {
                    list!!.add(Item(i))
                }
            }
            return list as ArrayList<Item>
        }
    }
}