package duke;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to create tasks (including deadline, event and todo tasks)
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * To create a task
     * @param description The name/description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return a status icon to indicate if a task has been completed
     * @return "[X]" if completed and "[ ]" otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * To mark a given task as completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * To mark a given task as completed and to update its status icon in the list of tasks
     * in user's hard disk
     * @throws IOException if there is an error when overwriting/appending to the contents of
     * the file
     */
    public void markAsDoneAndUpdate() throws IOException{
        this.isDone = true;
        for (int i = 0; i < TaskList.getTaskList().size(); i++) {
            if (i == 0) {
                writeToFile("data/jarvis.txt", (i + 1) + "." +
                        TaskList.getTaskList().get(i).toPrintToFile()
                        + System.lineSeparator());
            } else {
                appendToFile("data/jarvis.txt", (i + 1) + "." +
                        TaskList.getTaskList().get(i).toPrintToFile()
                        + System.lineSeparator());
            }
        }
    }

    /**
     * Returns the task as a string that is to be displayed to the user
     * @return the given task as a string that is to be displayed to the user
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the task as a string that is to be displayed to the user
     * @return the given task as a string that is to be displayed to the user
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    /**
     * Returns the task as a string that is to be appended to the contents of the list of
     * tasks in user's hard disk
     * @return the task as a string that is to be appended to the contents of the list of
     * tasks in user's hard disk
     */
    public String toPrintToFile() {
        return this.getStatusIcon() + this.description;
    }

    /**
     * To write to the file in user's hard disk that stores a list of tasks or to overwrite
     * the contents of this file
     * @param filePath the relative path to the file
     * @param textToAdd the content that is to be written
     * @throws IOException if there is an error in writing to/overwriting the file
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * To append content to the file in user's hard disk that stores a list of tasks
     * @param filePath the relative path to the file
     * @param textToAppend the content that is to be appended
     * @throws IOException if there is an error in appending to existing content of the file
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

}