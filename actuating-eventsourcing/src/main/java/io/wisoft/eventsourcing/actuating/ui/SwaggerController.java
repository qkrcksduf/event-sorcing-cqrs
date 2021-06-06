package io.wisoft.eventsourcing.actuating.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {

  @GetMapping(value = "/")
  public String index() {
    final String url = "/swagger-ui.html";
    return "redirect:" + url;
  }

}
