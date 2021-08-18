import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you? :)");
        Scanner sc = new Scanner(System.in); //initialize scanner
        String inpt_str = sc.nextLine(); //scanning user's first input
        Task inpt = new Task(inpt_str);
        ArrayList<Task> lst = new ArrayList<>(); // initialize array list

        // printing user's input & adding it to an array list, loops until input is "bye"
        while(!inpt.getString().equals("bye")){
            if(inpt.getString().replaceAll("[\\d]", "").equals("done ")){
                int order = Integer.parseInt(inpt.getString().substring(5)); //getting the order of the task
                Task currentTask = lst.get(order-1);
                currentTask.completed();
                System.out.println("Nice! I've marked this task as done: \n" + currentTask.toString());
            } else if(inpt.getString().equals("list")){
                System.out.println("here are the tasks in your list:");
                lst.forEach(x -> System.out.println((lst.indexOf(x) + 1) + ". " + x.toString()));
            } else {
                System.out.println("added: " + inpt.getString());
                lst.add(inpt);
            }
            inpt_str = sc.nextLine();
            inpt = new Task(inpt_str);
        }
        System.out.println("Goodbye, I will miss you!");
    }
}
