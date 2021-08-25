import java.util.ArrayList;

public class Ui {
    private static final String introString = "Hey there! I'm Good Duke. How many I help you today?";
    private static final String outroString = "That was an excellent chat - I look forward to seeing you again soon!";
    private static final String readSaveString = "Sorry, there was a problem reading the save file :(";
    private static final String writeSaveString = "Sorry, there was a problem saving your tasks :(";
    private static final String newSaveString = "Empty save file detected - loading a blank list.";

    private String addedString(Task task, int size) {
        return String.format("Alright, I've added this task: \n\t%s\nNow, you have %d tasks in the list.\n", task, size);
    }

    private String doneString(Task task) {
        return String.format("Certainly, I've marked this task as done: \n\t%s\n", task);
    }

    private String deletedString(Task task, int size) {
        return String.format("Certainly, I've deleted this task: \n\t%s\nNow, you have %d tasks in the list.\n", task, size);
    }

    public static String taskListString(ArrayList<Task> taskList) {
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

    public void showTasks(ArrayList<Task> tasks) {
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
}
