package com.example.deliverypizza;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deliverypizza.adapter.CompraAdapter;
import com.example.deliverypizza.adapter.PizzaAdapter;
import com.example.deliverypizza.entity.Carrito;
import com.example.deliverypizza.entity.Pizza;

import java.util.List;

public class PagoActivity extends AppCompatActivity {

    private RecyclerView recyclerPizzas;
    private CompraAdapter pizzaAdapter;
    private TextView txtTotal;
    private Carrito carrito;  // Variable para el carrito

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        // Inicializar vistas
        recyclerPizzas = findViewById(R.id.recyclerPizzas);
        recyclerPizzas.setLayoutManager(new LinearLayoutManager(this));
        txtTotal = findViewById(R.id.txtTotal);

        // Obtener el carrito desde el intent
        if (getIntent().hasExtra("carrito")) {
            carrito = (Carrito) getIntent().getSerializableExtra("carrito");

            if (carrito != null) {
                List<Pizza> pizzaList = carrito.getPizzas();
                // Configurar el adapter con la lista de pizzas en el carrito
                pizzaAdapter = new CompraAdapter(pizzaList, this);
                recyclerPizzas.setAdapter(pizzaAdapter);

                // Calcular y mostrar el total del carrito
                calcularTotal(pizzaList);
            } else {
                Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Error al recibir el carrito", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcularTotal(List<Pizza> pizzas) {
        double total = 0;
        for (Pizza pizza : pizzas) {
            total += pizza.getCookTimeMinutes();  // Usar el tiempo de cocción como precio
        }
        txtTotal.setText("Total: S/ " + total);
    }
}
