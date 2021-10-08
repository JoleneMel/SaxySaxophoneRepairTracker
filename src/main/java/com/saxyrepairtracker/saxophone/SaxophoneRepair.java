package com.saxyrepairtracker.saxophone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import com.saxyrepairtracker.ComponentScanMarker;

@SpringBootApplication(scanBasePackageClasses = {ComponentScanMarker.class})
public class SaxophoneRepair extends SpringBootServletInitializer{

  public static void main(String[] args) {
    SpringApplication.run(SaxophoneRepair.class, args);
  }
}
