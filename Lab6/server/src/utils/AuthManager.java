package utils;

import exchange.CommandStatus;
import exchange.Response;
import exchange.User;
import stdgroup.StudyGroup;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Set;

public class AuthManager {
    DBManager dbManager;
    public AuthManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public Response checkUser(User user){
        CommandStatus cmd = null;
        try {
            cmd = dbManager.check(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String str = "Пользователь авторизирован" ;
        if (cmd == CommandStatus.WRONG_USERNAME && user.isNewUser())
            str = "Пользователь с таким ником уже существует";
        else if (cmd == CommandStatus.WRONG_USERNAME && !user.isNewUser())
            str = "Пользователя с таким ником не существует";
        else if (cmd == CommandStatus.WRONG_PASSWORD)
            str = "Неверный логин или пароль";
        return new Response( cmd, str );
    }
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(password.getBytes());
            BigInteger integers = new BigInteger(1, bytes);
            String newPassword = integers.toString(16);
            while (newPassword.length() < 32) {
                newPassword = "0" + newPassword;
            }
            return newPassword;
        } catch (NoSuchAlgorithmException exception) {
            System.out.println("Не найден алгоритм хэширования пароля!");
            throw new IllegalStateException(exception);
        }
    }
}
