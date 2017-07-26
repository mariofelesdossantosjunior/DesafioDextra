package com.mario.desafiodextra.view.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mario.desafiodextra.R;
import com.mario.desafiodextra.model.entity.Lanche;

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

public class LunchAdapter extends RecyclerView.Adapter<LunchAdapter.MyViewHolder> {
    private static ClickList clickList;
    private List<Lanche> lunchs;

    public LunchAdapter(List<Lanche> lunchs, ClickList clickList) {
        this.lunchs = lunchs;
        this.clickList = clickList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_lunch, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Lanche lunch = this.lunchs.get(position);

        if(lunch.getImage() != null) {
            Uri uri = Uri.parse(lunch.getImage());
            holder.imv_lunch.setImageURI(uri);
        }

        holder.tv_lunch_name.setText(lunch.getName());
        holder.tv_lunch_price.setText(formatCurrency(lunch.getPriceTotal()));
        holder.tv_lunch_ingredients.setText(makeIngredients(lunch.getIngredients()));
    }

    @Override
    public int getItemCount() {
        return lunchs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imv_lunch)SimpleDraweeView imv_lunch;
        @BindView(R.id.tv_row_lunch_name)TextView tv_lunch_name;
        @BindView(R.id.tv_row_lunch_price)TextView tv_lunch_price;
        @BindView(R.id.tv_row_lunch_ingredients)TextView tv_lunch_ingredients;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(v -> clickList.onClickItem(lunchs.get(getLayoutPosition())));
        }
    }
}


