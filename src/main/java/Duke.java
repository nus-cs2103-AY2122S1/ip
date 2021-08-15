import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    //This method creates the while loop for echo, and calls different methods depending on user's input
    private static void echo() {
        ArrayList<Task> userInputRecord = new ArrayList<>();
        Scanner myScanner = new Scanner(System.in);
        String userInput = myScanner.nextLine();

        while(!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                printUserInputRecord(userInputRecord);
            } else if(userInput.startsWith("done")){
                if(isDoneCommand(userInput)) {
                    markAsDone(userInput, userInputRecord);
                } else {
                    System.out.println("    ____________________________________________________________\n" +
                            "     OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                            "    ____________________________________________________________");
                    userInput = myScanner.nextLine();
                    continue;
                }
            } else {
                add(userInput,userInputRecord);
            }
            userInput = myScanner.nextLine();
        }
        myScanner.close();
    }

    //This method adds the user's input to the reminder list
    private static void add(String userInput, ArrayList<Task> userInputRecord) {
        Task task;
        if(userInput.startsWith("todo")) {
            try {
                String description = userInput.substring(5);
                if (description.trim().isEmpty()) {
                    System.out.println("    ____________________________________________________________\n" +
                            "     OOPS!!! The description of a todo cannot be empty.\n" +
                            "    ____________________________________________________________");
                    return;
                }
                task = new ToDo(description);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("    ____________________________________________________________\n" +
                        "     OOPS!!! The description of a todo cannot be empty.\n" +
                        "    ____________________________________________________________");
                return;
            }
        } else if(userInput.startsWith("deadline")) {
            try {
                int byPosition = userInput.lastIndexOf("/by");
                String ddl = userInput.substring(byPosition + 4);
                String description = userInput.substring(9,byPosition); //Length of "deadline " = 9
                task = new Deadline(description,ddl);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("    ____________________________________________________________\n" +
                        "     OOPS!!! The description of a deadline cannot be empty.\n" +
                        "    ____________________________________________________________");
                return;
            }

        } else if(userInput.startsWith("event")) {
            try {
                int atPosition = userInput.lastIndexOf("/at");
                String time = userInput.substring(atPosition + 4);
                String description = userInput.substring(6, atPosition);//Length of "event " = 6
                task = new Event(description, time);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("    ____________________________________________________________\n" +
                        "     OOPS!!! The description of an event cannot be empty.\n" +
                        "    ____________________________________________________________");
                return;
            }
        } else {
            System.out.println("    ____________________________________________________________\n" +
                    "     OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "    ____________________________________________________________");
            return;
        }

        userInputRecord.add(task);
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       " + task + "\n" +
                "     Now you have " + userInputRecord.size() + " tasks in the list.\n" +
                "    ____________________________________________________________");
    }

    //This method prints the saved list of events from the user.
    private static void printUserInputRecord(ArrayList<Task> userInputRecord) {
        if(userInputRecord.isEmpty()) {
            System.out.println("    ____________________________________________________________\n" +
                    "       Ah oh, seems like nothing is added yet :( \n" +
                    "       Try to input something first! \n" +
                    "    ____________________________________________________________"
            );

        } else {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < userInputRecord.size(); i++) {
                System.out.println("     " + (i + 1) + "." + userInputRecord.get(i));
            }
            System.out.println("    ____________________________________________________________");
        }
    }

    /**This method checks if a String starting with done is indeed a done command.
     * Examples:
     * Case 1: done 3 => valid, standard form
     * Case 2: done         5 ==> valid, after trimmed
     * Case 3: done with my schoolwork ==> invalid, will generate an exception
     * **/
    private static boolean isDoneCommand(String userInput) {
        String copy = userInput.replace("done", "");
        copy = copy.replaceAll("[0-9]", ""); //Learnt from https://attacomsian.com/blog/java-extract-digits-from-string
        copy = copy.trim();
        return copy.isEmpty();
    }

    //This method marks a saved event as done
    private static void markAsDone(String userInput, ArrayList<Task> userInputRecord) {
        int itemToComplete = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
        try {
            Task taskDone = userInputRecord.get(itemToComplete);
            taskDone.setDone(true);
            userInputRecord.set(itemToComplete, taskDone);
            System.out.println("    ____________________________________________________________\n" +
                    "     Nice! I've marked this task as done:\n" +
                    "     " + userInputRecord.get(itemToComplete) + "\n" +
                    "    ____________________________________________________________");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    ____________________________________________________________\n" +
                    "      Oops, the ID of the task does not exist\n" +
                    "    ____________________________________________________________");
        }
    }

    public static void main(String[] args) {
        String greeting = "    ____________________________________________________________\n" +
                "     Hello! I'm Peoduo\n" +
                "     Can I help you?\n" +
                "    ____________________________________________________________";
        System.out.println(greeting);
        echo();
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
