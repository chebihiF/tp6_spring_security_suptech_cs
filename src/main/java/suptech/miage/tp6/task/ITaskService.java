package suptech.miage.tp6.task;

import java.util.List;

public interface ITaskService {
    public Task addTask(Task task) throws RuntimeException;
    public Task updateTask(Task task) throws RuntimeException;
    public Task deleteTask(Task task) throws RuntimeException;
    public Task getTask(Long id) throws RuntimeException;
    public List<Task> getTasks() throws RuntimeException;
}
