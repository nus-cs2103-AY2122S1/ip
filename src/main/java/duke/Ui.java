package duke;

import java.util.ArrayList;

public class Ui {

    public void startMessage(ArrayList<Task> taskList) {
        System.out.println("Hello I am Duke.\nWhat can I do for you?");
        System.out.println();
        if (taskList.size() > 0) {
            System.out.println("Current number of tasks: " + taskList.size());
            iterateTaskList(taskList);
            System.out.println();
        }
    }

    public void iterateTaskList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("List is empty!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task temp = taskList.get(i);
            System.out.printf("%s. %s\n", i + 1, temp);
        }
        System.out.println();
    }

    public void endMessage() {
        System.out.println("Bye! See you next time!");
    }


}
