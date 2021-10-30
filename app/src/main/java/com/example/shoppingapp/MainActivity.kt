package com.example.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private lateinit var productAdapter: ProductAdapter

    private var products= listOf<Product>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Paper.init(this)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        apiService= APIConfig.getRetrofitClient(this).create(ApiService::class.java)

        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.purple_200))
        swipeRefreshLayout.isRefreshing= true

        products_recyclerview.layoutManager= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        cart_size.text= ShoppingCart.getShoppingCartSize().toString()
        getProducts()
    }


    fun getProducts(){
        apiService.getProducts().enqueue(object : retrofit2.Callback<List<Product>>{
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                print(t.message)
                t.message?.let { Log.d("Data error", it) }
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                swipeRefreshLayout.isRefreshing= false
                products= response.body()!!

                productAdapter= ProductAdapter(this@MainActivity,products)

                products_recyclerview.adapter= productAdapter
                productAdapter.notifyDataSetChanged()
            }
        })
    }
}