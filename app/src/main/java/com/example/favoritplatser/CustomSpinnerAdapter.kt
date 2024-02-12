package com.example.favoritplatser

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

class CustomSpinnerAdapter(context: Context, resource: Int, objects: List<Any>) : ArrayAdapter<Any>(context, resource, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Implementacija za prilagođeni view koji se prikazuje u samom spinneru.
        return super.getView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Implementacija za prilagođeni view koji se prikazuje u dropdown listi.
        return super.getView(position, convertView, parent)
    }
}
