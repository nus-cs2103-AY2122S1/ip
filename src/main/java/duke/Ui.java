package duke;

import duke.task.Task;

public class Ui {

    public Ui() {}

    /**
     * Returns a greeting message.
     *
     * @return Greeting message
     */
    public String greeting() {
        return "    Hello! I am Vision. How can I be of assistance?";
    }

    /**
     * Returns a farewell message.
     *
     * @return Farewell message
     */
    public String bye() {
        return "    Bye. Hope to see you again soon!";
    }

    /**
     * Returns a message stating that there is an IO error.
     *
     * @return Message stating that there is an IO error.
     */
    public String ioErrorMsg() {
        return "Something went wrong!";
    }

    /**
     * Returns a message confirming a task has been added to the list.
     *
     * @param desc The description of the task.
     * @param size The size of the list.
     * @return Message confirming a task has been added to the list.
     */
    public String taskAddedMsg(String desc, int size) {
        return "    Got it. I've added this task:\n" + "        " + desc +
                "\n    Now you have " + size + " tasks in the list.";
    }

    /**
     * Returns a message confirming a task has been deleted to the list.
     *
     * @param desc The description of the task.
     * @param size The size of the list.
     * @return Message confirming a task has been deleted to the list.
     */
    public String taskDeleteMsg(String desc, int size) {
        return "    Got it. I've deleted this task:\n" + "        " + desc +
                "\n    Now you have " + size + " tasks in the list.";
    }

    /**
     * Returns a message stating that a task has been marked as done.
     *
     * @return Message stating that a task has been marked as done.
     */
    public String taskDoneConfirmation(String desc) {
        return "    Nice! I've marked this task as done:\n\t" +
                desc;
    }

    public String parsingFormatErrorMsg() {
        return "   ☹ OOPS!!! Please check the formatting of your input!";
    }

    /**
     * Returns various error messages for various error events.
     *
     * @param caseNumber The case number.
     * @return The error message relevant to the problem.
     */
    public String taskErrorMsg(int caseNumber) {
        switch (caseNumber) {
        case 1:
            return "   ☹ OOPS!!! The description of a todo cannot be empty.";
        case 2:
            return "   ☹ OOPS!!! The description of a deadline cannot be empty.";
        case 3:
            return "   ☹ OOPS!!! The description of an event cannot be empty.";
        case 4:
            return "   ☹ OOPS!!! Index out of bounds";
        default:
            return "   ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }

    /**
     * Returns all the tasks in the list.
     *
     * @param storageList The list storing all the tasks.
     * @return The contents in the list.
     */
    public String displayListContents(StorageList storageList) {
        String output = "    Here are the tasks in your list:";

        for (int i = 0; i < storageList.size(); i++) {
            int num = i + 1;
            Task task = storageList.get(i);
            output += "\n        " + num + "." + task.toString();
        }
        return output;
    }
}
