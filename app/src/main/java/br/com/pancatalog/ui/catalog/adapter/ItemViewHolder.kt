package br.com.pancatalog.ui.catalog.adapter

import android.R.attr.onClick
import androidx.recyclerview.widget.RecyclerView
import br.com.pancatalog.databinding.RowItemBinding
import br.com.pancatalog.domain.model.Item

class ItemViewHolder(
    private val binding: RowItemBinding,
    private val onClick: (Item) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item) {
        binding.title.text = item.title
        binding.subtitle.text = item.subtitle
        binding.root.setOnClickListener { onClick(item) }
    }
}
