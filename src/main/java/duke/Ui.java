package duke;

public class Ui {
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String linebreak = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

    public void greetingMsg() {
        String str =  "Hello from\n"
                + logo
                + linebreak
                + "My favorite partner is back! How can I help?\n"
                + linebreak;
        System.out.print(str);
    }

    public void goodbyeMsg() {
       System.out.print( linebreak
                + "Have a good day, friend!\n"
                + linebreak);
    }

    public void listTaskList(TaskList taskList) {
        System.out.print(linebreak);
        for (int j = 0; j < Task.noOfTask; j++) {
            String listItem = (j + 1)
                    + "."
                    + taskList.getTask(j).getTaskType()
                    + taskList.getTask(j).checkIsDone()
                    + " " + taskList.getTask(j).getDescription() + "\n";
            System.out.print(listItem);
        }
        System.out.print(linebreak);
    }

    public void doneTaskMsg(Task task) {
        System.out.print( linebreak
                + "Well Done, I'll get it marked:\n"
                + task.checkIsDone()
                + " " + task.getDescription() + "\n"
                + linebreak);
    }

    public void deleteTaskMsg(Task task) {
        System.out.print( linebreak
                + "Roger! I will remove this task from the list: \n"
                + task.getTaskType()
                + task.checkIsDone()
                + task.getDescription() + "\n"
                + "Now you have "
                + Task.noOfTask
                + " tasks left in the list.\n"
                + linebreak);
    }

    public void addTaskMsg(Task task) {
        System.out.print( linebreak
                +"Roger! I will add this task in: \n"
                + task.getTaskType()
                + task.checkIsDone()
                + " "
                + task.getDescription() + "\n"
                + "Now you have "
                + Task.noOfTask
                + " tasks left in the list.\n"
                + linebreak);
    }
}
