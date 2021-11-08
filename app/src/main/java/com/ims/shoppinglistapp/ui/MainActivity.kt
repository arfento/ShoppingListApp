package com.ims.shoppinglistapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ims.shoppinglistapp.R
import com.ims.shoppinglistapp.adapter.ShoppingAdapter
import com.ims.shoppinglistapp.data.db.ShoppingDatabase
import com.ims.shoppinglistapp.data.db.entities.ShoppingItem
import com.ims.shoppinglistapp.data.repositories.ShoppingRepository
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory : ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val database = ShoppingDatabase(this)
//        val repository = ShoppingRepository(database)
//        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingAdapter(listOf(), viewModel)
        rvShoppingItem.layoutManager = LinearLayoutManager(this)
        rvShoppingItem.adapter = adapter

        viewModel.getAllShoppingitem().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()

        })

        fab.setOnClickListener {
            AddShoppingItemDialog(this,
            object : AppDialogListener{
                override fun onAddButtonClicker(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }

}