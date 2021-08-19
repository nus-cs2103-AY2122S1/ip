public class TodoTask extends Task {

    /**
     * Constructor for a TodoTask object.
     * @param name
     */
    public TodoTask(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
