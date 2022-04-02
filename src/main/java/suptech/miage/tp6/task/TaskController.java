package suptech.miage.tp6.task;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final ITaskService taskService;

    @GetMapping
    public List<Task> getTasks(){
        return taskService.getTasks();
    }

    @PostMapping
    public Task add(@RequestBody Task task){
        return taskService.addTask(task);
    }

    @DeleteMapping("/{id}")
    public Task delete(@PathVariable Long id){
        return taskService.deleteTask(taskService.getTask(id));
    }
}
