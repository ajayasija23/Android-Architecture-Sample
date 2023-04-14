package com.asija.assignment.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asija.assignment.R
import com.asija.assignment.databinding.ItemShoppingBinding
import com.asija.assignment.model.Product
import com.bumptech.glide.Glide

class ProductAdapter(private val context: Context, private val list: List<Product>, private val onSelect:(Product)->Unit): RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    class MyViewHolder(val binding:ItemShoppingBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemShoppingBinding.inflate(LayoutInflater.from(context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item=list[position]
        holder.binding.item=item
        holder.binding.apply {
            tvCurrentPrice.text=String.format("%s %.2f",context.getString(R.string.rs),item.price)
            Glide.with(context).load(item.image).into(ivImage)
            root.setOnClickListener {
                onSelect.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}