package ru.timur.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        User user = new User("login", "email", List.of("a1", "a2"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("target/car.json"), user);
    }
}