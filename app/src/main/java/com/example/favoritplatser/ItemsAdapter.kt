package com.example.favoritplatser
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.favoritplatser.R

class ItemsAdapter(private val items: List<Item>, private val listener: (Item) -> Unit) : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvName: TextView = itemView.findViewById(R.id.tvItemNameView)
        val tvDescription: TextView = itemView.findViewById(R.id.tvItemDescriptionView)
        val tvLatitude: TextView = itemView.findViewById(R.id.tvItemLatitudeView)
        val tvLongitude: TextView = itemView.findViewById(R.id.tvItemLongitudeView)
        val tvCategory:TextView = itemView.findViewById(R.id.tvItemCategoryView)

        fun bind(item: Item, listener: (Item) -> Unit) {
            itemView.setOnClickListener { listener(item) }

            tvName.text = item.name ?: "N/A"
            tvDescription.text = item.description ?: "N/A"
            tvCategory.text = item.category ?: "N/A"
            tvLatitude.text = item.latitude?.toString() ?: "N/A"
            tvLongitude.text = item.longitude?.toString() ?: "N/A"

            // Ovdje dodajte postavljanje ostalih podataka o stavci
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ItemViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int = items.size
}


