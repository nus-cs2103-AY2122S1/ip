package bern.model;

/**
 * A class to represents a Task of type "todo".
 */
public class ToDo extends Task{
    /**
     * Constructor for ToDo.
     *
     * @param description The description of the Task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * A method to return the String representation of the class.
     *
     * @return The String representation of the class.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ToDo)) {
            return false;
        } else {
            ToDo compared = (ToDo) obj;
            if (compared.description.equals(this.description)) {
                System.out.println("yo guys im in");
                return true;
            } else {
                return false;
            }
        }
    }
}
