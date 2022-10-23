package peaksoft.service;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserServiceImpl implements UserService {

    public void createUsersTable() {
            String query = "create table users(id serial primary key, name varchar, last_name varchar,age int);";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("Table users has been created successfully!");
        } catch (SQLException e) {
            System.out.println("Table users has been created unsuccessfully!");
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String query = "drop table users;";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("Table users has been dropped successfully!");
        } catch (SQLException e) {
            System.out.println("Table users has been dropped unsuccessfully!");
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "insert into users( name,last_name,age) values(?,?,?)";
        try (Connection c = Util.getConnection();
             PreparedStatement ps = c.prepareStatement(query)){
            ps.setString(1,name);
            ps.setString(2,lastName);
            ps.setInt(3,age);
            ps.executeUpdate();
            System.out.println("User with name " + name + " has been successfully added to database");
        }
        catch (SQLException e){
            System.out.println("User with name " + name + " has been unsuccessfully added to database");
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String query = "delete from users where id = ?";
        try (Connection c = Util.getConnection();
             PreparedStatement ps = c.prepareStatement(query)){
            ps.setLong(1,id);
            ps.executeUpdate();
            System.out.println("User with id " + id + " has been successfully removed from database");
        }
        catch (SQLException e){
            System.out.println("User with id " + id + " has been unsuccessfully removed from database");
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User>  users = new LinkedList<>();
        String SQL = "SELECT * FROM users";
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                User tmp = new User();
                tmp.setId(rs.getLong("id"));
                tmp.setAge(rs.getByte("age"));
                tmp.setName(rs.getString("name"));
                tmp.setLastName(rs.getString("last_name"));
                users.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
}

    public void cleanUsersTable() {
        String query = "truncate table users;";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("Table users has been dropped successfully!");
        } catch (SQLException e) {
            System.out.println("Table users has been dropped unsuccessfully!");
            System.out.println(e.getMessage());
        }
    }
}
