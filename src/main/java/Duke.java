import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        //Task[] storedInputs = new Task[100];
        List<Task> storedInputs = new ArrayList<>();
        int index = 0;
        Scanner scanObj = new Scanner(System.in);

        String greeting = "Hello! I'm BunnyBot, Joe Wel's personal slave!\n" + "What can I do for you?";
        System.out.println(greeting);

        while(true) {
            String userInput = scanObj.nextLine();
            try {
                if (userInput.equals("bye")) {
                    String byeMsg = "    ----------------------------\n"
                            + "    okay :<, bye!" + "\n"
                            + "    ----------------------------";
                    System.out.println(byeMsg);
                    break;
                } else if (userInput.equals("list")) {
                    String message = "    ----------------------------\n"
                            + "    " + "Here are the tasks in your list:\n";
                    int i = 0;
                    while (i < storedInputs.size()) {
                        message += "    " + (i+1) + ". " + storedInputs.get(i).toString() + "\n";
                        i++;
                    }
                    message += "    ----------------------------";
                    System.out.println(message);
                } else if (userInput.substring(0,4).equals("done")) {
                    String userIndex = userInput.substring(5);
                    int i = Integer.valueOf(userIndex);
                    if (storedInputs.get(i-1) == null) {
                        System.out.println("no task found!");
                    } else {
                        storedInputs.get(i-1).markAsDone();
                        String message = "----------------------------\n"
                                +"Nice! I have marked this task as done:\n"
                                +"[X] " + storedInputs.get(i-1).getDescription() + "\n" + "----------------------------";
                        System.out.println(message);
                    }
                } else if (userInput.substring(0,4).equals("todo")) {
                    try {
                        Task A = new ToDo(userInput.substring(5));
                        storedInputs.add(A);
                        index++;
                        String message = "----------------------------\n"+
                                "Got it, I've added this task: \n"
                                + A.toString() + "\n"
                                + "Now you have " + index + " tasks in the list\n"
                                +"----------------------------";
                        System.out.println(message);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Oops! The description of a todo cannot be empty!");
                    }

                } else if (userInput.substring(0,5).equals("event")) {
                    try {
                        int i = userInput.indexOf("/");
                        String description = userInput.substring(6,i-1);
                        String time = userInput.substring(i+1);
                        Task A = new Event(description, time);
                        storedInputs.add(A);
                        index++;
                        String message = "----------------------------\n"
                                +"Got it, I've added this task: \n"
                                + A.toString() + "\n"
                                + "Now you have " + index + " tasks in the list\n"
                                +"----------------------------";
                        System.out.println(message);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Oops! The description of an event cannot be empty and must contain a time!");
                    }
                } else if (userInput.substring(0,8).equals("deadline")) {
                    try {
                        int i = userInput.indexOf("/");
                        String description = userInput.substring(9,i-1);
                        String time = userInput.substring(i+1);
                        Task A = new Deadlines(description, time);
                        storedInputs.add(A);
                        index++;
                        String message = "----------------------------\n"
                                +"Got it, I've added this task: \n"
                                + A.toString() + "\n"
                                + "Now you have " + index + " tasks in the list\n"
                                +" ----------------------------";
                        System.out.println(message);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Oops! The description of a deadline cannot be empty and must contain a time!");
                    }
                } else if (userInput.substring(0,6).equals("delete")) {
                    String userIndex = userInput.substring(7);
                    int i = Integer.valueOf(userIndex);
                    if (storedInputs.get(i-1) == null) {
                        System.out.println("no task found!");
                    } else {
                        index--;
                        String message = "----------------------------\n"
                                +"Got it, I've removed this task: \n"
                                + storedInputs.get(i-1).toString() + "\n"
                                + "Now you have " + index + " tasks in the list\n"
                                +" ----------------------------";
                        System.out.println(message);
                        storedInputs.remove(i-1);
                    }
                } else {
                    System.out.println("sorry! i'm not taught that command yet :<");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("sorry! i'm not taught that command yet :<<");
            }
        }
    }
}
