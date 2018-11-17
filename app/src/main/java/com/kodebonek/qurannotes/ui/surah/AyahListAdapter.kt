package com.kodebonek.qurannotes.ui.surah

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kodebonek.qurannotes.R
import com.kodebonek.qurannotes.data.entity.Ayah

class AyahListAdapter (private val onClick: (Ayah) -> Unit): RecyclerView.Adapter<AyahListAdapter.AyahViewHolder>() {

    private val items = ArrayList<Ayah>()

    fun reset() {
        items.clear()
        notifyDataSetChanged()
    }

    fun addItems(list: List<Ayah>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyahViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ayah, parent, false)
        return AyahViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AyahViewHolder, position: Int) {
        val edition = items[position]
        if (edition!=null)
            holder.bind(edition, onClick)
    }
    inner class AyahViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTranslation: TextView = view.findViewById(R.id.tvTranslation)
        private val tvArabic: TextView = view.findViewById(R.id.tvArabic)

        fun bind(ayah: Ayah?, onClick: (Ayah) -> Unit) {
            tvTranslation.text = "[${ayah?.numberInSurah}]  ${ayah?.text.orEmpty()}"
            //remove basmallah header on first ayah , except for Al-Fatihah & At-Taubah
            if (ayah?.numberInSurah == 1 && ayah?.surahNumber!= 1 && ayah?.surahNumber != 9) {
                val arabicWithoutBismillah = ayah?.arabic?.substring(39)
                tvArabic.text = arabicWithoutBismillah
            } else
                tvArabic.text = ayah?.arabic

            itemView.setOnClickListener {
                ayah?.let(onClick)
            }
        }
    }
}