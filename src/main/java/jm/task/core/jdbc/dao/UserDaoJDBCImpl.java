package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection = getConnection();
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    String sql;

    public UserDaoJDBCImpl() {


    }

//    public UserDaoJDBCImpl(Connection connection, PreparedStatement preparedStatement, Statement statement, String sql) {
//        this.connection = connection;
//        this.preparedStatement = preparedStatement;
//        this.statement = statement;
//        this.sql = sql;
//    }

    public void createUsersTable() throws SQLException {
        //работает
        sql = "CREATE TABLE User " +
              "(id INTEGER not NULL AUTO_INCREMENT, " +
              " name VARCHAR(50), " +
              " lastName VARCHAR(50), " +
              " age INTEGER not NULL, " +
              " PRIMARY KEY (id))";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println("Такая таблица уже есть");
        }
//        finally {
//            if (preparedStatement != null){
//                preparedStatement.close();
//            }
//            if (connection != null){
//                connection.close();
//            }
//        }
    }

    public void dropUsersTable() throws SQLException {
        //работает
        sql = "DROP TABLE user";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();

        }
//        finally {
//            if (preparedStatement != null){
//                preparedStatement.close();
//            }
//            if (connection!= null){
//                connection.close();
//            }
//        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        //работает
        sql = "INSERT INTO user(id, name, lastName, age) VALUES (id, ? ,?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setLong(3, age);

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();

        }
//        finally {
//            if (preparedStatement != null){
//                preparedStatement.close();
//            }
//            if (connection!= null){
//                connection.close();
//            }
//        }


    }

    public void removeUserById(long id) throws SQLException {
        //работает
        sql = "DELETE FROM user where ID = "+ id;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();

        }
//        finally {
//            if (preparedStatement != null){
//                preparedStatement.close();
//            }
//            if (connection!= null){
//                connection.close();
//            }
//        }

    }

    public List<User> getAllUsers() throws SQLException {
        //работает
        List<User> userList = new ArrayList<>();

        sql = "SELECT id, name, lastName, Age FROM user";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()){
                User user = new User();

                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
//        finally {
//            if (statement != null){
//                statement.close();
//            }
//            if (connection!= null){
//                connection.close();
//            }
//        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        //работает

        sql = "DELETE FROM USER";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();


        } catch (SQLException e){
            e.printStackTrace();

        }
//        finally {
//            if (preparedStatement != null){
//                preparedStatement.close();
//            }
//            if (connection!= null){
//                connection.close();
//            }
//        }

    }
}
