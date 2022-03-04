package suptech.miage.tp6.task;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final ITaskService taskService;

    @GetMapping
    public List<Task> getTasks(){
        return taskService.getTasks();
    }
}
