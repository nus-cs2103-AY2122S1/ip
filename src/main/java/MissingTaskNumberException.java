public class MissingTaskNumberException extends Exception {
    public MissingTaskNumberException() { }

    @Override
    public String toString() {
        return "OOPS!!! To delete a task, the task number cannot be empty.";
    }
}
