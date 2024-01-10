package uz.gita.lesson40.presentation.adapter

  import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.lesson40.R
import uz.gita.lesson40.domain.entity.Type


class PaymentAdapter : ListAdapter<Type, PaymentViewHolder>(CharacterComparator) {
    private var onClickListener: ((Int) -> Unit)? = null
    fun setOnClickClickListener(clickListener: (Int) -> Unit) {
        onClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val view = LayoutInflater
            .from(parent.context).inflate(R.layout.payment_item, parent, false)
        return PaymentViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val character = getItem(position)
        character?.let { holder.bind(it) }
    }

    object CharacterComparator : DiffUtil.ItemCallback<Type>() {
        override fun areItemsTheSame(oldItem: Type, newItem: Type): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Type, newItem: Type): Boolean {
            return oldItem == newItem
        }
    }
}

class PaymentViewHolder(val view: View, val onItemClickListener: ((Int) -> Unit)?) :
    RecyclerView.ViewHolder(view) {
    val layout: CardView = view.findViewById(R.id.card_view)
    private val image: ImageView = view.findViewById(R.id.image)

    fun bind(service: Type) {
        layout.setOnClickListener {
            onItemClickListener?.invoke(bindingAdapterPosition)
        }


        if (service.id<0){
            image.setImageResource(service.category_id)
        }else if (service.id==3){
            image.setImageResource(R.drawable.img_17)
        }
        else{
            Glide.with(image)
                .load(service.icon_url)
                .into(image)
        }
    }

}
