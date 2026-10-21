package org.example.entity;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnnotatedProductTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testFor_Product_Serialization_Of_Public_Fields(){
        String json = objectMapper.writeValueAsString(new AnnotatedProduct());
        assertTrue(json.contains("age"));
    }

    @Test
    void testFor_Product_Serialization_Of_Private_Fields(){
        String json = objectMapper.writeValueAsString(new AnnotatedProduct());
        assertTrue(json.contains("name"));
    }
}
