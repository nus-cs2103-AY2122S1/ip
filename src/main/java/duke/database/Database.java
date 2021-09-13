package duke.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import duke.exception.DatabaseAccessException;
import duke.exception.DatabaseFileException;
import duke.task.DeadlineTask;
import duke.task.DurationTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.ToDoTask;

/**
 * Encapsulates a database access and necessary methods for Duke's list.
 */
public abstract class Database {
    /** Name of database table. */
    protected static final String DATABASE_NAME = "tasks_data";
    protected static final String TASK_TABLE_NAME = "tasks";
    protected Connection connection;

    /**
     * Returns connection established by the database loader.
     *
     * @return SQL connection
     */
    public abstract Connection getSqlConnection();

    /**
     * Executes create table statement.
     */
    public abstract void load();

    /**
     * Initializes and tests SQL connection by attempting to execute select
     * statements from respective tables in the database.
     */
    protected void initialize() {
        connection = this.getSqlConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + TASK_TABLE_NAME);
            ResultSet rs = ps.executeQuery();
            close(ps, rs);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Unable to access SQLite database...");
        }
    }

    /**
     * Returns the list of tasks stored in the database.
     *
     * @return list of tasks stored
     */
    public abstract List<Task> getTasksList();

    /**
     * Adds a task to the database.
     *
     * @param task to be added
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
     * Marks a task as completed in the database.
     *
     * @param index index of the task, {@code 1} for the first task, {@code 2} for
     *              the second task etc.
     * @return task that is marked completed, {@code null} if there are no tasks at
     *         the specified index
     */
    public abstract Task markCompleted(int index);

    /**
     * Finds tasks by searching for a keyword/phrase.
     *
     * @param pattern keyword/phrase to be searched
     * @return a list of tasks satisfying the condition mentioned above
     */
    public abstract List<Task> findTasksByName(String pattern);

    /**
     * Recreates a task based on provided information. Used when retrieving task
     * from SQL database.
     *
     * @param type        type of task
     * @param name        name of task
     * @param isCompleted {@code true} if the task has been completed
     * @param date        date of the task, nullable
     * @return the recreated task
     */
    protected Task createTask(TaskType type, String name, boolean isCompleted, String date, int duration) {
        LocalDate localDate = Optional.ofNullable(date).filter(str -> !str.equals("null"))
                .map(str -> LocalDate.parse(str)).orElse(null);
        switch (type) {
        case TODO:
            return new ToDoTask(name, isCompleted);
        case DEADLINE:
            return new DeadlineTask(name, isCompleted, localDate);
        case EVENT:
            return new EventTask(name, isCompleted, localDate);
        case DURATION:
            return new DurationTask(name, duration, isCompleted);
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
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (this.connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Unable to close SQLite connection...");
        }
    }

    /**
     * Closes {@link PreparedStatement} and {@link Connection} to the database.
     *
     * @param ps   PreparedStatement of the database
     * @param conn Connection to the database
     */
    protected void close(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (this.connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Unable to close SQLite connection...");
        }
    }

    /**
     * Gets directory for which the database will be saved into.
     *
     * @return database directory
     */
    private File getDataFolder() {
        File dataFolder = new File("data");
        dataFolder.mkdir();
        return dataFolder;
    }

    protected File getDataFile() {
        File dataFile = new File(this.getDataFolder(), DATABASE_NAME + ".db");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new DatabaseFileException("Unable to create data file!");
            }
        }
        return dataFile;
    }

}
