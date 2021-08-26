package duke;

public class Ui {
    static final String EXPECTED_DONE_INDEX_GOT_NONE = "You need to indicate which task number should be marked as done.";
    static final String EXPECTED_DONE_INDEX_GOT_OTHER = "The task to be marked as done should be indicated its list index.";
    static final String EXPECTED_TO_DO_DESCRIPTION = "The description of a todo cannot be empty.";
    static final String EXPECTED_DEADLINE_DESCRIPTION = "The description of a deadline cannot be empty.";
    static final String EXPECTED_DEADLINE_BY = "The done-by date of a deadline cannot be empty.";
    static final String EXPECTED_EVENT_DESCRIPTION = "The description of an event cannot be empty.";
    static final String EXPECTED_EVENT_AT = "The timing of an event cannot be empty.";
    static final String EXPECTED_DELETED_INDEX_GOT_NONE = "You need to indicate which task number should be deleted.";
    static final String EXPECTED_DELETED_INDEX_GOT_OTHER = "The task to be deleted should be indicated its list index.";
    static final String UNRECOGNISED_OPERATION = "Sorry, I do not understand this command.";
    private static final String INTRO_STRING = "Hey there! I'm Good duke.Duke. How many I help you today?";
    private static final String OUTRO_STRING = "That was an excellent chat - I look forward to seeing you again soon!";
    private static final String READ_SAVE_STRING = "Sorry, there was a problem reading the save file :(";
    private static final String WRITE_SAVE_STRING = "Sorry, there was a problem saving your tasks :(";
    private static final String NEW_SAVE_STRING = "Empty save file detected - loading a blank list.";

    public static String taskListString(TaskList taskList) {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            output += String.format("%d. %s\n", i + 1, taskList.get(i));
        }
        return output;
    }

    private String addedString(Task task, int size) {
        return String.format("Alright, I've added this task: \n" +
                        "\t%s\n" +
                        "Now, you have %d tasks in the list.\n",
                task, size);
    }

    private String doneString(Task task) {
        return String.format("Certainly, I've marked this task as done: \n" +
                        "\t%s\n",
                task);
    }

    private String deletedString(Task task, int size) {
        return String.format("Certainly, I've deleted this task: \n" +
                        "\t%s" +
                        "\nNow, you have %d tasks in the list.\n",
                task, size);
    }

    public void print(String str) {
        String horizontalLine = "________________________________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println(str);
        System.out.println(horizontalLine);
    }

    public void showIntro() {
        print(INTRO_STRING);
    }

    public void showOutro() {
        print(OUTRO_STRING);
    }

    public void showAdded(Task task, int size) {
        print(addedString(task, size));
    }

    public void showDone(Task task) {
        print(doneString(task));
    }

    public void showDeleted(Task task, int size) {
        print(deletedString(task, size));
    }

    public void showTasks(TaskList tasks) {
        print(taskListString(tasks));
    }

    public void showReadSaveError() {
        print(READ_SAVE_STRING);
    }

    public void showWriteSaveError() {
        print(WRITE_SAVE_STRING);
    }

    public void showNewSave() {
        print(NEW_SAVE_STRING);
    }
}