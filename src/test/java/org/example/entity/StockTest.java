package org.example.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.exc.UnrecognizedPropertyException;
import tools.jackson.databind.json.JsonMapper;

public class StockTest {
    private static final ObjectMapper objectMapper = JsonMapper.builder()
            .enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .build();

    @Test
    void testFor_Stock_Entity_Serialization() {
        Stock stock = new Stock();
        stock.setName("HBL");
        stock.setQuantity(1000000L);

        String json = objectMapper.writeValueAsString(stock);

        assertTrue(json.contains("name"));
        assertTrue(json.contains("quantity"));
    }

    @Test
    void testFor_Stock_Entity_Deserialization() {
        String content = "{\"name\":\"HBL\",\"quantity\":1000000}";

        Stock stock = objectMapper.readValue(content, Stock.class);

        assertEquals("HBL", stock.getName());
        assertEquals(1000000, stock.getQuantity());
    }

    @Test
    void testFor_Stock_Entity_Deserialization_WithIgnoringProperties() {
        String content = "{\"name\":\"HBL\",\"quantity\":1000000,\"type\":\"Mature\"}";

        UnrecognizedPropertyException exception = assertThrows(UnrecognizedPropertyException.class,
                () -> objectMapper.readValue(content, Stock.class));

        assertEquals("type", exception.getPropertyName());
    }
}
