import tasks.*;
import exceptions.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        //Task[] tasks = new Task[100];
        ArrayList<Task> tasks = new ArrayList<Task>();
        int taskCounter = 0;

        String input = "";
        try {while(true){
            input = sc.nextLine();
            if(input.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
                //System.exit();
            }
            else if(input.equals("list")) {
                for(int i = 0; i < taskCounter; i++){
                    System.out.println((i+1) + "." + tasks.get(i).toString());
                }
            } else if (input.length() > 4 && input.substring(0,4).equals("done")){
                String taskDone = input.substring(5);
                int taskDoneIndex = Integer.parseInt(taskDone)-1;
                tasks.get(taskDoneIndex).makeDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(tasks.get(taskDoneIndex).toString());
            }
            else {
                if(input.length()<4 ){
                    throw new DukeException("Unacceptable input");
                }
                if(input.substring(0,4).equals("todo")){
                    TodoTask newTask = new TodoTask(input.substring(5));
                    tasks.add(newTask);
                    taskCounter++;
                    System.out.println("Got it. I've added this task: ");
                    System.out.print("  " + newTask.toString());

                } else if(input.substring(0,5).equals("event")){
                    String at = input.split("/")[1].substring(3);
                    EventTask newEvent = new EventTask(input.substring(6).split("/")[0], at);
                    tasks.add(newEvent);
                    taskCounter++;
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + newEvent.toString());
                } else if(input.length()>5 && input.length()<8){
                    throw new DukeException("Unacceptable input");
                }
                else if(input.substring(0,6).equals("delete")) {
                    // delete from arraylist
                    // reduce counter by 1
                    int indexToDel = Integer.parseInt(input.substring(7));
                    Task tasktoDel = tasks.get(indexToDel);
                    tasks.remove(indexToDel);
                    taskCounter--;
                    System.out.println("   Noted. I've removed this task: ");
                    System.out.println("  " + tasktoDel.toString());
                }
                else if(input.substring(0,8).equals("deadline")){
                    // deadline
                    String by = input.split("/")[1].substring(3);
                    DeadlineTask newDeadline = new DeadlineTask(input.substring(9).split("/")[0], by);
                    tasks.add(newDeadline);
                    taskCounter++;
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + newDeadline.toString());

                }

                else {
                    throw new DukeException("Unacceptable input");
                }

                System.out.println("\nNow you have " +  (taskCounter) + " tasks in the list.");
            }
        }} catch (DukeException e){
            System.out.println("OOPS!!! You have enteted an invalid category");
        }

    }
}

