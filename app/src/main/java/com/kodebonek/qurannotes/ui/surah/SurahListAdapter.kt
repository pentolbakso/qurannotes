package com.kodebonek.qurannotes.ui.surah

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kodebonek.qurannotes.R
import com.kodebonek.qurannotes.data.entity.Surah

class SurahListAdapter(private val onClick: (Surah) -> Unit): RecyclerView.Adapter<SurahListAdapter.SurahViewHolder>() {

    private val items = ArrayList<Surah>()

    fun reset() {
        items.clear()
        notifyDataSetChanged()
    }

    fun addItems(list: List<Surah>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_surah, parent, false)
        return SurahViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val edition = items[position]
        if (edition!=null)
            holder.bind(edition, onClick)
    }

    inner class SurahViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvNumber: TextView = view.findViewById(R.id.tvNumber)
        private val tvName: TextView = view.findViewById(R.id.tvName)
        private val tvTranslation: TextView = view.findViewById(R.id.tvTranslation)

        fun bind(surah: Surah?, onClick: (Surah) -> Unit) {
            tvNumber.text = surah?.number.toString()
            tvName.text = surah?.englishName.orEmpty()
            tvTranslation.text = surah?.englishNameTranslation.orEmpty()
            itemView.setOnClickListener {
                surah?.let(onClick)
            }
        }
    }
}