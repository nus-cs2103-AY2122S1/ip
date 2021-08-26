package duke;

public class Ui {
    static final String introString = "Hey there! I'm Good duke.Duke. How many I help you today?";
    static final String outroString = "That was an excellent chat - I look forward to seeing you again soon!";
    static final String readSaveString = "Sorry, there was a problem reading the save file :(";
    static final String writeSaveString = "Sorry, there was a problem saving your tasks :(";
    static final String newSaveString = "Empty save file detected - loading a blank list.";
    static final String SHOW_MATCHES_STRING = "Here are the matching tasks in your list:";
    static final String EXPECTED_SEARCH_QUERY = "The search query of a find operation cannot be empty.";

    private String addedString(Task task, int size) {
        return String.format("Alright, I've added this task: \n\t%s\nNow, you have %d tasks in the list.\n", task, size);
    }

    private String doneString(Task task) {
        return String.format("Certainly, I've marked this task as done: \n\t%s\n", task);
    }

    private String deletedString(Task task, int size) {
        return String.format("Certainly, I've deleted this task: \n\t%s\nNow, you have %d tasks in the list.\n", task, size);
    }

    public static String taskListString(TaskList taskList) {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            output += String.format("%d. %s\n", i + 1, taskList.get(i));
        }
        return output;
    }

    public void print(String str) {
        String horizontalLine = "________________________________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println(str);
        System.out.println(horizontalLine);
    }

    public void showIntro() {
        print(introString);
    }

    public void showOutro() {
        print(outroString);
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
        print(readSaveString);
    }

    public void showWriteSaveError() {
        print(writeSaveString);
    }

    public void showNewSave() {
        print(newSaveString);
    }
    
    public void showMatches(TaskList tasks) {
        print(SHOW_MATCHES_STRING + "\n" + taskListString(tasks));
    }
}
