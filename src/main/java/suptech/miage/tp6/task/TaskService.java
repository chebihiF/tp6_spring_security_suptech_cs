package suptech.miage.tp6.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task addTask(Task task) throws RuntimeException {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) throws RuntimeException {
        return taskRepository.save(task);
    }

    @Override
    public Task deleteTask(Task task) throws RuntimeException {
        taskRepository.delete(task);
        return task ;
    }

    @Override
    public Task getTask(Long id) throws RuntimeException {
        return taskRepository.findById(id).get();
    }

    @Override
    public List<Task> getTasks() throws RuntimeException {
        return taskRepository.findAll();
    }
}
