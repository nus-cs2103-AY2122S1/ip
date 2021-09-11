package duke;

import java.util.ArrayList;

public class Ui {

    private static final String separator = "=======================================";

    public Ui() {

    }

    public static void print(String text) {
        System.out.println(separator);
        text.lines().map(x -> "    " + x).forEach(x -> System.out.println(x));
        System.out.println(separator);
    }

    public static void done(String doneEntry) throws DukeException {
        int taskNumber = Integer.parseInt(doneEntry.substring(5,6));
        if (taskNumber > TaskList.noOfTasks()) {
            throw new DukeException("Sorry ☹, please enter a valid task to complete!");
        }
        duke.Task doneTask = TaskList.getCurrentTask(taskNumber - 1);
        doneTask.markAsDone();
        print("Congratulations on finishing this task!\n [X] " + doneTask.getDescription());
    }

    public static void invalidInput() throws DukeException {
        throw new DukeException("Sorry ☹, please enter a valid command!");
    }

    public static void list() {
        int len = TaskList.noOfTasks();
        String sentence = "";
        for (int i = 1; i < len + 1; i++) {
            Task currentTask = TaskList.getCurrentTask(i - 1);
            sentence = sentence + i + "." + currentTask.toString() + "\n";
        }
        if (len == 0) {
            print("You currently have no pending tasks!");
        } else {
            sentence = "Here are your current tasks!" + "\n" + sentence;
            print(sentence);
        }
    }

    public static void find(String findWord) {
        String word = findWord.substring(5);
        int len = TaskList.noOfTasks();
        String sentence = "";
        for (int i = 1; i < len + 1; i++) {
            Task currentTask = TaskList.getCurrentTask(i - 1);
            String currentDescription = currentTask.getDescription();
            if (currentDescription.contains(word)) {
                sentence = sentence + i + "." + currentTask.toString() + "\n";
            }
        }
        if (sentence.equals("")) {
            print("Sorry! I can't find any task that matches your keyword ☹");
        } else {
            sentence = "Here are the matching tasks I found:" + "\n" + sentence;
            print(sentence);
        }
    }
}
