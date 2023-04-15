package net.goldally.psasic_.responces;

import java.sql.Date;

public class UserInfo {
    String name;
    String surname;
    String username;
    Date dateOfBirth;
    String icon;
    public UserInfo(String username, String name, String surname, Date dateOfBirth, String icon){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.icon = icon;
    }
}
