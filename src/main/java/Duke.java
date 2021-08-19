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
        String userInput = sc.nextLine(); //scanning user's first input

        // Catch DukeException
        try{
            InputChecker ic = new InputChecker(userInput);
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }

        String inpt_str = userInput;
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
            } else if(inpt_str.contains("deadline") || inpt_str.contains("todo") || inpt_str.contains("event")){
                if(inpt_str.contains("deadline")) {
                    if(inpt_str.length() > 8) {
                        Deadline d = new Deadline(inpt.getString().substring(9, inpt.getString().indexOf("/")),
                                inpt.getString().substring(inpt.getString().indexOf("/by") + 3));
                        lst.add(d);
                        System.out.println("Got it. I've added this deadline: \n" + d.toString());
                    }
                } else if(inpt_str.contains("event")){
                    if(inpt_str.length() > 5) {
                        Event d = new Event(inpt.getString().substring(6, inpt.getString().indexOf("/")),
                                inpt.getString().substring(inpt.getString().indexOf("/at") + 3));
                        lst.add(d);
                        System.out.println("Got it. I've added this event: \n" + d.toString());
                    }
                } else if(inpt_str.contains("todo")) {
                    if(inpt_str.length() > 4) {
                        Todo d = new Todo(inpt.getString().substring(5));
                        lst.add(d);
                        System.out.println("Got it. I've added this todo: \n" + d.toString());
                    }
                }
                System.out.println("Now you have " + lst.size() + " tasks in the list.");
            } else if(inpt_str.contains("delete")){
                if(inpt_str.length() > 7){
                    int order = Integer.parseInt(inpt.getString().substring(7)); //getting the order of the task
                    System.out.println("Noted! I have removed this task: \n" + lst.get(order-1).toString());
                    lst.remove(order-1);
                    System.out.println("Now you have " + lst.size() + " tasks in the list.");
                }
            }
            inpt_str = sc.nextLine();

            // Catch DukeException
            try{
                InputChecker ic = new InputChecker(inpt_str);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            inpt = new Task(inpt_str);
        }
        System.out.println("Goodbye, I will miss you!");
    }
}
