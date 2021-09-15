package duchess.task;

import java.time.LocalDateTime;

/**
 * This class implements a ToDo task.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo.
     * @param name the name of the todo task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a simplified representation of the ToDo for easier recovery from save file.
     * @return The file formatted string representation of the ToDo.
     */
    public String toFileFormat() {
        return String.format("T%s,%b", name, isDone);
    }

    /**
     * Returns a string representation of the ToDo, with an [X] marked for done and [ ] as undone.
     * @return the string representation of the ToDo.
     */
    public LocalDateTime getDateTimeStart() {
        return LocalDateTime.MAX;
    }

    @Override
    public String toString() {
        return String.format("[T]" + super.toString());
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ToDo)) {
            return false;
        } else {
            ToDo t = (ToDo) o;
            return t.name.equals(name);
        }
    }
}
