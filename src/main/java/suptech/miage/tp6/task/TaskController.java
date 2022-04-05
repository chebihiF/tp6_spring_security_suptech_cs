package suptech.miage.tp6.task;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final ITaskService taskService;

    // hasRole('ROLE_'), hasAnyRole('ROLE_ , ROLE_ '), hasAuthority('permission'), ...

    @GetMapping
    @PreAuthorize("hasAuthority('task:read')")
    public List<Task> getTasks(){
        return taskService.getTasks();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('task:write')")
    public Task add(@RequestBody Task task){
        return taskService.addTask(task);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('task:delete')")
    public Task delete(@PathVariable Long id){
        return taskService.deleteTask(taskService.getTask(id));
    }
}
