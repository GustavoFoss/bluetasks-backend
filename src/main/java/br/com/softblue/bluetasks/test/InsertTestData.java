package br.com.softblue.bluetasks.test;

import br.com.softblue.bluetasks.domain.AppUser;
import br.com.softblue.bluetasks.domain.AppUserRepository;
import br.com.softblue.bluetasks.domain.task.Task;
import br.com.softblue.bluetasks.domain.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InsertTestData {

  private TaskRepository taskRepository;
  private AppUserRepository appUserRepository;

  @Autowired
  public InsertTestData(TaskRepository taskRepository, AppUserRepository appUserRepository) {
    this.taskRepository = taskRepository;
    this.appUserRepository = appUserRepository;
  }

  @EventListener
  public void onApplicationEvent(ContextRefreshedEvent event) {
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    AppUser appUser = new AppUser("henrique", passwordEncoder.encode("abc"), "fuchsao");
    appUserRepository.save(appUser);

    LocalDate baseDate = LocalDate.parse("2025-02-01");

    for (int i = 1; i <=10 ; i++) {
      Task task = new Task("Tarefa#" + i,baseDate.plusDays(i),false );
      task.setAppUser(appUser);
      taskRepository.save(task);
    }
  }
}
