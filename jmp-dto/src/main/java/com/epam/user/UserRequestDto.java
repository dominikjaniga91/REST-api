package com.epam.user;

import java.time.LocalDate;

/**
 * @author Dominik_Janiga
 */
class UserRequestDto {

    private Long id;
    private String name;
    private String surname;
    private String birthday;

    User toUser() {
        return new User(this.id, this.name, this.surname, LocalDate.parse(this.birthday));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
