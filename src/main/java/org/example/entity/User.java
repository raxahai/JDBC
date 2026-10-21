package org.example.entity;


import com.fasterxml.jackson.annotation.*;

@JsonClassDescription(value = "This is a user entity class")
public class User {
    /**
     * This is a id
     * Type: LONG
     */
    private Long id;
    @JsonProperty(value = "first_name")
    @JsonAlias({"name", "current_name"})
    private String firstName;
    @JsonProperty(value = "last_name", access = JsonProperty.Access.READ_ONLY)
    private String lastName;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonGetter(value = "email")
    public String getEmail() {
        return email;
    }

    @JsonSetter(value = "email", nulls = Nulls.SKIP)
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
