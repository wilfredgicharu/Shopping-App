package com.example.shoppingapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.product_row_item.view.*


//this adapter is responsible to display products

class ProductAdapter(var context: Context, var products: List<Product> = arrayListOf()):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.product_row_item, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ProductAdapter.ViewHolder, position: Int) {
        viewHolder.bindProduct(products[position])

        (context as MainActivity).coordinator
    }

    override fun getItemCount(): Int =products.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bindProduct(product: Product){
            itemView.product_name.text = product.name
            itemView.product_price.text = "$${product.price.toString()}"
            Picasso.get().load(product.photos[0].filename).fit().into(itemView.product_image)


       itemView.addToCart.setOnClickListener { view->
           val item= CartItem(product)
           ShoppingCart.addItem(item)

           Snackbar.make(
               (itemView.context as MainActivity).coordinator, "${product.name} added to your cart ",
               Snackbar.LENGTH_LONG
           ).show()
       }

            itemView.removeItem.setOnClickListener { view ->
                val item = CartItem(product)
                ShoppingCart.removeItem(item, itemView.context)

                Snackbar.make(
                    (itemView.context as MainActivity).coordinator, "${product.name} removed from your cart", Snackbar.LENGTH_LONG
                ).show()
            }
        }

    }

}