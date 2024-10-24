package com.example.deliverypizza.entity;

import java.util.Collections;
import java.util.List;

public class Pizza {
    private int id;
    private String name;
    private List<String> ingredients;
    private int prepTimeMinutes;
    private int cookTimeMinutes;
    private String image;
    private double rating;
    private int userId;   // Cambiar "Portada" a "image" para la URL de la imagen

    public Pizza(int id, String name, List<String> ingredients, int prepTimeMinutes, int cookTimeMinutes, String image, double rating, int userId) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.prepTimeMinutes = prepTimeMinutes;
        this.cookTimeMinutes = cookTimeMinutes;
        this.image = image;
        this.rating = rating;
        this.userId = userId;
    }

    public Pizza() {

    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = Collections.singletonList(ingredients);
    }

    public int getCookTimeMinutes() {
        return cookTimeMinutes;
    }

    public void setCookTimeMinutes(int cookTimeMinutes) {
        this.cookTimeMinutes = cookTimeMinutes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrepTimeMinutes() {
        return prepTimeMinutes;
    }

    public void setPrepTimeMinutes(int prepTimeMinutes) {
        this.prepTimeMinutes = prepTimeMinutes;
    }
}
