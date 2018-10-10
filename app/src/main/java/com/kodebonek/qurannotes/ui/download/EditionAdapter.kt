package com.kodebonek.qurannotes.ui.download

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kodebonek.qurannotes.R
import com.kodebonek.qurannotes.data.entity.Edition


class EditionAdapter(private val onClick: (Edition) -> Unit): RecyclerView.Adapter<EditionAdapter.EditionViewHolder>() {

    private var selectedPos : Int = RecyclerView.NO_POSITION

    private val items = ArrayList<Edition>()

    fun reset() {
        selectedPos = RecyclerView.NO_POSITION
        items.clear()
        notifyDataSetChanged()
    }

    fun addItems(list: List<Edition>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditionViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_edition, parent, false)
        return EditionViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: EditionViewHolder, position: Int) {
        val edition = items[position]
        if (edition!=null)
            holder.bind(edition, onClick)
    }

    inner class EditionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvName: TextView = view.findViewById(R.id.tvName)
        private val tvDescription: TextView = view.findViewById(R.id.tvDescription)

        fun bind(edition: Edition?, onClick: (Edition) -> Unit) {
            tvName.text = edition?.englishName.orEmpty()
            tvDescription.text = "${edition?.language.orEmpty()} / ${edition?.type.orEmpty()} / ${edition?.format.orEmpty()}"
            itemView.setOnClickListener {
                if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener

                notifyItemChanged(selectedPos)
                selectedPos = adapterPosition
                notifyItemChanged(selectedPos)

                edition?.let(onClick)
            }

            itemView.setBackgroundColor(if (selectedPos == adapterPosition)
                itemView.resources.getColor(R.color.green)
                else itemView.resources.getColor(R.color.white)
            )
        }
    }

}

