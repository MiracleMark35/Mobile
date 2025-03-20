package com.example.midterm_frost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<Product> cartItems;
    private Context context;

    public CartAdapter(List<Product> cartItems, Context context) {
        this.cartItems = cartItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = cartItems.get(position);
        holder.cartProductName.setText(product.getName());
        holder.cartProductPrice.setText(String.format("$%.2f", product.getPrice()));
        holder.cartProductImage.setImageResource(product.getImageResource());

        // Remove item from cart
        holder.removeFromCart.setOnClickListener(v -> {
            Cart.removeItem(product);
            cartItems.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cartProductImage;
        TextView cartProductName, cartProductPrice;
        Button removeFromCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartProductImage = itemView.findViewById(R.id.cart_product_image);
            cartProductName = itemView.findViewById(R.id.cart_product_name);
            cartProductPrice = itemView.findViewById(R.id.cart_product_price);
            removeFromCart = itemView.findViewById(R.id.remove_from_cart);
        }
    }
}
