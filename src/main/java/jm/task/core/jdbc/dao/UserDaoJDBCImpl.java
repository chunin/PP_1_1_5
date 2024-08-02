package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    private PreparedStatement preparedStatement = null;
    private Statement statement = null;
    private String sql;

    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {

        sql = "CREATE TABLE User " +
                "(id INTEGER not NULL AUTO_INCREMENT, " +
                " name VARCHAR(50), " +
                " lastName VARCHAR(50), " +
                " age INTEGER not NULL, " +
                " PRIMARY KEY (id))";

        try (Connection connection = getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Такая таблица уже есть");
        }
    }

    @Override
    public void dropUsersTable() {

        sql = "DROP TABLE user";

        try (Connection connection = getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        sql = "INSERT INTO user(id, name, lastName, age) VALUES (id, ? ,?, ?)";
        try (Connection connection = getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setLong(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        sql = "DELETE FROM user where ID = " + id;
        try (Connection connection = getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();

        sql = "SELECT id, name, lastName, Age FROM user";
        try (Connection connection = getConnection()) {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("AGE"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {

        sql = "DELETE FROM USER";

        try (Connection connection = getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
