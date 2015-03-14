/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Jacob Gorney, Max Savard, Matt Mossner, Spencer Kokaly
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package db;

import businessobjects.Problem;
import businessobjects.Clarification;
import businessobjects.User;
import java.sql.*;
import java.util.ArrayList;

/**
 * DBMgr is responsible for getting the appropriate database connection.
 * Supported DB types: MySQL Planned: SQL Server, Postgresql, SQLite, Oracle
 */
public class DBMgr {

    /**
     * The main connection object.
     */
    private static Connection dbConnection;
    /**
     * Database host.
     */
    private static String host;
    /**
     * Database to connect to.
     */
    private static String database;
    /**
     * Database username.
     */
    private static String username;
    /**
     * Database password.
     */
    private static String password;

    /**
     * Public method to build the initial database connection.
     *
     * @param host Host or IP address of server
     * @param database Database name
     * @param username Database username
     * @param password Database password
     * @return boolean
     */
    public static boolean build(String host, String database, String username,
            String password) {
        // Set data
        DBMgr.host = host;
        DBMgr.database = database;
        DBMgr.username = username;
        DBMgr.password = password;
        // Build the connection
        return build();
    }

    /**
     * Check if the database connection was initially established.
     *
     * @return Initial establish result
     */
    public static boolean isConnectionMade() {
        return (!(dbConnection == null));
    }
    
    /**
     * Check if a user exists in the system.
     * @param username Username of user to check
     * @return Result
     */
    public boolean doesUserExist(String username) {
        Statement stmt = null;
        String query = "SELECT COUNT(*) as c FROM `Team` WHERE `username` = '" + username + "';";
        try {
            // Execute the SQL
            stmt = getConnection().createStatement();
            ResultSet result = stmt.executeQuery(query);
            if (!result.next()) {
                return false;
            }			// Return the user ID
            return (result.getInt("c") >= 1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Problem> getProblemSet() {
        Statement stmt = null;
        String query = "SELECT `ProblemNum`, `Title`, `Description` FROM "
                + "`Problems` ORDER BY `ProblemNum` ASC;";
        try {
            stmt = getConnection().createStatement();
            ResultSet result = stmt.executeQuery(query);
            // Loop
            ArrayList<Problem> problemSet = new ArrayList<>();
            while (result.next())
                problemSet.add(new Problem(result.getInt("ProblemNum"),
                result.getString("Title"), result.getString("Description")));
            if (problemSet.size() == 0)
                return null;
            else
                return problemSet;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * Get a user id for passed credentials. This is a form of account
     * validation.
     *
     * @param username Username
     * @param password Password
     * @return User Id
     */
    public int getUserIdForCredentials(String username, String password) {
        Statement stmt = null;
        String query = "SELECT `team_id` FROM `Team` WHERE `username` = '" + username
                + "'" + " AND `password` = '" + password + "' LIMIT 1;";
        try {
            // Execute the SQL
            stmt = getConnection().createStatement();
            ResultSet result = stmt.executeQuery(query);
            if (!result.next()) {
                return -1; // No user with credentials
            }			// Return the user ID
            return result.getInt("team_id");
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    /**
     * Add a user to the system.
     * 
     * @param username Username
     * @param password Password
     * @return Pass or fail
     */
    public boolean addUser(String username, String password) {
        Statement stmt = null;
        String query = "INSERT INTO `Team` (`username`, `password`) VALUES " +
                "('" + username + "', '" + password + "');";
        try {
            stmt = getConnection().createStatement();
            stmt.execute(query);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Get a list of users.
     *
     * @return List of users
     */
    public ArrayList<User> getUsers() {
        Statement stmt = null;
        String query = "SELECT * FROM `Team` ORDER BY `team_id` ASC;";
        try {
            stmt = getConnection().createStatement();
            ResultSet result = stmt.executeQuery(query);
            if (!result.next()) {
                return null;
            }
            // Build the list
            ArrayList<User> users = new ArrayList<>();
            do {
                users.add(new User(result.getInt("team_id"),
                        result.getString("username"), result.getString("password")));
            } while (result.next());
            // Return the list
            return users;
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * Get a list of clarifications initiated by a particular user.
     * 
     * @param userId User ID
     * @return List of clarifications
     */
    public ArrayList<Clarification> getUserClarifications(int userId) {
        return null;
    }

    /**
     * Build the actual connection but check for connection status before
     * creating it.
     */
    private static boolean build() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            dbConnection = DriverManager.getConnection("jdbc:mysql://" + DBMgr.host + "/"
                    + DBMgr.database + "?" + "user=" + DBMgr.username + "&password=" + DBMgr.password);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Return the active connection object. Returns null on error.
     *
     * @return Connection to database
     */
    private static Connection getConnection() {
        try {
            if (dbConnection.isValid(10) || dbConnection.isClosed()) {
                build();
            }
            return dbConnection;
        } catch (SQLException e) {
            return null;
        }
    }
}
