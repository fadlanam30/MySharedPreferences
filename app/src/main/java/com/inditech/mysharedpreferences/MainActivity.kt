package com.inditech.mysharedpreferences

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.inditech.mysharedpreferences.databinding.ActivityMainBinding
import com.inditech.mysharedpreferences.helper.PrefHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var prefHelper: PrefHelper

    private val list = ArrayList<Customer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefHelper = PrefHelper(this)

        list.addAll(listCustomers)


        showRecyclerList()

//        val email = intent.getStringExtra(LoginActivity.EXTRA_EMAIL_USER)
//        val password = intent.getStringExtra(LoginActivity.EXTRA_PASSWORD_USER)

//        val email = prefHelper.getString(Constant.PREF_EMAIL)
//        val password = prefHelper.getString(Constant.PREF_PASSWORD)

//        binding.tvEmail.text = "Email : $email"
//        binding.tvPassword.text = "Password : $password"

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this).setTitle("Peringatan!")
                .setMessage("Apakah anda ingin keluar dari akun ?")
                .setCancelable(true)
                .setPositiveButton("Ya") { _, _ ->
                    prefHelper.clear()
                    Toast.makeText(this, "Keluar", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                .setNegativeButton("Tidak") { dialogInterface, _ ->
                    dialogInterface.cancel()
                }
                .show()
        }
    }

    private fun showRecyclerList() {
        binding.rvCustomers.setHasFixedSize(true)
        binding.rvCustomers.layoutManager = LinearLayoutManager(this)
        val listCustomerAdapter = ListCustomerAdapter(list)
        binding.rvCustomers.adapter = listCustomerAdapter

        listCustomerAdapter.setOnItemClickCallback(object :
            ListCustomerAdapter.OnItemClickCallback {
            override fun onItemClicked(customer: Customer) {
                Toast.makeText(this@MainActivity, customer.name, Toast.LENGTH_SHORT).show()
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra(EXTRA_CUSTOMER, customer)
                startActivity(intentToDetail)
            }

        })

    }

    private val listCustomers: ArrayList<Customer>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataDomicile = resources.getStringArray(R.array.data_domicile)
            val dataGender = resources.getStringArray(R.array.data_gender)
            val listCustomer = ArrayList<Customer>()
            for (i in dataName.indices) {
                val customer = Customer(dataName[i], dataDomicile[i], dataGender[i])
                listCustomer.add(customer)
            }
            return listCustomer
        }

    companion object {
        const val EXTRA_CUSTOMER = "EXTRA_CUSTOMER"
    }

}