package com.epam.user;

/**
 * @author Dominik_Janiga
 */
class UserResponseDto {

    private Long id;
    private String name;
    private String surname;
    private String birthday;

    UserResponseDto(Long id, String name, String surname, String birthday) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthday() {
        return birthday;
    }
}
