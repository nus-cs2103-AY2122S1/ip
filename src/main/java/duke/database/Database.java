package duke.database;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.ToDoTask;

public abstract class Database {
    protected Connection connection;
    /** Name of database table. */
    protected static final String DATABASE_NAME = "tasks_data";
    protected static final String TASK_TABLE_NAME = "tasks";

    /**
     * Returns connection established by the database loader.
     *
     * @return SQL connection
     */
    public abstract Connection getSQLConnection();

    /**
     * Executes create table statement.
     */
    public abstract void load();

    /**
     * Initializes and tests SQL connection by attempting to execute select
     * statements from respective tables in the database.
     */
    public void initialize() {
        connection = this.getSQLConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + TASK_TABLE_NAME);
            ResultSet rs = ps.executeQuery();
            close(ps, rs);
        } catch (SQLException ex) {
            // TODO
        }
    }

    /**
     * Returns the list of custom shops (represented by its custom model data)
     * unlocked by the player.
     *
     * @param player player of interest
     * @return list of shops unlocked by the player
     */
    public abstract List<Task> getTasksList();

    /**
     * Updates the shop owned by player to database.
     * 
     * @param playerID player's UUID
     * @param number   updated number of shops owned
     */
    public abstract void addTask(Task task);

    /**
     * Removes a task from the database.
     * 
     * @param index index of the task, {@code 1} for the first task, {@code 2} for
     *              the second task etc.
     * @return {@code null} if there are no tasks at the specified index
     */
    public abstract Task removeTask(int index);

    /**
     * Mark a task as completed in the database.
     * 
     * @param index index of the task, {@code 1} for the first task, {@code 2} for
     *              the second task etc.
     * @return task that is marked completed, {@code null} if there are no tasks at
     *         the specified index
     */
    public abstract Task markCompleted(int index);

    /**
     * Recreate a task based on provided information. Used when retrieving task from
     * SQL database.
     * 
     * @param type      type of task
     * @param name      name of task
     * @param completed {@code true} if the task has been completed
     * @param date      date of the task, nullable
     * @return
     */
    protected Task createTask(TaskType type, String name, boolean completed, String date) {
        switch (type) {
        case TODO:
            return new ToDoTask(name, completed);
        case DEADLINE:
            return new DeadlineTask(name, completed, date);
        case EVENT:
            return new EventTask(name, completed, date);
        default:
            return null;
        }
    }

    /**
     * Releases both the {@link PreparedStatement} and {@link ResultSet} of the
     * database.
     *
     * @param ps PreparedStatement of the database
     * @param rs ResultSet of the database
     */
    protected void close(PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
            if (this.connection != null)
                connection.close();
        } catch (SQLException ex) {
            // TODO
        }
    }

    /**
     * Close {@link PreparedStatement} and {@link Connection} to the database.
     *
     * @param ps   PreparedStatement of the database
     * @param conn Connection to the database
     */
    protected void close(PreparedStatement ps) {
        try {
            if (ps != null)
                ps.close();
            if (this.connection != null)
                connection.close();
        } catch (SQLException ex) {
            // TODO
        }
    }

    /**
     * Get directory for which the database will be saved into.
     * 
     * @return database directory
     */
    protected File getDataFolder() {
        return new File("data");
    }
}
