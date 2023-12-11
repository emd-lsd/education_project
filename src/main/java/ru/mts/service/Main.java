package ru.mts.service;

import ru.mts.entity.Products;

public class Main {
    public static void main(String[] args) {
        // Создание новых объектов товаров
        Products prod1 = new Products(5, 200, 0.75);
        Products prod2 = new Products(30, 33, 42.575);
        Products prod3 = new Products(3, 10, 59.1);
        //Products prod4 = new Products(5, 15, 101);

        // Расчёт общей суммы товаров для объектов
        Products.calculateTotal(prod1);
        Products.calculateTotal(prod2);
        Products.calculateTotal(prod3);
        //Products.calculateTotal(prod4);

    }
}