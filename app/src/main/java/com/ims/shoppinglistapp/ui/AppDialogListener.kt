package com.ims.shoppinglistapp.ui

import com.ims.shoppinglistapp.data.db.entities.ShoppingItem

interface AppDialogListener {
    fun onAddButtonClicker(item: ShoppingItem)
}