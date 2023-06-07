package linkactivity.linkactivity;

import java.io.BufferedReader;
        import java.io.FileReader;
        import java.sql.Connection;
        import java.sql.DriverManager;

public class DBConnection {

    private static final String DATABASE_NAME = "mydb";
    private static final String DATABASE_USER = "root";
    private static final String PASSWORD_FILE_PATH = "password.txt"; // Percorso del file contenente la password
    private static final String URL_DB = "jdbc:mysql://127.0.0.1/" + DATABASE_NAME;
    private static Connection connection;

    private DBConnection() {

    }

    public static Connection getDBConnection() {

        try {
            if (connection == null) {
                String databasePass = readPasswordFromFile();
                connection = DriverManager.getConnection(URL_DB, DATABASE_USER, databasePass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static String readPasswordFromFile() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(PASSWORD_FILE_PATH));
        String password = reader.readLine();
        reader.close();
        return password;
    }
}
