package com.example.moisesapi.recycleview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moisesapi.R

class RecycleViewAdapaterData(var words:MutableList<String>, var context: Context, var recycleViewCallback: RecycleViewCallback, ) : RecyclerView.Adapter<ViewHolderData>() {
    var listanova: MutableList<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderData {

            val view = LayoutInflater.from(context).inflate(R.layout.fragment_view_holder, parent, false)
            return ViewHolderData(view, recycleViewCallback)
        }

        override fun getItemCount(): Int {
            return words.size
        }

        override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
            val data = words[position]
            holder.bind(data)
        }

        @SuppressLint("NotifyDataSetChanged")
        fun pegaLista (list: MutableList<String>) {
            words = list
            notifyDataSetChanged()
        }
    }
