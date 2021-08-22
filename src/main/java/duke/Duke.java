package duke;

import duke.logic.LCommandParser;
import duke.logic.LPrintTask;
import duke.logic.LStorage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Duke is a personal assistant that allows users to keep track of events, deadlines and things to do.
 * The main method will start the personal assistant in the console.
 */
public class Duke {
    private final TaskList taskList;
    private final LStorage lStorage;

    public Duke(String filePath, int listLimit) {
        this.taskList = new TaskList(listLimit);
        this.lStorage = new LStorage(filePath, taskList);
    }

    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I am\n" + logo);
        System.out.println("What can I do for you today?");
        System.out.println("------------------");
        String input;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            try {
                LCommandParser commandParser = new LCommandParser(input, taskList, lStorage);
                if (commandParser.willExit()) {
                    return;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("------------------");
        }

        sc.close();
    }

    public static void main(String[] args) {
        new Duke("./dukedata.txt", 100).run();
    }


}
