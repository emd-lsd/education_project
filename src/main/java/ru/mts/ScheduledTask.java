package ru.mts;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.repository.AnimalsRepository;
import ru.mtsstarter.animals.Animal;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Component
public class ScheduledTask {
    private final AnimalsRepository animalsRepository;

    public ScheduledTask(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }
    @Scheduled(fixedRate = 5000)
    public void doTask(){
        //вызов findLeapYearNames
        String[] animalNames = animalsRepository.findLeapYearNames();
        for (String name : animalNames) {
            System.out.println(name);
        }
        System.out.println("Високосные годы рождений\n");

        //вызов findOlderAnimal
        Animal[] olderAnimals = animalsRepository.findOlderAnimal(3);
        for (Animal element : olderAnimals) {
            System.out.printf("%s %s%n", element.getName(), element.getBirthDay().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
        System.out.println("Животные старше N лет\n");

        //вызов findDuplicates
        Set<Animal> duplicates = animalsRepository.findDuplicate();

        //вызов printDuplicate
        animalsRepository.printDuplicate();
        System.out.println("Дубликаты\n");
    }
}
