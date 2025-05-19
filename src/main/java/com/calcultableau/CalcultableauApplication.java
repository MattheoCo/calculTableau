package com.calcultableau;

import com.calcultableau.service.CalculService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

@SpringBootApplication
public class CalcultableauApplication implements CommandLineRunner {

    @Autowired
    private CalculService calculService;

    public static void main(String[] args) {
        SpringApplication.run(CalcultableauApplication.class, args);
    }

    @Override
    public void run(String... args) {
        calculService.executerCalcul();
    }
}