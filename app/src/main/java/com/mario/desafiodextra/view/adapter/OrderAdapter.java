package com.mario.desafiodextra.view.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mario.desafiodextra.R;
import com.mario.desafiodextra.model.entity.Pedido;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mario.desafiodextra.util.Generics.formatCurrency;
import static com.mario.desafiodextra.util.Generics.makeIngredients;

/**
 * @author Mario Feles dos Santos Junior
 * @email mario_feles@live.com
 * @date 17/05/17 15:45
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    private List<Pedido> orders;

    public OrderAdapter(List<Pedido> orders) {
        this.orders = orders;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_order, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Pedido order = this.orders.get(position);

        if(order.getSandwich().getImage() != null) {
            Uri uri = Uri.parse(order.getSandwich().getImage());
            holder.imv_order_sandwich.setImageURI(uri);
        }

        holder.tv_order_sandwich_name.setText(order.getSandwich().getName());
        holder.tv_order_sandwich_price.setText(formatCurrency(order.getSandwich().getPriceTotal()));
        holder.tv_order_sandwich_ingredients.setText(makeIngredients(order.getSandwich().getIngredients()));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imv_order_sandwich)SimpleDraweeView imv_order_sandwich;
        @BindView(R.id.tv_row_order_sandwich_name)TextView tv_order_sandwich_name;
        @BindView(R.id.tv_row_order_sandwich_price)TextView  tv_order_sandwich_price;
        @BindView(R.id.tv_row_order_sandwich_ingredients)TextView  tv_order_sandwich_ingredients;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}


