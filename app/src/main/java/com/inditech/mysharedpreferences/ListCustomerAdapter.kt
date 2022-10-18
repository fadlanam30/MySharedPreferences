package com.inditech.mysharedpreferences

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inditech.mysharedpreferences.databinding.ItemCustomerLayoutBinding

class ListCustomerAdapter(private val listItem: ArrayList<Customer>) : RecyclerView.Adapter<ListCustomerAdapter.ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(var binding: ItemCustomerLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCustomerLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val customerData = listItem[position]

        holder.binding.tvNameItemCustomer.text = customerData.name
        holder.binding.tvDomicileItemCustomer.text = "Domisili : ${customerData.domicile}"
        holder.binding.tvGenderItemCustomer.text = "Gender : ${customerData.gender}"
        holder.binding.root.setOnClickListener { onItemClickCallback.onItemClicked(listItem[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listItem.size

    interface OnItemClickCallback {
        fun onItemClicked(customer: Customer)
    }

}