package com.parita.shoestore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.parita.shoestore.R
import com.parita.shoestore.model.Shoes

class ShoesListAdapter(var list: ArrayList<Shoes>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val root: View =
            LayoutInflater.from(parent.context).inflate(R.layout.shoe_list_row, parent, false)
        return RecyclerviewHolder(root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewViewHolder = holder as RecyclerviewHolder
        viewViewHolder.shoeName.text = list.get(position).shoeId + "-" + list.get(position).shoeName
        viewViewHolder.shoeCompany.text = list.get(position).shoeCompany
        viewViewHolder.desc.text = list.get(position).shoeDescription
        viewViewHolder.shoeSize.text = "Size:" + list.get(position).shoeSize
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class RecyclerviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var shoeName: TextView = itemView.findViewById(R.id.shoe_name)
        var shoeCompany: TextView = itemView.findViewById(R.id.shoe_company)
        var desc: TextView = itemView.findViewById(R.id.shoe_description)
        var shoeSize: TextView = itemView.findViewById(R.id.shoe_size)
    }
}