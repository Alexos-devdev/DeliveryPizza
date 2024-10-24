package com.example.deliverypizza;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deliverypizza.adapter.PizzaAdapter;
import com.example.deliverypizza.entity.Pizza;
import com.example.deliverypizza.entity.RecetaResponse;
import com.example.deliverypizza.service.ApiPizza;
import com.example.deliverypizza.util.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvPizzas;
    private PizzaAdapter pizzaAdapter;
    private ImageButton btnCarrito; // Botón para el carrito
    private TextView txtResultado;

    private List<Pizza> lstSalida;
    private ArrayList<Pizza> carrito = new ArrayList<Pizza>(); // Lista para acumular las pizzas seleccionadas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa las vistas
        rvPizzas = findViewById(R.id.rv_pizzas);
        rvPizzas.setLayoutManager(new LinearLayoutManager(this));
        txtResultado = findViewById(R.id.txtResultado);
        btnCarrito = findViewById(R.id.btnCarrito);

        // Llamada a la API para cargar datos de pizza
        loadPizzaData();

        // Configurar el botón del carrito
        btnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir a la interfaz de pago
                Intent intent = new Intent(MainActivity.this, PagoActivity.class);
                intent.putExtra("carrito", carrito); // Pasar el carrito
                startActivity(intent);
            }
        });
    }

    private void loadPizzaData() {
        ApiPizza apiPizza = ApiClient.getClient().create(ApiPizza.class);

        Call<RecetaResponse> call = apiPizza.getPizza();
        call.enqueue(new Callback<RecetaResponse>() {
            @Override
            public void onResponse(Call<RecetaResponse> call, Response<RecetaResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pizza> pizzaList = response.body().getRecipes();
                    pizzaAdapter = new PizzaAdapter(pizzaList, MainActivity.this);
                    rvPizzas.setAdapter(pizzaAdapter);
                    lstSalida = pizzaList; // Guardar la lista de pizzas para referencia posterior
                } else {
                    Toast.makeText(MainActivity.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RecetaResponse> call, Throwable t) {
                Log.e("API Error", t.getMessage());
                Toast.makeText(MainActivity.this, "Fallo al cargar datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
