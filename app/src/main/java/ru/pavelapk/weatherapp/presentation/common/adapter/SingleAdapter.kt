package ru.pavelapk.weatherapp.presentation.common.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * It always has 1 ViewHolder.
 * Use for ViewHolders like as Footer, Header and etc. with data set
 */
abstract class SingleAdapter<T : Any>(
    @LayoutRes private val layoutRes: Int
) : RecyclerView.Adapter<SingleAdapter.SingleViewHolder<T>>() {

    protected var data: T? = null
        private set

    abstract fun createViewHolder(view: View): SingleViewHolder<T>

    fun submitData(data: T) {
        if (this.data == data) return
        onDataChanged(data)
    }

    override fun getItemCount(): Int = if (data == null) 0 else 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return createViewHolder(view)
    }

    override fun onBindViewHolder(holder: SingleViewHolder<T>, position: Int) {
        data?.let { holder.bind(it) }
    }

    private fun onDataChanged(newData: T?) {
        /* An adapter always contains at most one item.
         notifyDataSetChanged used to recalculate the number of elements for situations
         where the adapter you want to hide
         */
        when {
            data == null -> notifyItemInserted(0)
            newData != null -> notifyItemChanged(0)
            else -> notifyItemRemoved(0)
        }

        data = newData
    }

    abstract class SingleViewHolder<T : Any>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(data: T)
    }
}
