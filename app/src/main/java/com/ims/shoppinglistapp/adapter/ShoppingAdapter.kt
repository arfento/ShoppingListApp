package com.ims.shoppinglistapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ims.shoppinglistapp.R
import com.ims.shoppinglistapp.data.db.entities.ShoppingItem
import com.ims.shoppinglistapp.ui.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingitem = items[position]

        holder.itemView.tvName.text = curShoppingitem.name
        holder.itemView.tvAmount.text = "${curShoppingitem.amount}"

        holder.itemView.imgDelete.setOnClickListener {
            viewModel.delete(curShoppingitem)
        }

        holder.itemView.imgPlus.setOnClickListener {
            curShoppingitem.amount++
            viewModel.upsert(curShoppingitem)
        }

        holder.itemView.imgMinus.setOnClickListener {
            if (curShoppingitem.amount > 0){
                curShoppingitem.amount--
                viewModel.upsert(curShoppingitem)
            }

        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}