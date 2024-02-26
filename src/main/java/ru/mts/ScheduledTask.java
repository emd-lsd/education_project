package ru.mts;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.repository.AnimalsRepository;
import ru.mtsstarter.animals.Animal;
import java.time.LocalDate;
import java.util.Map;

@Component
public class ScheduledTask {
    private final AnimalsRepository animalsRepository;

    public ScheduledTask(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }
    @Scheduled(fixedRate = 5000)
    public void doTask(){
        //вызов findLeapYearNames
        Map<String, LocalDate> animalNames = animalsRepository.findLeapYearNames();
        for (Map.Entry<String, LocalDate> entry : animalNames.entrySet()) {
            System.out.println(entry.getKey());
        }
        System.out.println("Високосные годы рождений\n");

        //вызов findOlderAnimal
        int age = 3;
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimal(age);
        for (Map.Entry<Animal, Integer> entry : olderAnimals.entrySet()) {
            System.out.printf("%s Возраст: %s%n", entry.getKey().getName(), entry.getValue());
        }
        System.out.println("Животные старше " + age + " лет\n");

        //вызов findDuplicates
        Map<String, Integer> duplicates = animalsRepository.findDuplicate();

        //вызов printDuplicate
        animalsRepository.printDuplicate();
        System.out.println("Дубликаты\n");
    }
}
