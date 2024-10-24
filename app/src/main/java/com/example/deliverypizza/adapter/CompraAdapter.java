package com.example.deliverypizza.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.deliverypizza.PizzaDetailActivity;
import com.example.deliverypizza.R;
import com.example.deliverypizza.entity.Pizza;

import java.util.List;

public class CompraAdapter extends RecyclerView.Adapter<CompraAdapter.PizzaViewHolder>{
    private List<Pizza> pizzaList;
    private Context context;

    public CompraAdapter(List<Pizza> pizzaList, Context context) {
        this.pizzaList = pizzaList;
        this.context = context;
    }
    @NonNull
    @Override
    public CompraAdapter.PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrito, parent, false);
        return new CompraAdapter.PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);

        // Establecer los datos en el view holder
        holder.tvName.setText(pizza.getName());
        Glide.with(context).load(pizza.getImage()).into(holder.ivImage);

        // Configurar clic para cada pizza
        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un intent para ir a PizzaDetailActivity
                Intent intent = new Intent(context, PizzaDetailActivity.class);

                // Pasar los datos de la pizza seleccionada a la nueva actividad
                intent.putExtra("pizza_name", pizza.getName());
                intent.putExtra("pizza_image", pizza.getImage());
                intent.putExtra("pizza_cook_time", pizza.getCookTimeMinutes());


                // Iniciar la nueva actividad
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public class PizzaViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivImage;
        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.txtNombre);
            ivImage = itemView.findViewById(R.id.imgPizza);
        }
    }
}
