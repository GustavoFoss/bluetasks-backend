package br.com.softblue.bluetasks.domain.task;

public class DuplicateTaskException extends Exception {

  public DuplicateTaskException(String message) {
    super(message);
  }
}
