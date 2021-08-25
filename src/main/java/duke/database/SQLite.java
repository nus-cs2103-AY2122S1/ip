package duke.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DatabaseAccessException;
import duke.task.Task;
import duke.task.TaskType;

/**
 * SQLite implementation of accessing the task database.
 */
public class SQLite extends Database {
    private static final String SQLITE_CREATE_TASK_TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS " + TASK_TABLE_NAME
            + " (`type` STRING NOT NULL, `name` TEXT NOT NULL, `completed` INTEGER NOT NULL, `date` TEXT);";

    @Override
    public Connection getSqlConnection() {
        File dataFile = this.createOrOpenDataFile();
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFile);
            return connection;
        } catch (SQLException ex) {
            throw new DatabaseAccessException("SQLite exception on initialize");
        } catch (ClassNotFoundException ex) {
            throw new DatabaseAccessException("You need the SQLite JBDC library. Google it. Put it in /lib folder.");
        }
    }

    @Override
    public void load() {
        this.connection = getSqlConnection();
        try {
            Statement s = this.connection.createStatement();
            s.executeUpdate(SQLITE_CREATE_TASK_TABLE_STATEMENT);
            s.close();
        } catch (SQLException e) {
            throw new DatabaseAccessException("Unable to access SQLite database...");
        }
        initialize();
    }

    @Override
    public List<Task> getTasksList() {
        List<Task> list = new ArrayList<>();
        try {
            this.connection = getSqlConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + TASK_TABLE_NAME + ";");
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
            throw new DatabaseAccessException("Unable to access SQLite database...");
        }
        return list;
    }

    @Override
    public void addTask(Task task) {
        TaskType type = task.getType();
        String name = task.getName();
        int completed = task.isCompleted() ? 1 : 0;
        String date = task.getDate() == null ? null : task.getDate().toString();
        try {
            connection = getSqlConnection();
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO " + TASK_TABLE_NAME + " (type, name, completed, date) VALUES('"
                            + type + "', '" + name + "', " + completed + ", '" + date + "')");
            ps.executeUpdate();
            close(ps);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Unable to access SQLite database...");
        }
    }

    @Override
    public Task removeTask(int index) {
        Task result = null;
        try {
            this.connection = getSqlConnection();
            PreparedStatement ps = connection
                    .prepareStatement("SELECT rowid, * FROM " + TASK_TABLE_NAME + " ORDER BY rowid;");
            ResultSet rs = ps.executeQuery();

            int row = 0;
            while (rs.next() && row < index) {
                if (++row == index) {
                    TaskType type = TaskType.valueOf(rs.getString("type"));
                    String name = rs.getString("name");
                    boolean completed = rs.getBoolean("completed");
                    String date = rs.getString("date");
                    result = this.createTask(type, name, completed, date);

                    int rowid = rs.getInt("rowid");
                    ps = connection
                            .prepareStatement("DELETE FROM " + TASK_TABLE_NAME + " WHERE rowid = " + rowid + ";");
                    ps.executeUpdate();
                }
            }
            close(ps, rs);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Unable to access SQLite database...");
        }
        return result;
    }

    @Override
    public Task markCompleted(int index) {
        Task result = null;
        try {
            this.connection = getSqlConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT rowid, * FROM " + TASK_TABLE_NAME + ";");
            ResultSet rs = ps.executeQuery();

            int row = 0;
            while (rs.next() && row < index) {
                if (++row == index) {
                    TaskType type = TaskType.valueOf(rs.getString("type"));
                    String name = rs.getString("name");
                    String date = rs.getString("date");
                    result = this.createTask(type, name, true, date);

                    int rowid = rs.getInt("rowid");
                    ps = connection.prepareStatement(
                            "UPDATE " + TASK_TABLE_NAME + " SET completed = " + 1 + " WHERE rowid = " + rowid + ";");
                    ps.executeUpdate();
                }
            }
            close(ps, rs);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Unable to access SQLite database...");
        }
        return result;
    }

    @Override
    public List<Task> findTasksByName(String pattern) {
        List<Task> list = new ArrayList<>();
        try {
            this.connection = getSqlConnection();
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM " + TASK_TABLE_NAME + " WHERE name LIKE '" + pattern + "';");
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
            throw new DatabaseAccessException("Unable to access SQLite database...");
        }
        return list;
    }

}