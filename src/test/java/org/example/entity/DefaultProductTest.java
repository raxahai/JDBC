package org.example.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

public class DefaultProductTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testFor_Product_Serialization_Of_Public_Fields(){
        String json = objectMapper.writeValueAsString(new DefaultProduct());
        assertTrue(json.contains("age"));
    }

    @Test
    void testFor_Product_Serialization_Of_Private_Fields(){
        String json = objectMapper.writeValueAsString(new DefaultProduct());
        assertFalse(json.contains("name"));
    }
}
