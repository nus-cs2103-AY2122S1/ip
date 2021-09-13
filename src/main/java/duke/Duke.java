package duke;

import java.io.File;
import java.io.IOException;

import duke.command.Command;
import duke.data.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Duke programme implements a bot that help users to record the tasks they have.
 */
public class Duke {
    private static final String STORAGE_DIRECTORY_PATH = "data/duke.txt";

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private File dataFile;

    public Duke() { //needed for Application.launch() to work
        this.dataFile = new File(STORAGE_DIRECTORY_PATH);
        ui = new Ui();
        storage = new Storage(STORAGE_DIRECTORY_PATH);
        //try {
//            if (!this.dataFile.exists()) {
//                boolean isFileCreated = dataFile.createNewFile();
//                taskList = new TaskList();
//            } else {
//                taskList = new TaskList(storage.load());
//            }

//            if (!isFileCreated) {
//                taskList = new TaskList(storage.load());
//            } else {
//                taskList = new TaskList();
//            }

            try {
                if (!this.dataFile.exists()) {
                    try {
                        if (this.dataFile.getParentFile().mkdirs()) {
                            System.out.println("Directory for file created.");
                        }
                        if (this.dataFile.createNewFile()) {
                            System.out.println("File created: duke.txt");
                        }
                        taskList = new TaskList();
                    } catch (IOException e) {
                        System.out.println("Error has occurred when creating file!");
                        e.printStackTrace();
                    }
                } else {
                    taskList = new TaskList(storage.load());
                }
            } catch (IOException | DukeException e) {
                e.printStackTrace();
            }
        }


//    } catch (IOException e) {
//            e.printStackTrace();
//            //todo do we need to involve the case whereby this error occurs and show the error message to the user?
//        }
//    }


    /**
     * Returns a string representing the response from Duke based on the user input.
     *
     * @param input the user input
     * @return a response string from Duke
     */
    public String getResponse(String input) {
        String response;
        Parser p = new Parser();
        try {
            Command c = p.parse(input);
            response = c.execute(taskList, ui, storage);
        } catch (DukeException e) {
//            e.printStackTrace();
            response = ui.showErrorMessage(e.getMessage());
            //timer
        }
        return response;
    }
}
