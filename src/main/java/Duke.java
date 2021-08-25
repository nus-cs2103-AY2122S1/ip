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

        String readStr = sc.nextLine();
        String[] splitStr = readStr.split(" ", 2);
        
        while (!splitStr[0].equalsIgnoreCase("bye")) {
            printDivider();
            
            switch (Instruction.comparesTo(splitStr[0])) {
            case LIST:
                printLine("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    int currIndex = i + 1;
                    Task currTask = taskList.get(i);
                    printLine(currIndex + "." + currTask.toString());
                }
                break;
            case DONE:
                int taskNo = Integer.parseInt(splitStr[1]) - 1;
                if (taskNo < taskList.size()) {
                    Task currTask = taskList.get(taskNo);
                    currTask.isDone = true;
                    printMessage("Good job on completing your task!", "I've marked this task as done:",
                            currTask.toString());
                } else {
                    printLine("Sorry, this task does not exist. :(");
                }
                break;
            case DELETE:
                taskNo = Integer.parseInt(splitStr[1]) - 1;
                if (taskNo < taskList.size()) {
                    Task currTask = taskList.get(taskNo);
                    taskList.remove(taskNo);
                    printMessage("Ok!! I have removed the following task from your list:", currTask.toString(),
                            "Now you have " + taskList.size() + " task(s) left~ Yay!");
                }
                break;
            case TODO:
                if (splitStr[1].isEmpty()) {
                    throw new DukeException("Oh no!! You cannot leave the description of a todo empty!!! ☹");
                } else {
                    Todos addTodo = new Todos(splitStr[1]);
                    taskList.add(addTodo);
                    printMessage("Ok! I have added this task to your list:", "[T][ ] " + splitStr[1],
                            "Now you have a total of " + taskList.size() + " task(s)!");
                }
                break;
            case DEADLINE:
                if (splitStr[1].isEmpty()) {
                    throw new DukeException("Oh no!! Your description for the deadline cannot be empty!!! ☹");
                } else {
                    int posBy = splitStr[1].indexOf("by") + 3;
                    int posSlash = splitStr[1].indexOf("/") - 1;
                    String desc = splitStr[1].substring(0, posSlash);
                    String date = splitStr[1].substring(posBy);
                    Deadlines addDeadline = new Deadlines(desc, date);
                    taskList.add(addDeadline);
                    printMessage("Oh no! A new deadline?! It's okay, you got this!", addDeadline.toString(),
                            "Now you have a total of " + taskList.size() + " task(s)!");
                }
                break;
            case EVENT:
                if (splitStr[1].isEmpty()) {
                    throw new DukeException("Oh no!! Something is missing from your description for the event!!! ☹");
                } else {
                    int posAt = splitStr[1].indexOf("at") + 3;
                    int posSlash = splitStr[1].indexOf("/") - 1;
                    String desc = splitStr[1].substring(0, posSlash);
                    String date = splitStr[1].substring(posAt);
                    Events addEvent = new Events(desc, date);
                    taskList.add(addEvent);
                    printMessage("Ok! I have added this event to your list:", addEvent.toString(),
                            "Now you have a total of " + taskList.size() + " task(s)!");
                }
                break;
            case INVALID:
                throw new DukeException("Oops!! Sorry, I do not understand what you mean... ☹");
            }
            
            printDivider();
            
            String next = sc.nextLine();
            if (next.isEmpty()) {
                break;
            }
            else {
                splitStr = next.split(" ", 2);
            }
        }
        printDivider();
        printLine("Bye! See you again~");
        printDivider();
    }
}
