package ru.mts;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.repository.AnimalsRepository;
import ru.mtsstarter.animals.Animal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Component
public class ScheduledTask {
    private final AnimalsRepository animalsRepository;

    public ScheduledTask(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }
    @Scheduled(fixedRate = 5000)
    public void doTask(){
        Map<String, LocalDate> animalNames = animalsRepository.findLeapYearNames();
        for (Map.Entry<String, LocalDate> entry : animalNames.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
        System.out.println("Високосные годы рождений\n");

        int age = 3;
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimal(age);
        for (Map.Entry<Animal, Integer> entry : olderAnimals.entrySet()) {
            System.out.printf("%s Возраст: %s%n", entry.getKey().toString(), entry.getValue());
        }
        System.out.println("Животные старше " + age + " лет\n");

        Map<String, List<Animal>> duplicates = animalsRepository.findDuplicate();

        animalsRepository.printDuplicate();
        System.out.println("Дубликаты\n");

        animalsRepository.findAverageAge(animalsRepository.convertMapToList());

        List<Animal> animalList = animalsRepository.findOldAndExpensive(animalsRepository.convertMapToList());
        for(Animal animal : animalList){
            System.out.printf("%s Дата рождения: %s Стоимость: %s%n", animal.getName(), animal.getBirthDay(), animal.getCost());
        }
        System.out.println("Животные старше 5 лет и дороже средней стоимости всех\n");

        List<String> lowCosterNames = animalsRepository.findMinConstAnimals(animalsRepository.convertMapToList());
        for(String name : lowCosterNames){
            System.out.println(name);
        }
        System.out.println("Имена самых дешевых животных\n");
    }
}
