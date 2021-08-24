package duke;

import java.util.Scanner;

public class Ui {

    public void greet(){
        System.out.println("Hello! I'm duke! What can I do for you?");
    }

    public void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String errorMessage){
        System.out.println(errorMessage);
    }

    public String readCommand(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void listAllTasks(TaskList tasks){
        for(int i = 0; i < tasks.size(); i++){
            System.out.println((i+1) + "." + tasks.get(i).toString());
        }
    }

    public void markAsDone(Task task){
        System.out.println("Nice! I've marked this task as done:\n"
                + task.toString());
    }

    public void addTask(Task task, int taskListSize){
        System.out.println("Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have "
                + taskListSize + " tasks in the list.");
    }

    public void deleteTask(Task task, int taskListSize){
        System.out.println("Successfully deleted task"
                + task.toString()
                + "\nNow you have" + taskListSize + " tasks in the list.");
    }
}
