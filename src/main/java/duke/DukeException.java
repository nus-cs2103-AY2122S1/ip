package duke;

/**
 * Represents exceptions specific to Duke
 */
public class DukeException extends Exception {
    public DukeException(String str){
        super("🤬 " + str);
    }
}
