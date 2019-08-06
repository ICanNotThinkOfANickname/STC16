package lesson13;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class ConnectDB {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "123456789";
        Connection connection = connectDB(url, user, password);

        List<String> tablesSQL = new ArrayList<String>();
        tablesSQL.add("CREATE TABLE IF NOT EXISTS \"USER\"(\n" +
                "user_id serial PRIMARY KEY,\n" +
                "name varchar(50) UNIQUE NOT NULL,\n" +
                "birthday TIMESTAMP NOT NULL,\n" +
                "login_id TIMESTAMP NOT NULL,\n" +
                "city varchar(355) NOT NULL,\n" +
                "email varchar(355) UNIQUE NOT NULL,\n" +
                "description TEXT NOT NULL);");

        tablesSQL.add("CREATE TABLE IF NOT EXISTS \"ROLE\"(\n" +
                "role_id serial PRIMARY KEY,\n" +
                "name varchar(50) UNIQUE NOT NULL,\n" +
                "CHECK (name = 'Administration' OR name = 'Clients' OR name = 'Billing'),\n" +
                "description TEXT NOT NULL);");

        tablesSQL.add("CREATE TABLE IF NOT EXISTS \"USER_ROLE\"(\n" +
                "id INTEGER UNIQUE NOT NULL,\n" +
                "role_id integer NOT NULL,\n" +
                "user_id integer NOT NULL,\n" +
                "PRIMARY KEY (role_id, user_id),\t\n" +
                "CONSTRAINT USER_ROLE_user_id_fkey FOREIGN KEY (user_id)\n" +
                "\tREFERENCES \"USER\" (user_id) MATCH SIMPLE\n" +
                "\tON UPDATE NO ACTION ON DELETE NO ACTION,\n" +
                "CONSTRAINT ROLE_role_id_fkey FOREIGN KEY (role_id)\n" +
                "\tREFERENCES \"ROLE\" (role_id) MATCH SIMPLE\n" +
                "\tON UPDATE NO ACTION ON DELETE NO ACTION);");

        createTables(connection, tablesSQL);

        String insertQuery = "INSERT INTO \"USER\" (user_id, name, birthday, login_id, city, email, description)\n" +
                "VALUES (?, ?, '1975-10-05 01:30:00', '2019-08-02 21:09:00', 'Саранск', 'pochta1975@yandex.ru', 'Коренной житель');";
        PreparedStatement insert = connection.prepareStatement(insertQuery);
        insert.setInt(1, 0);
        insert.setString(2, "Николай");
        insert.execute();
        insert.close();

        /*Batch процесс*/
        List<String> names = Arrays.asList("Administration", "Clients", "Billing");
        List<String> descriptions = Arrays.asList("Эмигрант", "Временная прописка", "Нелегал");
        String insertBatchQuery = "INSERT INTO \"ROLE\" (role_id, name, description)\n" +
                "VALUES (?, ?, ?); ";
        try {
            PreparedStatement insertBatch = connection.prepareStatement(insertBatchQuery);
            connection.setAutoCommit(false);
            for (int i = 0; i <= 2; i++) {
                insertBatch.setInt(1, i);
                insertBatch.setString(2, names.get(i));
                insertBatch.setString(3, descriptions.get(i));
                insertBatch.addBatch();
            }
            insertBatch.executeBatch();
            connection.commit();
            insertBatch.close();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }

        /*Параметризированная выборка по login_ID и name*/
        String query = "SELECT * FROM \"USER\";";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String resultSetLogin = resultSet.getString("login_ID");
            String resultSetName = resultSet.getString("name");
            System.out.println("Параметризированная выборка по логину и имени: " + "login_ID: " + resultSetLogin + "; name: " + resultSetName);
        }
        resultSet.close();
        statement.close();

        /*Выполнение 2-х SQL операции Insert и установка точки сохранения(SAVEPOINT)*/
        Savepoint savepointOne = connection.setSavepoint("SavepointOne");
        try {
            insertRow(connection, 1, "Константин", "1980-05-25 10:00:00", "2019-08-01 11:30:00", "Калининград",
                    "pochta1980@yandex.ru", "Временная прописка");
            insertRow(connection, 0, 0, 0);
            insertRow(connection, 1, 1, 1);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback(savepointOne);
            System.out.println("SQLException");
        }

        /*Выполнение 2-х SQL операции Insert, установка точки сохранения(SAVEPOINT A), ввод некорректных данных*/
        Savepoint savepointA = connection.setSavepoint("SavepointA");
        try {
            insertRow(connection, 3, "Некорректные данные", "Нелегал");
        } catch (SQLException e) {
            connection.rollback(savepointA);
            System.out.println("SQLException, возврат к точке A");
        }

        connection.close();
    }

    public static Connection connectDB(String url, String user, String password) throws SQLException {
        Connection connection = null;
        DriverManager.registerDriver(new Driver());
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public static void createTables(Connection cn, List<String> tablesSQL) throws SQLException {
        PreparedStatement preparedStatement = null;
        for (String elem : tablesSQL) {
            preparedStatement = cn.prepareStatement(elem);
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();
    }

    /*Внесение записей в таблицу USER*/
    public static void insertRow(Connection connection, int user_id, String name, String dateOfBirth, String login_id,
                                 String city, String email, String description) throws SQLException {
        String insert = "INSERT INTO \"USER\" (user_id, name, birthday, login_id, city, email, description)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setInt(1, user_id);
        preparedStatement.setString(2, name);
        preparedStatement.setTimestamp(3, stringToTimestampConverter(dateOfBirth));
        preparedStatement.setTimestamp(4, stringToTimestampConverter(login_id));
        preparedStatement.setString(5, city);
        preparedStatement.setString(6, email);
        preparedStatement.setString(7, description);
        preparedStatement.execute();
        preparedStatement.close();
    }

    /*Внесение записи в таблицу ROLE*/
    public static void insertRow(Connection connection, int role_id, String name, String description) throws SQLException {
        String insert = "INSERT INTO \"ROLE\" (role_id, name, description)" +
                "VALUES (?, ?, ?);";
        PreparedStatement prepStat = connection.prepareStatement(insert);
        prepStat.setInt(1, role_id);
        prepStat.setString(2, name);
        prepStat.setString(3, description);
        prepStat.execute();
        prepStat.close();
    }

    /*Внесение записи в таблицу USER_ROLE*/
    public static void insertRow(Connection connection, int id, int role_id, int user_id) throws SQLException {
        String insert = "INSERT INTO \"USER_ROLE\" (id, role_id, user_id)" +
                "VALUES (?, ?, ?);";
        PreparedStatement prepStat = connection.prepareStatement(insert);
        prepStat.setInt(1, id);
        prepStat.setInt(2, role_id);
        prepStat.setInt(3, user_id);
        prepStat.execute();
        prepStat.close();
    }

    public static Timestamp stringToTimestampConverter(String givenString) {
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(givenString);
        return timestamp;
    }

    private static class Driver implements java.sql.Driver {
        @Override
        public Connection connect(String url, Properties info) throws SQLException {
            return null;
        }

        @Override
        public boolean acceptsURL(String url) throws SQLException {
            return false;
        }

        @Override
        public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
            return new DriverPropertyInfo[0];
        }

        @Override
        public int getMajorVersion() {
            return 0;
        }

        @Override
        public int getMinorVersion() {
            return 0;
        }

        @Override
        public boolean jdbcCompliant() {
            return false;
        }

        @Override
        public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            return null;
        }
    }
}