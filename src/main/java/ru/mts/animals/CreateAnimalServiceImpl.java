package ru.mts.animals;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс CreateAnimalServiceImpl реализует интерфейс CreateAnimalService.
 * Внутри класса переопределен дефолтный метод интерфейса для реализаиии его через другой цикл
 * с использованием реализованного метода getFactory интерфейса для выбора случайной Фабрики.
 * Так же этот метод перегружен введением атрибута целого числа - желаемого числа создаваемых животных.
 * Добавлены методы генерации параметров для создания животных
 */
public class CreateAnimalServiceImpl implements CreateAnimalService {
    static final String[] Names = {"Simba", "Rex", "Whiskers", "Fluffy", "Dumbo", "Jerry", "Tom", "Nemo", "Mikky"}; // клички
    static final String[] Characters = {"Brave", "Playful", "Calm", "Curious", "Gentle"}; // поведения

    /**
     * Перегруженный метод создания животных по заданному целому числу
     *
     * @param amount число животных
     * @return animals - массив животных
     */
    public ArrayList<Animal> createAnimals(int amount) {
        Random random = new Random();
        ArrayList<Animal> animals = new ArrayList<>();
        AnimalFactory animalFactory;
        Animal animal;
        for (int i = 0; i < amount; i++) {
            animalFactory = getFactory();
            animal = animalFactory.generateAnimal();
            animals.add(i, animal);
            System.out.printf("%s %s %s %s %s%n", animal.getName(), animal.getBreed(), animal.getCost(), animal.getCharacter(), animal.getBirthDay());
        }
        System.out.println("Вывод N животных из имплемента\n");
        return animals;
    }

    /**
     * Переопределенный метод создания 10 животных через цикл do while
     *
     * @return animals - массив животных
     */
    public ArrayList<Animal> createAnimals() {
        ArrayList<Animal> animals = new ArrayList<>();
        int count = 0;
        AnimalFactory animalFactory;
        Animal animal;
        do {
            animalFactory = getFactory();
            animal = animalFactory.generateAnimal();
            animals.add(count, animal);
            System.out.printf("%s %s %s %s %s%n", animal.getName(), animal.getBreed(), animal.getCost(), animal.getCharacter(), animal.getBirthDay());
            count++;
        } while (count < 10);
        System.out.println("\nВывод 10 животных из имплемента\n");
        return animals;
    }

    /**
     * Создает случайную фабрику
     *
     * @return случайная фабрика
     */
    @Override
    public AnimalFactory getFactory() {
        Random random = new Random();
        AnimalFactory animalFactory;
        if (random.nextInt(2) == 0) animalFactory = new PetCreatorFactory();
        else animalFactory = new PredatorCreatorFactory();
        return animalFactory;
    }

    protected static String generateName() {
        Random random = new Random();
        // Генерация случайного имени
        return Names[random.nextInt(Names.length)];
    }

    protected static BigDecimal generateCost() {
        Random random = new Random();
        // Генерация случайной стоимости
        return BigDecimal.valueOf(random.nextDouble() * 1000).setScale(2, RoundingMode.HALF_UP);
    }

    protected static String generateCharacter() {
        Random random = new Random();
        // Генерация случайного поведения
        return Characters[random.nextInt(Characters.length)];
    }

    protected static LocalDate generateBirthDay() {
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
}
