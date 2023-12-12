package ru.mts.entity;
/*
    Класс товаров с параметрами:
    количество;
    цена;
    скидка;
 */

import java.util.Optional;

public class Products {
    // Количество товаров
    private int amount;
    // Цена товара
    private double price;
    // Скидка на товар
    private double discount;

    /*
    Конструктор класса Товары
    -amount – количество товаров
    -price – цена товаров
    -discount – скидка на товар
     */
    public Products(int amount, double price, double discount) {
        this.amount = Optional.of(amount).orElseThrow(() -> new IllegalArgumentException("Количество товаров не может быть пустым"));
        this.price = Optional.of(price).orElseThrow(() -> new IllegalArgumentException("Цена не может быть пустой"));
        this.discount = Optional.of(discount).orElseThrow(() -> new IllegalArgumentException("Скидка не может быть пустой"));
        System.out.println(Products.this);
    }



    // Метод для посчёта общей суммы покпки со скидкой и без неё
    public static void calculateTotal(Products product){

        // Инициализация объекта Optional
        Optional<Products> prodExample = Optional.ofNullable(product);

        // Проверка на null
        if(prodExample.isPresent()){
            // Проверка на некорректные данные
            if(product.amount <= 0 || product.price <= 0 || product.discount <0 || product.discount > 100){
                System.out.println("Некорректные данные. Стоимость товара и количество должны быть больше 0. Скидка должна быть в диапазоне от 0 до 100. Выход из программы!");
                throw new IllegalArgumentException("Incorrect data");
            }

            double totalCost = product.amount * product.price; //Сумма покупки без скидки
            double totalCostWithDisc = totalCost*(1-product.discount*0.01); // Сумма покупки со скидкой

            System.out.println("Общая сумма покупки без скидки: "+String.format("%.2f", totalCost));
            System.out.println("Общая сумма покупки со скидкой: "+String.format("%.2f",totalCostWithDisc));
            System.out.println();
        }
        else {
            throw new IllegalArgumentException("NOT NULL!");
        }


    }
}
