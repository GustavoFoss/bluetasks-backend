package br.com.softblue.bluetasks.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "app_user")
public class AppUser {

  public AppUser() {
  }

  public AppUser(String username, String password, String displayName) {
    this.username = username;
    this.password = password;
    this.displayName = displayName;
  }

  @Id
  @GeneratedValue
  private Integer Id;

  @NotEmpty(message = "O usuario é obrigatorio")
  private String username;

  @NotEmpty(message = "A senha é obrigatoria")
  private String password;

  @NotEmpty(message = "Nome de exibição é obrigatorio")
  private String displayName;

  public Integer getId() {
    return Id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

}
