package suptech.miage.tp6.task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class TaskConfig {

    private final ITaskService taskService;

    public TaskConfig(ITaskService taskService) {
        this.taskService = taskService;
    }

    CommandLineRunner initTask(){
        return args -> {
            taskService.addTask(new Task(
                    null,
                    "learn Spring Boot",
                    "Learn spring boot",
                    LocalDate.of(2022,5,10),
                    true));
            taskService.addTask(new Task(
                    null,
                    "learn Angular",
                    "Learn Angular",
                    LocalDate.of(2022,6,15),
                    false));
        };
    }
}
