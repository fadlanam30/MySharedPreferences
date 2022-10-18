package com.inditech.mysharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inditech.mysharedpreferences.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val customer = intent.getParcelableExtra<Customer>(MainActivity.EXTRA_CUSTOMER)

        if (customer != null){
            binding.apply {
                tvCustomerName.text = "Nama : ${customer.name}"
                tvCustomerDomicile.text = "Domisili : ${customer.domicile}"
                tvCustomerGender.text = "Gender : ${customer.gender}"
            }
        }
    }
}