package ru.pavelapk.weatherapp.presentation.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.pavelapk.weatherapp.R
import ru.pavelapk.weatherapp.databinding.ItemLocationBinding
import ru.pavelapk.weatherapp.domain.location.model.Location
import ru.pavelapk.weatherapp.presentation.common.adapter.createDiff

class SearchAdapter(
    private val listener: SearchListener
) : ListAdapter<Location, SearchAdapter.LocationViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_location, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LocationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding by viewBinding(ItemLocationBinding::bind)

        init {
            binding.root.setOnClickListener {
                listener.onLocationClick(getItem(bindingAdapterPosition))
            }
        }

        fun bind(data: Location) = with(binding) {
            textViewName.text = data.name
            textViewRegionName.text = data.regionName
        }
    }

    fun interface SearchListener {
        fun onLocationClick(location: Location)
    }

    private companion object {
        val DIFF = createDiff<Location> { oldItem, newItem ->
            oldItem.coordinates == newItem.coordinates
        }
    }
}
