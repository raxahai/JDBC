package org.example;

import org.example.entity.User;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.cfg.DatatypeFeature;
import tools.jackson.databind.cfg.DatatypeFeatures;
import tools.jackson.databind.cfg.JsonNodeFeature;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.core.json.JsonReadFeature;
import tools.jackson.core.json.JsonWriteFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        ObjectMapper objectMapper = JsonMapper.builder()
                .configure(JsonNodeFeature.WRITE_NULL_PROPERTIES, false)
                .configure(JsonReadFeature.ALLOW_JAVA_COMMENTS, true)
                .configure(JsonReadFeature.ALLOW_UNQUOTED_PROPERTY_NAMES, true)
                .configure(JsonReadFeature.ALLOW_SINGLE_QUOTES, true)
                .configure(JsonWriteFeature.ESCAPE_NON_ASCII, true)
                .configure(JsonReadFeature.ALLOW_MISSING_VALUES, false)
                .build();

        List<User> users = setUsers();

        objectMapper.writeValue(new File("./src/main/java/org/example/hello.json"), users);

        List<User> read = objectMapper.readValue(new File("./src/main/java/org/example/hello.json"), new TypeReference<>() {
        });

//        System.out.println(read.get(1).getEmail());
    }

    public static List<User> setUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("Syed Raza");
        user1.setLastName("Haider");

        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Syed Irteza");
        user2.setLastName("Haider");

        User user3 = new User();
        user3.setId(3L);
        user3.setFirstName("Syed Hussain");
        user3.setLastName("Haider");

        return List.of(user1, user2, user3);
    }
}
