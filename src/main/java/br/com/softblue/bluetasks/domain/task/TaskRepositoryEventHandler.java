package br.com.softblue.bluetasks.domain.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;


@Component
@RepositoryEventHandler(Task.class)
public class TaskRepositoryEventHandler {

  private TaskRepository taskRepository;

  @Autowired
  public TaskRepositoryEventHandler(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @HandleBeforeSave
  @HandleBeforeCreate
  public void handle(Task task) throws DuplicateTaskException {
    Task task1 = taskRepository.findByDescription(task.getDescription());
    boolean duplicate = false;

    if (task1 != null) {
      if ((task.getId() == null || task.getId() == 0) && task.getDescription().equals(task1.getDescription())) {
        duplicate = true;
      }

      if ((task.getId() != null && task.getId() > 0) && !task.getId().equals(task1.getId())) {
        duplicate = true;
      }
    }

    if (duplicate == true) {
      throw new DuplicateTaskException("Task duplicada");
    }
  }


}
