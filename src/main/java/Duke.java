import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String indent = "       ";

    private static void printMessage (String s1, String s2, String s3) {
        System.out.println(indent + s1);
        System.out.println(indent + s2);
        System.out.println(indent + s3);
    }

    private static void printLine (String s) {
        System.out.println(indent + s);
    }

    private static void printDivider () {
        System.out.println("\n ------------------------------------------- \n");
    }

    public static void main(String[] args) throws DukeException {
        
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();
        
        printMessage("Hello! I'm Viper :)", "I love to help!", "How can I help you today?");
        printDivider();

        String str = sc.nextLine();

        while (!str.trim().equalsIgnoreCase("bye")) {
            printDivider();
            
            if (str.trim().equalsIgnoreCase("list")) {
                printLine("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    int currIndex = i + 1;
                    Task currTask = taskList.get(i);
                    printLine(currIndex + "." + currTask.toString());
                }
            } else if (str.toLowerCase().contains("done")) {
                int taskNo = Integer.parseInt(str.substring(4).trim()) - 1;
                if (taskNo < taskList.size()) {
                    Task currTask = taskList.get(taskNo);
                    currTask.isDone = true;
                    printMessage("Good job on completing your task!", "I've marked this task as done:",
                            currTask.toString());
                } else {
                    printLine("Sorry, this task does not exist. :(");
                }
            } else if (str.toLowerCase().contains("delete")) {
                int taskNo = Integer.parseInt(str.substring(6).trim()) - 1;
                if (taskNo < taskList.size()) {
                    Task currTask = taskList.get(taskNo);
                    taskList.remove(taskNo);
                    printMessage("Ok!! I have removed the following task from your list:", currTask.toString(),
                            "Now you have " + taskList.size() + " task(s) left~ Yay!");
                }
            } else if (str.toLowerCase().contains("todo")) {
                String desc = str.substring(4).trim();
                Todos addTodo = new Todos(desc);
                if (desc.isEmpty()) {
                    throw new DukeException("Oh no!! You cannot leave the description of a todo empty!!! ☹");
                } else {
                    taskList.add(addTodo);
                    printMessage("Ok! I have added this task to your list:", "[T][ ] " + desc,
                            "Now you have a total of " + taskList.size() + " task(s)!");
                }
            } else if (str.toLowerCase().contains("deadline")) {
                String desc = str.substring(8).trim();
                String[] stringParts = desc.split("/");
                if (stringParts.length < 2) {
                    throw new DukeException("Oh no!! Something is missing from your description for the deadline!!! ☹");
                } else {
                    Deadlines addDeadline = new Deadlines(stringParts[0].trim(), stringParts[1].trim().substring(2).trim());
                    taskList.add(addDeadline);
                    printMessage("Oh no! A new deadline?! It's okay, you got this!", addDeadline.toString(),
                            "Now you have a total of " + taskList.size() + " task(s)!");
                }
            } else if (str.toLowerCase().contains("event")) {
                String desc = str.substring(6).trim();
                String[] stringParts = desc.split("/");
                if (stringParts.length < 2) {
                    throw new DukeException("Oh no!! Something is missing from your description for the event!!! ☹");
                } else {
                    Events addEvent = new Events(stringParts[0].trim(), stringParts[1].trim().substring(2).trim());
                    taskList.add(addEvent);
                    printMessage("Ok! I have added this event to your list:", addEvent.toString(),
                            "Now you have a total of " + taskList.size() + " task(s)!");
                }
            } else {
                throw new DukeException("Oops!! Sorry, I do not understand what you mean... ☹");
            }
            
            printDivider();
            
            String next = sc.nextLine();
            if (next.isEmpty()) {
                break;
            }
            else {
                str = next;
            }
        }
        printDivider();
        printLine("Bye! See you again~");
        printDivider();
    }
}
