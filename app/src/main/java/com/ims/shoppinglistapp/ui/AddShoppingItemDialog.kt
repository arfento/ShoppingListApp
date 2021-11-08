package com.ims.shoppinglistapp.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import com.ims.shoppinglistapp.R
import com.ims.shoppinglistapp.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

class AddShoppingItemDialog(context: Context, var dialogListener: AppDialogListener) :AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        txtSimpan.setOnClickListener {
            val name = edtInputName.text.toString()
            val amount = edtInputAmount.text.toString()
            
            if (name.isEmpty() || amount.isEmpty()){
                Toast.makeText(context, "Please enter all the information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, amount.toInt())
            dialogListener.onAddButtonClicker(item)
            dismiss()

        }

        txtCancel.setOnClickListener {
            cancel()
        }
    }
}