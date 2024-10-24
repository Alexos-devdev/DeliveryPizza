package com.example.deliverypizza.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carrito implements Serializable {
    private List<Pizza> pizzas;

    public Carrito() {
        this.pizzas = new ArrayList<>();
    }

    public void agregarPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public List<Pizza> getPizzas() {
        return pizzas; // Asegúrate de que esto existe
    }

    public double getTotal() {
        double total = 0.0;
        for (Pizza pizza : pizzas) {
            total += pizza.getCookTimeMinutes(); // Supongamos que el cookTime es el precio
        }
        return total;
    }
}
