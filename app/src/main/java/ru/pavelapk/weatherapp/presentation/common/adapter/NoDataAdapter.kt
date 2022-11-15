package ru.pavelapk.weatherapp.presentation.common.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class NoDataAdapter(
    @LayoutRes private val layout: Int
) : RecyclerView.Adapter<NoDataAdapter.NoDataViewHolder>() {

    abstract fun createViewHolder(view: View): NoDataViewHolder

    override fun getItemCount(): Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoDataViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)

        return createViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoDataViewHolder, position: Int) = Unit

    open class NoDataViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
