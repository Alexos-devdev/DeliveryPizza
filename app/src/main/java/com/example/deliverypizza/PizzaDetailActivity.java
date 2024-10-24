package com.example.deliverypizza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.deliverypizza.entity.Carrito;
import com.example.deliverypizza.entity.Pizza;

import java.util.Arrays;

public class PizzaDetailActivity extends AppCompatActivity {

    private ImageView ivPizzaDetailImage;
    private TextView tvPizzaDetailName, tvPizzaDetailIngredients, tvPizzaDetailPrepTime, tvPizzaDetailCookTime, tvPizzaDetailRating, tvPizzaQuantity;
    private Button btnComprar, btnIncrease, btnDecrease;
    private ImageButton btnCarrito;  // Declarar el botón carrito
    private Pizza selectedPizza;
    private Carrito carrito;
    private int pizzaQuantity = 1;  // Variable para la cantidad seleccionada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detail);

        // Recuperar el carrito de la actividad anterior o crear uno nuevo
        if (getIntent().hasExtra("carrito")) {
            carrito = (Carrito) getIntent().getSerializableExtra("carrito");
        } else {
            carrito = new Carrito();
        }

        // Inicializar las vistas
        ivPizzaDetailImage = findViewById(R.id.iv_pizza_detail_image);
        tvPizzaDetailName = findViewById(R.id.tv_pizza_detail_name);
        tvPizzaDetailIngredients = findViewById(R.id.tv_pizza_detail_ingredients);
        tvPizzaDetailPrepTime = findViewById(R.id.tv_pizza_detail_prep_time);
        tvPizzaDetailCookTime = findViewById(R.id.tv_pizza_detail_cook_time);
        tvPizzaDetailRating = findViewById(R.id.tv_pizza_detail_rating);
        tvPizzaQuantity = findViewById(R.id.tvPizzaQuantity);
        btnComprar = findViewById(R.id.btnComprar);
        btnIncrease = findViewById(R.id.btnIncrease);
        btnDecrease = findViewById(R.id.btnDecrease);
        btnCarrito = findViewById(R.id.btnCarrito);  // Inicializar el botón carrito

        // Obtener datos del Intent
        String name = getIntent().getStringExtra("pizza_name");
        String image = getIntent().getStringExtra("pizza_image");
        String ingredients = getIntent().getStringExtra("pizza_ingredients");
        int prepTime = getIntent().getIntExtra("pizza_prep_time", 0);
        int cookTime = getIntent().getIntExtra("pizza_cook_time", 0);
        double rating = getIntent().getDoubleExtra("pizza_rating", 0);

        // Crear objeto Pizza
        selectedPizza = new Pizza();
        selectedPizza.setName(name);
        selectedPizza.setImage(image);
        selectedPizza.setIngredients(ingredients);
        selectedPizza.setPrepTimeMinutes(prepTime);
        selectedPizza.setCookTimeMinutes(cookTime);
        selectedPizza.setRating(rating);

        // Configurar vistas
        tvPizzaDetailName.setText(name);
        tvPizzaDetailIngredients.setText("Ingredientes: " + ingredients);
        tvPizzaDetailPrepTime.setText("Tiempo de preparación: " + prepTime + " minutos.");
        tvPizzaDetailCookTime.setText("Precio: S/ " + cookTime);
        tvPizzaDetailRating.setText("Puntuación: " + rating);

        // Cargar imagen con Glide
        Glide.with(this).load(image).into(ivPizzaDetailImage);

        // Configurar botones de incremento y decremento
        btnIncrease.setOnClickListener(v -> {
            pizzaQuantity++;
            tvPizzaQuantity.setText(String.valueOf(pizzaQuantity));
        });

        btnDecrease.setOnClickListener(v -> {
            if (pizzaQuantity > 1) {
                pizzaQuantity--;
                tvPizzaQuantity.setText(String.valueOf(pizzaQuantity));
            }
        });

        // Configurar botón de comprar
        btnComprar.setOnClickListener(v -> {
            addPizzaToCart(selectedPizza, pizzaQuantity);
        });

        // Configurar evento del botón carrito para ir a PagoActivity
        btnCarrito.setOnClickListener(v -> {
            Intent intent = new Intent(PizzaDetailActivity.this, PagoActivity.class);
            intent.putExtra("carrito", carrito);  // Pasar el carrito completo
            startActivity(intent);
        });
    }

    private void addPizzaToCart(Pizza pizza, int quantity) {
        if (carrito != null && pizza != null) {
            for (int i = 0; i < quantity; i++) {
                carrito.agregarPizza(pizza);  // Agregar varias pizzas según la cantidad
            }

            Toast.makeText(this, quantity + " pizzas añadidas al carrito: " + pizza.getName() +
                    "\nTotal hasta ahora: S/ " + carrito.getTotal(), Toast.LENGTH_SHORT).show();

            // Crear Intent para ir a PagoActivity
            Intent intent = new Intent(PizzaDetailActivity.this, PagoActivity.class);
            intent.putExtra("carrito", carrito);  // Pasar el carrito completo
            startActivity(intent);
        } else {
            Toast.makeText(this, "Error: Carrito o pizza no válidos", Toast.LENGTH_SHORT).show();
        }
    }
}
