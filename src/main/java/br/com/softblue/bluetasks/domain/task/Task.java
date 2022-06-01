package br.com.softblue.bluetasks.domain.task;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.softblue.bluetasks.domain.AppUser;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Length;

@Entity
@EntityListeners(TaskListener.class)
public class Task {

  @Id
  @GeneratedValue
  private Integer Id;

  @NotEmpty(message = "Descrição não pode ser vazia")
  @Length(min = 3, max = 40, message = "O tamanho da tarefa é invalido")
  private String description;

  @NotNull
  @FutureOrPresent(message = "A data não pode estar no passado")
  private LocalDate whenToDo;

  private Boolean done = false;

  @ManyToOne
  @JoinColumn(name = "app_user_id")
  @JsonIgnore
  private AppUser appUser;

  public Task() {
  }

  public Task(String description, LocalDate whenToDo, Boolean done) {
    this.description = description;
    this.whenToDo = whenToDo;
    this.done = done;
  }

  public Integer getId() {
    return Id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getWhenToDo() {
    return whenToDo;
  }

  public void setWhenToDo(LocalDate whenToDo) {
    this.whenToDo = whenToDo;
  }

  public Boolean getDone() {
    return done;
  }

  public void setDone(Boolean done) {
    this.done = done;
  }

  public AppUser getAppUser() {
    return appUser;
  }

  public void setAppUser(AppUser appUser) {
    this.appUser = appUser;
  }
}
