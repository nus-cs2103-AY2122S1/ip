import java.util.ArrayList;

public class Ui {

    public void showError(String error) {
        System.out.println(error);
    }

    public void greeting() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void doneMessage(Task doneTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(doneTask);

    }

    public void deleteMessage(Task deletedTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
    }

    public void addMessage(Task addedTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(addedTask);
    }

    public void taskListSize(int size) {
        if (size == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.printf("Now you have %d tasks in the list.%n", size);
        }
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showTasks(ArrayList<Task> taskList) {


        if (taskList.size() == 0) {
            System.out.println("There are no tasks in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            int num = 1;
            for (Task task : taskList) {
                System.out.printf("%d.%s%n", num, task);
                num++;
            }
        }
    }


}
