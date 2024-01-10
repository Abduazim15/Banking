package uz.gita.lesson40.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.lesson40.R
import uz.gita.lesson40.domain.entity.FastPayModel


class FastAdapter : ListAdapter<FastPayModel, FastViewHolder>(CharacterComparator) {
    private var onClickListener: ((Int) -> Unit)? = null
    fun setOnClickClickListener(clickListener: (Int) -> Unit) {
        onClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FastViewHolder {
        val view = LayoutInflater
            .from(parent.context).inflate(R.layout.fast_item, parent, false)
        return FastViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: FastViewHolder, position: Int) {
        val history = getItem(position)
        history?.let { holder.bind(it) }
    }

    object CharacterComparator : DiffUtil.ItemCallback<FastPayModel>() {
        override fun areItemsTheSame(oldItem: FastPayModel, newItem: FastPayModel): Boolean {
            return oldItem.number == newItem.number
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: FastPayModel, newItem: FastPayModel): Boolean {
            return oldItem == newItem
        }
    }
}

class FastViewHolder(val view: View, val onItemClickListener: ((Int) -> Unit)?) :
    RecyclerView.ViewHolder(view) {
    private val layout: LinearLayout = view.findViewById(R.id.layout)
    private val number: TextView = view.findViewById(R.id.number)
    private val pay: TextView = view.findViewById(R.id.pay)

    fun bind(history: FastPayModel) {
        pay.setOnClickListener {
            onItemClickListener?.invoke(bindingAdapterPosition)
        }
        number.text = history.number


    }
}
