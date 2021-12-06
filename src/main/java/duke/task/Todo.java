package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a comma separated string representation of the Todo's data
     * for data storage.
     *
     * @return String that represents the Todo's data
     */

    @Override
    public String getData() {
        return String.format("T,%s", super.getData());
    }


    /**
     * Updates the Todo based on the given updated data.
     * @param updatedData is in the form {description}
     */
    @Override
    public void update(String updatedData) {
        assert updatedData.split("/").length == 1 : "only 1 field can be updated for todos";
        this.description = updatedData;
    }

    /**
     * Returns the string representation of a Todo.
     *
     * @return String representing a todo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

}
