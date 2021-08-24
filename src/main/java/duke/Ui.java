package duke;

import java.util.Scanner;

/**
 * Class that is in charge of interacting with user.
 */
public class Ui {

    /**
     * Says welcome to the user when they open duke bot.
     */
    public void greet(){
        System.out.println("Hello! I'm duke! What can I do for you?");
    }

    /**
     * Says GoodBye to the user when they leave.
     */
    public void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out the error.
     *
     * @param errorMessage ErrorMessage throws by Duke.
     */
    public void showError(String errorMessage){
        System.out.println(errorMessage);
    }

    /**
     * Reads in command lines from the user.
     *
     * @return Command typed in by the user.
     */
    public String readCommand(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Prints all tasks in TaskList.
     *
     * @param tasks TaskList that is going to be printed.
     */
    public void listAllTasks(TaskList tasks){
        for(int i = 0; i < tasks.size(); i++){
            System.out.println((i+1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Notifies users that certain task has been marked as done.
     *
     * @param task The task that's going to be marked as done.
     */
    public void markAsDone(Task task){
        System.out.println("Nice! I've marked this task as done:\n"
                + task.toString());
    }

    /**
     * Notifies users that certain tasks has been added.
     *
     * @param task Task that has been added.
     * @param taskListSize Size of the updated taskList.
     */
    public void addTask(Task task, int taskListSize){
        System.out.println("Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have "
                + taskListSize + " tasks in the list.");
    }

    /**
     * Notifies the user that certain task has been deleted.
     *
     * @param task Task that has been deleted.
     * @param taskListSize Size of the updated taskList.
     */
    public void deleteTask(Task task, int taskListSize){
        System.out.println("Successfully deleted task"
                + task.toString()
                + "\nNow you have" + taskListSize + " tasks in the list.");
    }
}
