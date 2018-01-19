/**
 * Java. Level 2. Lesson 7 Homework

 * @author Sergey Zhurov
 * @version  Jan 19, 2018
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

class MakeDBFile implements IConstants {

    final String NAME_TABLE = "users";
    final String SQL_CREATE_TABLE =
        "CREATE TABLE " + NAME_TABLE +
        "(login  CHAR(6) PRIMARY KEY NOT NULL," +
        " passwd CHAR(6) NOT NULL);";
    final String SQL_SELECT = "SELECT * FROM " + NAME_TABLE + ";";

    public static void main(String[] args) {
        new MakeDBFile();
    }

    MakeDBFile() {
        try {
            // loads a class, including running its static initializers
            Class.forName(DRIVER_NAME);
            // attempts to establish a connection to the given database URL
            Connection connect = DriverManager.getConnection(SQLITE_DB);
            // сreates an object for sending SQL statements to the database
            Statement stmt = connect.createStatement();
            // try to create table
            try {
                stmt.executeUpdate(SQL_CREATE_TABLE);
                System.out.println("Database with accounts table created.");
            } catch (SQLException e) {
                System.out.println("Database with accounts table already exist.");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while(true) {
                System.out.println("Enter command or \"help\" for help.");
                try {
                    String[] commands = reader.readLine().split(" ");
                    switch (commands[0]) {
                        case "help":
                            printRules();
                            break;
                        case "exit":
                            reader.close();
                            stmt.close();
                            connect.close();
                            System.exit(0);
                            break;
                        case "create":
                            create(stmt, commands[1], commands[2]);
                            break;
                        case "print":
                            read(stmt);
                            break;
                        case "update":
                            update(stmt, commands[1], commands[2]);
                            break;
                        case "delete":
                            delete(stmt, commands[1]);
                            break;
                    }
                } catch (IOException | ArrayIndexOutOfBoundsException | SQLException e) {
                    System.out.println("Wrong parameters.");
                    System.out.println();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
        }
    }

    private void create(Statement stmt, String login, String pass) throws SQLException {
        stmt.executeUpdate("INSERT INTO " + NAME_TABLE +
                " (login, passwd) " +
                "VALUES ('" + login + "', '" + pass + "');");
        System.out.println("Account created.");
    }

    private void read(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery(SQL_SELECT);
        System.out.println();
        System.out.println("LOGIN\tPASSWD");
        while (rs.next())
            System.out.println(
                    rs.getString("login") + "\t" +
                            rs.getString(PASSWD_COL));
        rs.close();
        System.out.println();
    }

    private void update (Statement stmt, String login, String pass) throws SQLException {
        stmt.executeUpdate("UPDATE " + NAME_TABLE +
                " SET login = \"" + login + "\", passwd = \"" + pass + "\" WHERE login = \"" + login + "\";");
        System.out.println("Password updated.");
    }

    private void delete (Statement stmt, String login) throws SQLException {
        stmt.executeUpdate("DELETE FROM " + NAME_TABLE +" WHERE login = \"" + login + "\";");
        System.out.println("Account deleted.");
    }

    private void printRules() {
        System.out.println("Enter: ");
        System.out.println("\"create <login> <pass>\"\t to create new user");
        System.out.println("\"print\"                \t to print users table");
        System.out.println("\"update <login> <pass>\"\t to update password");
        System.out.println("\"delete <login>\"       \t to delete user");
        System.out.println("\"exit\"                 \t to exit the program");
    }
}