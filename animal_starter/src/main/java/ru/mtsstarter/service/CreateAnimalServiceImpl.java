package ru.mtsstarter.service;


import org.springframework.beans.factory.annotation.Value;
import ru.mtsstarter.animals.Animal;
import ru.mtsstarter.animals.AnimalFactory;
import ru.mtsstarter.animals.AnimalFactoryImpl;
import ru.mtsstarter.animals.AnimalTypes;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Класс CreateAnimalServiceImpl реализует интерфейс CreateAnimalService.
 * Внутри класса переопределен дефолтный метод интерфейса для реализаиии его через другой цикл
 * с использованием реализованного метода getFactory интерфейса для выбора случайной Фабрики.
 * Так же этот метод перегружен введением атрибута целого числа - желаемого числа создаваемых животных.
 * Добавлены методы генерации параметров для создания животных
 */
public class CreateAnimalServiceImpl implements CreateAnimalService {

    private AnimalTypes animalTypes;

    @Value("${names}")
    private String[] animalNames;

    /**
     * Инициализация AnimalTypes
     *
     * @param animalTypes
     */
    public void setAnimalTypes(AnimalTypes animalTypes) {
        this.animalTypes = animalTypes;
    }

    /**
     * Перегруженный метод создания животных по заданному целому числу
     *
     * @param amount число животных
     * @return animals - массив животных
     */
    public Animal[] createAnimals(int amount) {
        Random random = new Random();
        ArrayList<Animal> animals = new ArrayList<>();
        AnimalFactory animalFactory;
        Animal animal;
        if (amount <= 0) throw new RuntimeException("Количество животных должно быть натуральным числом");
        for (int i = 0; i < amount; i++) {
            animalFactory = getFactory();
            animal = animalFactory.generateAnimal(animalTypes, generateRandomName());
            animals.add(i, animal);
            //System.out.printf("%s %s %s %s %s%n", animal.getName(), animal.getBreed(), animal.getCost(), animal.getCharacter(), animal.getBirthDay().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
        System.out.println("Вывод N животных из имплемента\n");
        return animals.toArray(new Animal[0]);
    }

    /**
     * Переопределенный метод создания 10 животных через цикл do while
     *
     * @return animals - массив животных
     */
    @Override
    public Animal[] createAnimals() {
        ArrayList<Animal> animals = new ArrayList<>();
        int count = 0;
        AnimalFactory animalFactory;
        Animal animal;
        do {
            animalFactory = getFactory();
            animal = animalFactory.generateAnimal(animalTypes, generateRandomName());
            animals.add(count, animal);
            System.out.printf("%s %s %s %s %s%n", animal.getName(), animal.getBreed(), animal.getCost(), animal.getCharacter(), animal.getBirthDay().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            count++;
        } while (count < 10);
        System.out.println("\nВывод 10 животных из имплемента\n");
        return animals.toArray(new Animal[0]);
    }

    /**
     * Создает случайную фабрику
     *
     * @return случайная фабрика
     */
    @Override
    public AnimalFactory getFactory() {
        return new AnimalFactoryImpl();
    }

    /**
     *
     * @return случайное имя из профиля
     */
    private String generateRandomName() {
        return animalNames[new Random().nextInt(animalNames.length)];
    }
}
