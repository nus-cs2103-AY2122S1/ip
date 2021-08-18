public class DukeException extends Exception{
    private String description;

    DukeException(String description) {
        this.description = description;
    }
}
