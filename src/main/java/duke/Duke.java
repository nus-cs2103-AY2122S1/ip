package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeExitException;
import duke.gui.Main;
import duke.storage.Storage;
import javafx.application.Application;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main class for Duke.
 */
public class Duke {
    static String border = "--------------------------------------------------";
    private Parser parser;
    private UI ui;
    private TaskArrayList taskList;
    private Path storagePath;
    /**
     * Main entry point for Duke project.
     *
     * @param args unused.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    public Duke(){
        storagePath = Paths.get(".", "data", "duke.txt");
        try {
            this.taskList = Storage.load(storagePath);
        } catch (DukeException | IOException e) {
            this.taskList = new TaskArrayList();
        }
        this.ui = new UI();
        this.parser = new Parser(ui, taskList, storagePath);
    }


    public String getResponse(String userInput) throws DukeExitException{
        try {
            return parser.run(userInput);
        }catch (DukeExitException e){
            throw e;
        } catch (DukeException e) {
            return e.getMessage();
        } finally {
            Storage.dump(taskList, storagePath);
        }
//        return userInput;
    }

}
