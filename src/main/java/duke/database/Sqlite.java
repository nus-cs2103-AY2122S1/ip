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
import duke.task.DurationTask;
import duke.task.Task;
import duke.task.TaskType;

/**
 * SQLite implementation of accessing the task database.
 */
public class Sqlite extends Database {
    private static final String SQLITE_CREATE_TASK_TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS " + TASK_TABLE_NAME
            + " (`type` STRING NOT NULL, `name` TEXT NOT NULL, `completed` INTEGER NOT NULL, `date` TEXT, "
            + "`duration` INTEGER);";

    @Override
    public Connection getSqlConnection() {
        File dataFile = this.getDataFile();
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
                boolean isCompleted = rs.getBoolean("completed");
                String date = rs.getString("date");
                int duration = rs.getInt("duration");
                list.add(this.createTask(type, name, isCompleted, date, duration));
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
        int isCompleted = task.isCompleted() ? 1 : 0;
        String date = task.getDate() == null ? null : task.getDate().toString();
        int duration = 0;
        if (task instanceof DurationTask) {
            duration = ((DurationTask) task).getDuration();
        }
        try {
            connection = getSqlConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO " + TASK_TABLE_NAME + " (type, name, completed, date, duration) VALUES('" + type
                            + "', '" + name + "', " + isCompleted + ", '" + date + "', " + duration + ")");
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
                    boolean isCompleted = rs.getBoolean("completed");
                    String date = rs.getString("date");
                    int duration = rs.getInt("duration");
                    result = this.createTask(type, name, isCompleted, date, duration);

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
                    int duration = rs.getInt("duration");
                    result = this.createTask(type, name, true, date, duration);

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
                boolean isCompleted = rs.getBoolean("completed");
                String date = rs.getString("date");
                int duration = rs.getInt("duration");
                list.add(this.createTask(type, name, isCompleted, date, duration));
            }
            close(ps);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Unable to access SQLite database...");
        }
        return list;
    }

}
