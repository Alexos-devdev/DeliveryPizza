package com.example.deliverypizza.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.deliverypizza.PizzaDetailActivity;
import com.example.deliverypizza.R;
import com.example.deliverypizza.entity.Pizza;

import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    private List<Pizza> pizzaList;
    private Context context;

    public PizzaAdapter(List<Pizza> pizzaList, Context context) {
        this.pizzaList = pizzaList;
        this.context = context;
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pizza, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);

        holder.tvPizzaName.setText(pizza.getName());
        // Cargar la imagen de la pizza usando Glide
        Glide.with(context)
                .load(pizza.getImage())
                .into(holder.ivPizzaImage);

        // Configurar el click en la imagen para abrir PizzaDetailActivity
        holder.ivPizzaImage.setOnClickListener(v -> {
            Intent intent = new Intent(context, PizzaDetailActivity.class);
            intent.putExtra("pizza_name", pizza.getName());
            intent.putExtra("pizza_image", pizza.getImage());
            intent.putExtra("pizza_ingredients", String.join(", ", pizza.getIngredients()));
            intent.putExtra("pizza_prep_time", pizza.getPrepTimeMinutes());
            intent.putExtra("pizza_cook_time", pizza.getCookTimeMinutes());
            intent.putExtra("pizza_rating", pizza.getRating());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public static class PizzaViewHolder extends RecyclerView.ViewHolder {

        TextView tvPizzaName;
        ImageButton ivPizzaImage;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPizzaName = itemView.findViewById(R.id.tv_name);
            ivPizzaImage = itemView.findViewById(R.id.iv_image);
        }
    }
}
