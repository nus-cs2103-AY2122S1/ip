import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);
    private String dashedLine = "_______________________________________________";

    public void greetUser(){
        System.out.println(dashedLine);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(dashedLine);
    }
    public String readInput(){
        String response = sc.nextLine();
        return response;
    }
    public void displayTaskAdded(Task td, int ctr){
        System.out.println("Got it! I've added this task: \n" + td.toString());
        ctr++;
        System.out.println("Now you have " + ctr + " tasks in the list.");
        System.out.println(dashedLine);
    }
    public void displayTaskRemoved(Task td, int ctr){
        System.out.println("Noted. I've now removed this task: \n" + td);
        ctr--;
        System.out.println("Now you have " + ctr+ " tasks in the list.");
        System.out.println(dashedLine);
    }
    public void displayTaskDone(Task td){
        System.out.println("Nice! I've marked this task as done: \n" + td);
        System.out.println(dashedLine);
    }
    public void displayTaskList(TaskList t){
        System.out.println(dashedLine);
        System.out.println("Here are the tasks on your list: ");
        System.out.println(t);
    }
    public void sayBye() {
        System.out.println(dashedLine);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(dashedLine);
    }
    public void displayError(String msg){
       System.out.println(msg);
    }
}
