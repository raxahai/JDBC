package org.example.entity;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tools.jackson.databind.ObjectMapper;

import java.io.StringWriter;

public class UserTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final String DOC = "{\"email\":null,\"first_name\":\"Syed Raza\",\"id\":1,\"last_name\":\"Haider\"}";

    @Test
    void firstNameMapperTest() {
        User actual = objectMapper.readValue(DOC, User.class);
        assertEquals("Syed Raza", actual.getFirstName());
    }

    @Test
    void lastName_ReadOnly_MapperTest() {
        StringWriter sw = new StringWriter();

        User user = new User();
        user.setFirstName("Syed Raza");
        user.setLastName("Haider");

        objectMapper.writeValue(sw, user);

        User actual = objectMapper.readValue(sw.toString(), User.class);
        assertNull(actual.getLastName());
    }

    @Test
    void checksForFirstName_Alias_Serialization() {
        User user = new User();
        user.setFirstName("Syed Raza");

        String json = objectMapper.writeValueAsString(user);
        assertTrue(json.contains("first_name"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"name", "current_name"})
    void checksForFirstName_Alias_Deserialization(String alias) {
        String str = "{\"%s\":\"Syed Raza\"}".formatted(alias);

        User actual = objectMapper.readValue(str, User.class);

        assertEquals("Syed Raza", actual.getFirstName());
    }
}
