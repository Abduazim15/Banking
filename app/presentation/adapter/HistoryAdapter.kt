package uz.gita.lesson40.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.lesson40.R
import uz.gita.lesson40.domain.entity.DataX


class HistoryAdapter : ListAdapter<DataX, HistoryViewHolder>(CharacterComparator) {
    private var onClickListener: ((Int) -> Unit)? = null
    fun setOnClickClickListener(clickListener: (Int) -> Unit) {
        onClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater
            .from(parent.context).inflate(R.layout.history_item, parent, false)
        return HistoryViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history = getItem(position)
        history?.let { holder.bind(it) }
    }

    object CharacterComparator : DiffUtil.ItemCallback<DataX>() {
        override fun areItemsTheSame(oldItem: DataX, newItem: DataX): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: DataX, newItem: DataX): Boolean {
            return oldItem == newItem
        }
    }
}

class HistoryViewHolder(val view: View, val onItemClickListener: ((Int) -> Unit)?) :
    RecyclerView.ViewHolder(view) {
    private val layout: LinearLayout = view.findViewById(R.id.layout)
    private val image: ImageView = view.findViewById(R.id.type_img)
    private val pan: TextView = view.findViewById(R.id.pan)
    private val time: TextView = view.findViewById(R.id.time)
    private val amount: TextView = view.findViewById(R.id.amount)

    fun bind(history: DataX) {
        layout.setOnClickListener {
            onItemClickListener?.invoke(bindingAdapterPosition)
        }
        pan.text = history.card.pan
        amount.text = history.amount.toString()

    }
}
