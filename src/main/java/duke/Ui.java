package duke;
public class Ui {

    public Ui() {

    }

    public static void print(String text) {
        System.out.println("=======================================");
        text.lines().map(x -> "    " + x).forEach(x -> System.out.println(x));
        System.out.println("=======================================");
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
            duke.Task currentTask = TaskList.getCurrentTask(i - 1);
            sentence = sentence + i + "." + currentTask.toString() + "\n";
        }
        print(sentence);
    }
}
