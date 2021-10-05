package exchange;

import java.io.Serializable;
import java.sql.Connection;

public class User implements Serializable {
    private String login;
    private String password;
    private boolean newUser;

    public User(String login , String password) {
        this.login = login;
        this.password = password;
    }
    public User(String login , String password, boolean newUser) {
        this.login = login;
        this.password = password;
        this.newUser = newUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isNewUser() {
        return newUser;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }

    public boolean getNewUser() { return  newUser; }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", newUser=" + newUser +
                '}';
    }

    //TODO при коннекте к серверу создается поток обработки подключения
    // и класс с данными клиента (его Chanel Socket, user)
    // происходит проверка на нового пользователя
    // и аунтефкация старого/добавление нового пользователя
    // и вывод сообщения на экран клиента
    // у каждого клиента:
    // свой  чтитель запросов (ForkJoinPool)
    // свой обработчик запросов (Cached thread pool)
    // свой ответчик (Fixed thread pool)
}
