package uz.gita.lesson40.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.lesson40.R
import uz.gita.lesson40.domain.entity.getResponse.Data
import java.text.NumberFormat
import java.util.Locale


class CardAdapter : ListAdapter<Data, CardViewHolder>(CharacterComparator) {
    private var onClickListener: ((Int) -> Unit)? = null
    fun setOnClickClickListener(clickListener: (Int) -> Unit) {
        onClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return CardViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val character = getItem(position)
        character?.let { holder.bind(it) }
    }

    object CharacterComparator : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }
}

class CardViewHolder(val view: View, val onItemClickListener: ((Int) -> Unit)?) :
    RecyclerView.ViewHolder(view) {
    val layout = view.findViewById<CardView>(R.id.layout)
    private val name: TextView = view.findViewById(R.id.balance)
    private val back: ImageView = view.findViewById(R.id.back_card_item)

    fun bind(card: Data) {
        val amount = roundToTwoDecimalPlaces(card.amount.toDouble())
        val summ = formatNumber(amount.toInt())
        if (card.theme == null || card.theme == 1)
            back.setImageResource(R.drawable.card_back)
        else back.setImageResource(card.theme)
        name.setText("$summ  UZS")
        layout.setOnClickListener {
            onItemClickListener?.invoke(bindingAdapterPosition)
        }
    }

    fun roundToTwoDecimalPlaces(number: Double): Double {
        val integerPart = number.toInt() // Get the integer part
        val decimalPart =
            ((number * 100) % 100).toInt() // Get the two digits after the decimal point
        val roundedNumber =
            integerPart + decimalPart / 100.00 // Combine the integer and decimal parts

        return roundedNumber
    }

    fun formatNumber(number: Int): String? {
        val numberFormat: NumberFormat = NumberFormat.getNumberInstance(Locale.US)
        return numberFormat.format(number)
    }
}