package duke;

import java.util.Scanner;

/**
 * Main class for the duke assistant bot.
 * 
 * @author Tianqi-Zhu
 */
public class Duke {

    private TaskList taskList;

    public Duke(TaskList taskList) {
        this.taskList = taskList;
    }

    protected TaskList getTaskList() {
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