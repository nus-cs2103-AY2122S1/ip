import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();

        String divider = "\n ------------------------------------------- \n";
        String indent = "       ";

        System.out.println(indent + "Hello! I'm Viper :)");
        System.out.println(indent + "How can I help you today?");
        System.out.println(divider);

        String str = sc.nextLine();

        while (!str.trim().equalsIgnoreCase("bye")) {
            //list out items in the list
            if (str.trim().equalsIgnoreCase("list")) {
                System.out.println(indent + "Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    int currIndex = i + 1;
                    Task currTask = taskList.get(i);
                    System.out.println(indent + currIndex + "." + currTask.toString());
                }
                System.out.println(divider);
            }
            //mark numbered task as done
            if (str.toLowerCase().contains("done")) {
                int taskNo = Integer.parseInt(str.substring(4).trim()) - 1;
                //check if taskNo exists:
                if (taskNo < taskList.size()) {
                    Task currTask = taskList.get(taskNo);
                    currTask.isDone = true;
                    System.out.println(indent + "Good job on completing your task!");
                    System.out.println(indent + "I've marked this task as done:");
                    System.out.println(indent + currTask.toString());
                }
                else {
                    System.out.println(indent + "Sorry, this task does not exist. :(");
                }
                System.out.println(divider);
            }
            //for todos:
            if (str.toLowerCase().contains("todo")) {
                String desc = str.substring(4).trim();
                Todos addTodo = new Todos(desc);
                taskList.add(addTodo);
                //print out msg:
                System.out.println(indent + "Ok! I have added this task to your list: ");
                System.out.println(indent + "[T][ ] " + desc);
                System.out.println("Now you have a total of " + taskList.size() + " task(s)!");
                System.out.println(divider);
            }
            //for deadline:
            if (str.toLowerCase().contains("deadline")) {
                String desc = str.substring(8).trim();
                String[] stringParts = desc.split("/");
                Deadlines addDeadline = new Deadlines(stringParts[0].trim(), stringParts[1].trim().substring(2).trim());
                taskList.add(addDeadline);
                //print out msg:
                System.out.println(indent + "Oh no! A new deadline?! It's okay, you got this!");
                System.out.println(indent + "I have added this deadline to your list: ");
                System.out.println(indent + addDeadline);
                System.out.println("Now you have a total of " + taskList.size() + " task(s)!");
                System.out.println(divider);
            }
            //for events:
            if (str.toLowerCase().contains("events")) {
                String desc = str.substring(6).trim();
                String[] stringParts = desc.split("/");
                Events addEvent = new Events(stringParts[0].trim(), stringParts[1].trim().substring(2).trim());
                taskList.add(addEvent);
                //print out msg:
                System.out.println(indent + "Ok! I have added this event to your list: ");
                System.out.println(indent + addEvent);
                System.out.println("Now you have a total of " + taskList.size() + " task(s)!");
                System.out.println(divider);
            }
            str = sc.nextLine();
        }

        System.out.println(indent + "Bye! See you again~");
        System.out.println(divider);

    }
}
