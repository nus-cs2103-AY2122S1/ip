package duke.processor;

import duke.exception.DukeExcpetion;
import duke.processor.Parser;
import duke.processor.Storage;
import duke.processor.TaskList;
import duke.processor.Ui;

import java.util.Scanner;

/**
 * Main class for the duke assistant bot(cli).
 * 
 * @author Tianqi-Zhu
 */
public class Duke {

    private TaskList taskList;

    public Duke(TaskList taskList) {
        this.taskList = taskList;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * run command line duke
     */
    private static void run() {
        Ui.greet();
        TaskList taskList = Storage.load();
        Scanner input = new Scanner(System.in);
        while (true) {
            String newInput = input.nextLine();
            if (newInput.equals("bye")) break; 
            try {
                Parser.parse(newInput, taskList).execute(taskList);
            } catch (DukeExcpetion e) {
                Ui.printString(e.toString());
            }
        }
        input.close();
        Ui.exit();
    }
}