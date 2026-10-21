package org.example.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AnnotatedProduct {
    private final String name = "Syed Raza";
    public final int age = 25;
}
