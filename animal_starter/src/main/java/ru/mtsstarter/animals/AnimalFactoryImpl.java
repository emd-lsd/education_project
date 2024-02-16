package ru.mtsstarter.animals;


import ru.mtsstarter.animals.pet.*;
import ru.mtsstarter.animals.predator.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalFactoryImpl implements AnimalFactory {
    Random random = new Random();
    static final String[] characters = {"Brave", "Playful", "Calm", "Curious", "Gentle"}; // поведения
    private final Map<String, AbstractAnimal> animal = new HashMap<>();


    public AnimalFactoryImpl() {
        animal.put("HAMSTER", new Hamster("", generateCost(), generateCharacter(), generateBirthDay()));
        animal.put("CAT", new Cat("", generateCost(), generateCharacter(), generateBirthDay()));
        animal.put("TURTLE", new Turtle("", generateCost(), generateCharacter(), generateBirthDay()));
        animal.put("DOG", new Dog("", generateCost(), generateCharacter(), generateBirthDay()));
        animal.put("RABBIT", new Rabbit("", generateCost(), generateCharacter(), generateBirthDay()));
        animal.put("BEAR", new Bear("", generateCost(), generateCharacter(), generateBirthDay()));
        animal.put("FOX", new Fox("", generateCost(), generateCharacter(), generateBirthDay()));
        animal.put("SHARK", new Shark("", generateCost(), generateCharacter(), generateBirthDay()));
        animal.put("TIGER", new Tiger("", generateCost(), generateCharacter(), generateBirthDay()));
        animal.put("WOLF", new Wolf("", generateCost(), generateCharacter(), generateBirthDay()));
    }

    /**
     *
     * @return Возвращает случайное поведение
     */
    @Override
    public String generateCharacter() {
        Random random = new Random();
        // Генерация случайного поведения
        return characters[random.nextInt(characters.length)];
    }

    /**
     *
     * @return Возвращает случайный день рождения в формате LocalDate
     */
    @Override
    public LocalDate generateBirthDay() {
        // Генерация случайного года от 2010 до 2023
        int year = ThreadLocalRandom.current().nextInt(2010, 2023);

        // Генерация случайного месяца
        int monthValue = ThreadLocalRandom.current().nextInt(1, 13);
        Month month = Month.of(monthValue);

        // Генерация случайного дня в месяце
        int maxDay = month.length(false); // Получаем количество дней в месяце
        int day = ThreadLocalRandom.current().nextInt(1, maxDay + 1);

        return LocalDate.of(year, month, day);
    }

    /**
     *
     * @return Возвращает случайную стоимость в формате BigDecimal
     */
    @Override
    public BigDecimal generateCost() {
        Random random = new Random();
        // Генерация случайной стоимости
        return BigDecimal.valueOf(random.nextDouble() * 1000).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public Animal generateAnimal(AnimalTypes animalTypes, String name) {
        if (animalTypes != null) {
            Animal anim = animal.get(animalTypes.toString());
            anim.setName(name);
            return anim;
        } else {
            throw new IllegalArgumentException("AnimalTypes cannot be null");
        }
    }
}
