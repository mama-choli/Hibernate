package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl()  {

    }

    // Создание таблицы для User(ов) – не должно приводить к исключению, если такая таблица уже существует


    public void createUsersTable() {
        try (Connection conn = Util.getconnectionjdbc();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id INTEGER NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, lastName CHAR(30) NOT NULL, age INTEGER NOT NULL, PRIMARY KEY (id))");

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует
    public void dropUsersTable() {
        try (Connection conn = Util.getconnectionjdbc();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Добавление User в таблицу
    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getconnectionjdbc();
             PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Удаление User из таблицы ( по id )
    public void removeUserById(long id) {
        try (Connection conn = Util.getconnectionjdbc(); PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Получение всех User(ов) из таблицы
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try (Connection conn = Util.getconnectionjdbc(); ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {

                User user = new User(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4));

                users.add(user);

            }

            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    // Очистка содержания таблицы

    public void cleanUsersTable() {
        try (Connection conn = Util.getconnectionjdbc(); Statement statement = conn.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
