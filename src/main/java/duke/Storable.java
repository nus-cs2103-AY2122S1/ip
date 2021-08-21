package duke;

public interface Storable {
    TaskList readTasksFromData() throws DukeException;

    void saveTasksToData(TaskList taskList) throws DukeException;
}
