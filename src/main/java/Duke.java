package main.java;
import java.util.Scanner;

public class Duke {

    /**
     * Displays the task added message according to the task input given.
     *
     * @param input The task input to be added to the list.
     * @param type  The type id. Todo: 1, Deadline: 2, Event: 3.
     * @param index The current index of the list.
     * @param info  The information regarding the task (In the format of "(By: ...)" or "(At: ...)", or "" for Todos)
     * @return The String sequence showing that the task has been added to the list.
     */
    public static String addTask(String input, int type, int index, String info) {

        // Determine the string that displays the type of task
        String taskType;
        String prefix;
        if (type == 1) {
            taskType = "task (Todo)";
            prefix = "[T][ ]";
        } else if (type == 2) {
            taskType = "task (Deadline)";
            prefix = "[D][ ]";
        } else if (type == 3) {
            taskType = "task (Event)";
            prefix = "[E][ ]";
        } else {
            taskType = "";
            prefix = "";
        }

        // Return the message accordingly
        return "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + "Alright. I've added the following "
                + taskType
                + ":\n--> "
                + prefix
                + " "
                + input
                + (type == 2 ? "(By: " + info + ")" : type == 3 ? "(At: " + info + ")" : "")
                + "\n\n"
                + "You currently have "
                + index
                + (index == 1 ? " task" : " tasks")
                + " in the list.\n"
                + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
    }

    /**
     * Main function for the Chatbot.
     *
     * @param args ...
     */
    public static void main(String[] args) {
        // Starting message
        String start = "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + "Hello! My name is LHWBot!\n"
                + "What can I do for you today?\n"
                + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
        System.out.println(start);

        // Array of String to store user inputs
        Task[] TaskArray = new Task[250];
        int index = 0;

        // Define the scanner to read user inputs
        Scanner reader = new Scanner(System.in);

        // Continuously listen for user inputs
        while (true) {

            // Get the next input from the user
            String input = reader.nextLine();

            // Continuously run the CommandHandler to handle new inputs from user
            CommandHandler handler = new CommandHandler(reader, input);
            handler.handle();
        }
    }
}

//            } else if (input.startsWith("done ")) {
//
//                // If the user types "done X" where X is a non-zero integer, mark the task as complete
//                // TODO Error handling for inputs
//                int doneIndex = Integer.parseInt(input.substring(5)) - 1;
//                TaskArray[doneIndex].toggleState();
//                String done = "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
//                        + "Great! I've marked the following task as done:\n"
//                        + TaskArray[doneIndex].getTaskState()
//                        + "\n";
//                done += "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
//                System.out.println(done);
//
//            } else if (input.startsWith("todo ") && input.length() > 5) {
//
//                // If the user types "todo [XXX]" where [XXX] is a substring, store substring as a Todo object
//                // TODO Error handling for inputs
//                String newTask = input.substring(5);
//                TaskArray[index] = new TodoTask(newTask);
//                index++;
//
//                // After storing it, repeat it back to the user
//                System.out.println(addTask(newTask, 1, index, ""));
//
//            } else if (input.startsWith("deadline ") && input.contains("/by ")) {
//                // If the user types "deadline [XXX]" where [XXX] is a substring, store substring as a Deadline object
//                // TODO Error handling for inputs
//                int position = input.indexOf("/by");
//                String newTask = input.substring(9, position);
//                String newInfo = input.substring(position + 4);
//                TaskArray[index] = new DeadlineTask(newTask, newInfo);
//                index++;
//
//                // After storing it, repeat it back to the user
//                System.out.println(addTask(newTask, 2, index, newInfo));
//
//            } else if (input.startsWith("event ") && input.contains("/at ")) {
//                // If the user types "event [XXX]" where [XXX] is a substring, store substring as an Event object
//                // TODO Error handling for inputs
//                int position = input.indexOf("/at");
//                String newTask = input.substring(6, position);
//                String newInfo = input.substring(position + 4);
//                TaskArray[index] = new EventTask(newTask, newInfo);
//                index++;
//
//                // After storing it, repeat it back to the user
//                System.out.println(addTask(newTask, 3, index, newInfo));
//
//            } else {
//
//
//                System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
//                        + "OOPS! Sorry, I don't know what that means...\n"
//                        + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n");
//
//            }