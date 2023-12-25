package ru.mts.animals;


import ru.mts.animals.pet.*;
import ru.mts.animals.predator.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс CreateAnimalServiceImpl реализует интерфейс CreateAnimalService.
 * Внутри класса переопределен дефолтный метод интерфейса для реализаиии его через другой цикл
 * с использованием распределенной генерации животных через методы по контракту.
 * Так же этот метод перегружен введением атрибута целого числа - желаемого числа создаваемых животных
 */
public class CreateAnimalServiceImpl implements CreateAnimalService {
    final String[] Names = {"Simba", "Rex", "Whiskers", "Fluffy", "Dumbo", "Jerry", "Tom", "Nemo", "Mikky"}; // клички
    final String[] Characters = {"Brave", "Playful", "Calm", "Curious", "Gentle"}; // поведения

    //Перегруженный метод создания животных по заданному целому числу
    public ArrayList<Animal> createAnimals(int amount) {
        Random random = new Random();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        for (int i=0; i<amount; i++){
            Animal pet = generatePet();
            Animal predator = generatePredator();
            if (random.nextInt(2) == 0) animals.add(i, pet);
            else animals.add(i, predator);
            Animal an = animals.get(i);
            System.out.println(an.getName()+" "+an.getBreed()+" "+an.getCost()+" "+an.getCharacter()+" "+an.getBirthDay());
        }
        System.out.println("\nВывод N животных из имплемента\n");
        return animals;
    }

    // Переопределенный метод создания 10 животных через цикл do while

    public void createAnimals() {
        Random random = new Random();
        int count = 0;
        AbstractAnimal ourAnimal;
        do {
            if (random.nextInt(2) == 0) ourAnimal = generatePet();
            else ourAnimal = generatePredator();
            count++;
            System.out.println("Животное " + ourAnimal.getBreed() + " по кличке " + ourAnimal.getName() + " с характером " + ourAnimal.getCharacter() + " стоимостью " + ourAnimal.getCost() + " создано.");
        } while (count < 10);
        System.out.println("\nВывод 10 животных из имплемента\n");
    }
/*
    @Override
    public Pet generatePet() {
        Random random = new Random();
        Pet pet;

        // Генерация случайных имени, стоимости, поведения
        String name = Names[random.nextInt(Names.length)];
        BigDecimal cost = BigDecimal.valueOf(random.nextDouble() * 1000).setScale(2, BigDecimal.ROUND_HALF_UP);
        String character = Characters[random.nextInt(Characters.length)];

        switch (random.nextInt(5)) {
            case 0: {
                pet = new Cat(name, cost, character);
                break;
            }
            case 1: {
                pet = new Dog(name, cost, character);
                break;
            }
            case 2: {
                pet = new Rabbit(name, cost, character);
                break;
            }
            case 3: {
                pet = new Turtle(name, cost, character);
                break;
            }
            default: {
                pet = new Hamster(name, cost, character);
                break;
            }
        }

        return pet;
    }

    @Override
    public Predator generatePredator() {
        Random random = new Random();
        Predator predator;

        // Генерация случайных имени, стоимости, поведения
        String name = Names[random.nextInt(Names.length)];
        BigDecimal cost = BigDecimal.valueOf(random.nextDouble() * 1000).setScale(2, BigDecimal.ROUND_HALF_UP);
        String character = Characters[random.nextInt(Characters.length)];

        switch (random.nextInt(5)) {
            case 0: {
                predator = new Shark(name, cost, character);
                break;
            }
            case 1: {
                predator = new Wolf(name, cost, character);
                break;
            }
            case 2: {
                predator = new Bear(name, cost, character);
                break;
            }
            case 3: {
                predator = new Fox(name, cost, character);
                break;
            }
            default: {
                predator = new Tiger(name, cost, character);
                break;
            }
        }

        return predator;
    }
*/
    @Override
    public ArrayList<Animal> create(int n) {
        Pet pet = null;
        ArrayList<Animal> animals = new ArrayList<Animal>();
        Random random = new Random();
        PetCreator petCreator = new PetCreator();
        animals.add(0, petCreator.creatorAnimal());
        for (int i=0; i<n; i++){
            animals.add(i, petCreator.creatorAnimal());
            Animal an =animals.get(i);
            System.out.println(an.getName()+" "+an.getBreed()+" "+an.getCost()+" "+an.getCharacter()+" "+an.getBirthDay());
        }
        return animals;
    }

    @Override
    public Pet generatePet() {
        PetCreator petCreator = new PetCreator();
        return (Pet) petCreator.creatorAnimal();
    }

    @Override
    public Predator generatePredator() {
        PredatorCreator predatorCreator = new PredatorCreator();
        return (Predator) predatorCreator.creatorAnimal();
    }

    protected String generateName(){
        Random random = new Random();
        // Генерация случайных имени, стоимости, поведения
        return Names[random.nextInt(Names.length)];
    }

    protected BigDecimal generateCost(){
        Random random = new Random();
        // Генерация случайных имени, стоимости, поведения
        return BigDecimal.valueOf(random.nextDouble() * 1000).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    protected String generateCharacter(){
        Random random = new Random();
        // Генерация случайных имени, стоимости, поведения
        return Characters[random.nextInt(Characters.length)];
    }

    protected LocalDate generateBirthDay(){
        // Генерация случайного года от 1950 до 2005 (для предположения, что человек в возрасте от 18 до 70 лет)
        int year = ThreadLocalRandom.current().nextInt(2010, 2020);

        // Генерация случайного месяца
        int monthValue = ThreadLocalRandom.current().nextInt(1, 13);
        Month month = Month.of(monthValue);

        // Генерация случайного дня в месяце
        int maxDay = month.length(false); // Получаем количество дней в месяце
        int day = ThreadLocalRandom.current().nextInt(1, maxDay + 1);

        return LocalDate.of(year, month, day);
    }

    public class PetCreator {
        Random random = new Random();
        // Генерация случайных имени, стоимости, поведения
        private Map<Integer, Pet> animal = new HashMap<>();

        public PetCreator(){

            animal.put(1,new Hamster(generateName(), generateCost(), generateCharacter(), generateBirthDay()));
            animal.put(2,new Cat(generateName(), generateCost(), generateCharacter(), generateBirthDay()));
            animal.put(3,new Turtle(generateName(), generateCost(), generateCharacter(), generateBirthDay()));
            animal.put(4, new Dog(generateName(), generateCost(), generateCharacter(), generateBirthDay()));
            animal.put(5, new Rabbit(generateName(), generateCost(), generateCharacter(), generateBirthDay()));
        }

        public Animal creatorAnimal(){
            int anim = random.nextInt(animal.size())+1;
            return animal.get(anim);
        }
    }

    public class PredatorCreator {
        Random random = new Random();
        // Генерация случайных имени, стоимости, поведения
        private Map<Integer, Predator> animal = new HashMap<>();

        public PredatorCreator(){

            animal.put(1,new Bear(generateName(), generateCost(), generateCharacter(), generateBirthDay()));
            animal.put(2,new Fox(generateName(), generateCost(), generateCharacter(), generateBirthDay()));
            animal.put(3,new Shark(generateName(), generateCost(), generateCharacter(), generateBirthDay()));
            animal.put(4, new Tiger(generateName(), generateCost(), generateCharacter(), generateBirthDay()));
            animal.put(5, new Wolf(generateName(), generateCost(), generateCharacter(), generateBirthDay()));
        }

        public Animal creatorAnimal(){
            int anim = random.nextInt(animal.size())+1;
            return animal.get(anim);
        }
    }
}
