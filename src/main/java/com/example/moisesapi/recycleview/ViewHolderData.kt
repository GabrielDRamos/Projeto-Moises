package com.example.moisesapi.recycleview

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moisesapi.R
import com.example.moisesapi.activities.MainActivity
import com.example.moisesapi.fragments.FragmentCallback

class ViewHolderData (itemView: View, var recycleViewCallback: RecycleViewCallback) : RecyclerView.ViewHolder(itemView) {


        fun bind(data: String){
                val textData: TextView = itemView.findViewById(R.id.viewHolderData)
                textData.text = data.replace("\n", "")
                itemView.setOnClickListener{
                    recycleViewCallback.callback(data)
              }
        }
}
