package duke.exceptions;

public class SaveDirectoryError extends DukeException {
    /**
     * Occurs when directory to save file in doesn't exist.
     */
    public SaveDirectoryError(){
        super ("Oh now! I guess the diwectowwy doesn't exist.");
    }
}