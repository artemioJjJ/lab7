package ru.itmo.p3131.student18.client.tools;

import ru.itmo.p3131.student18.client.tools.readers.UserDataReader;

public class User {
    private final String login;

    public User(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
