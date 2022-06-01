package br.com.softblue.bluetasks.infrastructure.web;

import br.com.softblue.bluetasks.domain.task.DuplicateTaskException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class RestResponseError {

  private String error;


  private RestResponseError() {

  }

  public String getError() {
    return error;
  }

  public static RestResponseError fromValidationError(Errors errors) {
    RestResponseError resp = new RestResponseError();
    StringBuilder sb = new StringBuilder();

    for (ObjectError error : errors.getAllErrors()) {
      sb.append(error.getDefaultMessage()).append(".");
    }

    resp.error = sb.toString();
    return resp;
  }

  public static RestResponseError fromMessage(String mesg) {
    RestResponseError restResponseError = new RestResponseError();
    restResponseError.error = mesg;
    return restResponseError;
  }
}
