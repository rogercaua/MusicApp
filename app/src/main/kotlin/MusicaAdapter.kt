package com.musicapp

import Musica
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.musicapp.R

class MusicaAdapter(private val tocarMusica: (Musica) -> Unit) : ListAdapter<Musica, MusicaAdapter.MusicaViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_musica, parent, false)
        return MusicaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicaViewHolder, position: Int) {
        val musica = getItem(position)
        holder.bind(musica)
    }

    inner class MusicaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tituloTextView: TextView = itemView.findViewById(R.id.titulo)
        private val artistaTextView: TextView = itemView.findViewById(R.id.artista)

        fun bind(musica: Musica) {
            tituloTextView.text = musica.titulo
            artistaTextView.text = musica.artista
            itemView.setOnClickListener { tocarMusica(musica) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Musica>() {
        override fun areItemsTheSame(oldItem: Musica, newItem: Musica): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Musica, newItem: Musica): Boolean {
            return oldItem == newItem
        }
    }
}
