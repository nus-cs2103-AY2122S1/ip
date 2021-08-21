package duke.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import duke.Response;
import duke.task.Task;
import duke.task.TaskType;

/**
 * TODO Use Duke Exceptions for exceptions...
 */
public class SQLite extends Database {

    private static final String SQLITE_CREATE_TASK_TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS " + TASK_TABLE_NAME
            + " (`type` STRING NOT NULL, `name` TEXT NOT NULL, `completed` INTEGER NOT NULL, `date` TEXT);";

    @Override
    public Connection getSQLConnection() {
        File dataFile = this.createOrOpenDataFile();
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFile);
            return connection;
        } catch (SQLException ex) {
            new Response("SQLite exception on initialize").print();
        } catch (ClassNotFoundException ex) {
            new Response("You need the SQLite JBDC library. Google it. Put it in /lib folder.").print();
        }
        return null;
    }

    @Override
    public void load() {
        connection = getSQLConnection();
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(SQLITE_CREATE_TASK_TABLE_STATEMENT);
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initialize();
    }

    @Override
    public List<Task> getTasksList() {
        List<Task> list = new ArrayList<>();
        try {
            this.connection = getSQLConnection();
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM " + TASK_TABLE_NAME + " WHERE player = '" + null + "';");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TaskType type = TaskType.valueOf(rs.getString("type"));
                String name = rs.getString("name");
                boolean completed = rs.getBoolean("completed");
                String date = rs.getString("date");
                list.add(this.createTask(type, name, completed, date));
            }
            close(ps);
        } catch (SQLException ex) {
            // TODO
        }
        return list;
    }

    @Override
    public void addTask(Task task) {
        TaskType type = task.getType(); // TODO
        String name = task.getName();
        int completed = task.isCompleted() ? 1 : 0;
        String date = task.getDate();
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection
                    .prepareStatement("REPLACE INTO " + TASK_TABLE_NAME + " (type, name, completed, date) VALUES('"
                            + type + "', '" + name + "', " + completed + ", '" + date + "')");
            ps.executeUpdate();
            close(ps);
        } catch (SQLException ex) {
            // TODO
        }
    }

    private File createOrOpenDataFile() {
        File dataFolder = new File(this.getDataFolder(), DATABASE_NAME + ".db");
        if (!dataFolder.exists()) {
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                new Response("Unable to create data file!").print();
                e.printStackTrace();
            }
        }
        return dataFolder;
    }

}