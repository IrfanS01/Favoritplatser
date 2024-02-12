package com.example.favoritplatser
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.favoritplatser.R

class ItemsAdapter(val context: Context, private val items: List<Item>) : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {



    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.itemNameTextView)

        fun bind(item: Item) {

            // Postavite vrijednosti za ostale view-ove u itemView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var item=(items[position])
        holder.nameTextView.text = item.name


    }

    override fun getItemCount() = items.size
}
