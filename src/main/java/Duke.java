import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static List<Task> toDoList = new ArrayList<>();

    public static void main(String[] args) {
        String dottedLines = "----------------------------------------------------------------------------------------";
        System.out.println(dottedLines);
        System.out.println("Hello I'm LOTTERY-A");
        System.out.println("Also known as the List Of Tasks That Eventually Require Your Attention");
        System.out.println("What can I do for you?");
        System.out.println(dottedLines);

        Scanner keyboard = new Scanner(System.in);

        while(keyboard.hasNext()) {
            String userInput = keyboard.next();
            String userDescription = keyboard.nextLine();
            if(!userDescription.isEmpty()) {
                userDescription = userDescription.substring(1);
            }

            if(userInput.equals("bye")) {
                System.out.println(dottedLines);
                System.out.println("Bye. Don't forget, these tasks will still require your attention when you return!");
                System.out.println(dottedLines);
                break;
            } else if(userInput.equals("list")) {
                for(int i = 0; i < toDoList.size(); i++) {
                    System.out.println(i+1 + "." + toDoList.get(i).toString());
                }
            } else if(userInput.equals("done")) {
                  toDoList.get(Integer.parseInt(userDescription) - 1).markAsDone();
            } else if(userInput.equals("todo")) {
                Todo todo = new Todo(userDescription);
                toDoList.add(todo);
                System.out.println(dottedLines);
                System.out.println("Got it. I've added this task:\n" + todo.toString());
                System.out.println("Now you have " + toDoList.size() + " tasks in the list");
                System.out.println(dottedLines);
            } else if(userInput.equals("deadline")) {
                int dueByIndex = userDescription.indexOf("/by");
                Deadline deadline = new Deadline(userDescription.substring(0, dueByIndex - 1),
                        userDescription.substring(dueByIndex + 4));
                toDoList.add(deadline);
                System.out.println(dottedLines);
                System.out.println("Got it. I've added this task:\n" + deadline.toString());
                System.out.println("Now you have " + toDoList.size() + " tasks in the list");
                System.out.println(dottedLines);
            } else if(userInput.equals("event")) {
                int duringIndex = userDescription.indexOf("/at");
                Event event = new Event(userDescription.substring(0, duringIndex - 1),
                        userDescription.substring(duringIndex + 4));
                toDoList.add(event);
                System.out.println(dottedLines);
                System.out.println("Got it. I've added this task:\n" + event.toString());
                System.out.println("Now you have " + toDoList.size() + " tasks in the list");
                System.out.println(dottedLines);
            }
        }
    }
}