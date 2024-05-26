package com.mmcs.memeapiclient.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mmcs.memeapiclient.R
import com.mmcs.memeapiclient.viewmodel.MemeApiClientViewModel
import com.squareup.picasso.Picasso

class HistoryRecyclerAdapter(private val data: MemeApiClientViewModel):
    RecyclerView.Adapter<HistoryRecyclerAdapter.HistoryViewHolder> () {
    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageItem: ImageView = itemView.findViewById(R.id.imageView)
        val imageName: TextView = itemView.findViewById(R.id.imageName)
        val removeListener = itemView.findViewById<Button>(R.id.deleteButton).setOnClickListener{
            data.deleteImage(adapterPosition)
            data.adapterPosition = -1
            notifyItemRemoved(adapterPosition)
        }
        val item = itemView.findViewById<LinearLayout>(R.id.recyclerItem).setOnClickListener{
            data.adapterPosition = adapterPosition
            val navController = itemView.findNavController()
            navController.navigate(R.id.startFragment)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return HistoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.getImageCount()
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val imageData = data.getImage(position)
        var url: String? = imageData.url
        if (url == "")
            url = null

        Picasso.get().load(url).placeholder(R.drawable.ic_launcher_foreground).into(holder.imageItem)
        holder.imageName.text = imageData.name
    }
}