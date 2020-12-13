package com.example.kingsofkode.BoardGame

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kingsofkode.R
import com.example.kingsofkode.models.Card
import com.example.kingsofkode.utils
import kotlinx.android.synthetic.main.card_list_item.view.*
import java.util.ArrayList

class CardInHandRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = ArrayList<Card>()

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return CardViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is CardViewHolder -> {
                holder.bind(items[position], context)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addCard(card: Card) {
        items.add(card)
        this.notifyDataSetChanged()
    }

    fun removeCard(name: String) {
        items = items.filter { it.name !=  name } as ArrayList<Card>
        this.notifyDataSetChanged()
    }

    class CardViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.image
        val nameView = itemView.name
        val priceView = itemView.price

        fun bind(card: Card, context: Context) {
            imageView.setImageDrawable(
                utils.getDrawableFromString(
                    context,
                    card.name,
                    context.resources
                )
            )
            nameView.text = card.name
            priceView.text = card.price.toString()
        }
    }
}