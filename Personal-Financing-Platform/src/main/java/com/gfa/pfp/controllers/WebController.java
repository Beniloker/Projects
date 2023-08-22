package com.gfa.pfp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class WebController {

  @GetMapping("/")
  public String renderMainPage() {
    return "index";
  }

}