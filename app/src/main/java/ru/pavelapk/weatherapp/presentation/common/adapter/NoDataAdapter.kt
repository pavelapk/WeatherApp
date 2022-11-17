package ru.pavelapk.weatherapp.presentation.common.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class NoDataAdapter(
    @LayoutRes private val layout: Int
) : RecyclerView.Adapter<NoDataAdapter.NoDataViewHolder>(), VisibilityControl {

    override var isVisible: Boolean = true
        set(value) {
            if (field == value) return
            field = value
            onDataChanged()
        }

    abstract fun createViewHolder(view: View): NoDataViewHolder

    override fun getItemCount(): Int = if (isVisible) 1 else 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoDataViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)

        return createViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoDataViewHolder, position: Int) = Unit

    private fun onDataChanged() {
        /* An adapter always contains at most one item.
         notifyDataSetChanged used to recalculate the number of elements for situations
         where the adapter you want to hide
         */
        if (isVisible) notifyItemInserted(0) else notifyItemRemoved(0)
    }

    open class NoDataViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
