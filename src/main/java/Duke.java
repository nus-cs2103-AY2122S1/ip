import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class encapsulating a chat bot.
 *
 * @author Toh Wang Bin
 */
public class Duke {

    /**
     * The variants of the Tasks handled by Duke
     */
    private enum Tasks {DEADLINE, EVENT, TODO}

    //ArrayList containing tasks stored by Duke
    public static ArrayList<Task> taskArray = new ArrayList<>();
    //Integer that stores the number of tasks currently stored by Duke.
    public static int listIndex = 0;


    /**
     * Starts the main process, activating the chatbot.
     *
     * @param args The default parameter for the main function.
     */
    public static void main(String[] args) {
        Storage.start();

        Scanner scan = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo + "\nWhat can I do for you today?");

        while(true) {
            String input = scan.nextLine();
            String[] inputArray = input.split("\\s");
            String firstString = inputArray[0];

            //case if nothing is entered
            if (firstString.equals("")) {
                System.out.println("...why so quiet? Come on, say something to me!");
                continue;
            }

            //case if user wants to exit the program
            if (firstString.equals("bye")) {
                break;
            }

            //cases for specified keywords
            if (firstString.equals("list")) {
                if (listIndex == 0) {
                    System.out.println("There are currently no tasks! Stop being so lazy and start adding your tasks!");
                    continue;
                }
                for (int i = 0; i < listIndex; i++) {
                    int listNumber = i + 1;
                    System.out.println(listNumber + ". " + taskArray.get(i).toString());
                }
                continue;
            }

            if (firstString.equals("done") || firstString.equals("delete")) {
                if (inputArray.length < 2) {
                    //case if no number is entered
                    System.out.println("Please enter the index of the task to complete after the keyword done!");
                    continue;
                }
                try {
                    int index = Integer.parseInt(inputArray[1]);
                    int arrayIndex = index - 1;
                    //case if entered index does not correspond to a task
                    if (index > listIndex || index < 1) {
                        System.out.println("That task does not exist! Don't try to trick me!");
                        continue;
                    }
                    Task currentTask = taskArray.get(arrayIndex);
                    if (firstString.equals("done")) {
                        //case to complete a task
                        currentTask.setCompleted();
                        System.out.println("Ok, very nice. I have set the following task as completed.\n" + currentTask.toString());
                    } else {
                        //remaining case is to delete the task.
                        taskArray.remove(currentTask);
                        listIndex -= 1;
                        System.out.println("Ok, very nice. I have deleted the following task.\n" + currentTask.toString());
                    }
                    System.out.println("Now you have " + listIndex + " tasks remaining. Get to work!");
                    Storage.saveData();

                } catch (NumberFormatException exception) {
                    System.out.println("Enter a valid number! Or do you not know basic math?");
                } finally {
                    continue;
                }
            }

            //cases for the 3 task types
            if (firstString.equals("todo")) {
                createTask(Tasks.TODO, inputArray);
                continue;
            }

            if (firstString.equals("deadline")) {
                createTask(Tasks.DEADLINE, inputArray);
                continue;
            }

            if (firstString.equals("event")) {
                createTask(Tasks.EVENT, inputArray);
                continue;
            }

            //case if first string input is not a keyword
            System.out.println("I could not understand your input. Please stop speaking gibberish to me!");
        }

        //exit from the program
        scan.close();
        System.out.println("kthxbye");
    }

    /**
     * Creates a Task as specified by the parameters.
     *
     * @param taskType The type of task to be created.
     * @param array The array of strings used to create the task.
     */
    private static void createTask(Tasks taskType, String[] array) {
        //preliminary check if more than 1 string was entered
        if (array.length < 2) {
            //case if no name is entered for the task
            System.out.println("Hey, I'm gonna need a name for your Task!");
            return;
        }

        StringBuilder str = new StringBuilder();
        Task tempTask = null;
        switch (taskType) {
        case TODO: {
            for (int i = 1; i < array.length; i++) {
                str.append(array[i]).append(" ");
            }

            tempTask = new Todos(str.toString());
            break;
        }

        case DEADLINE:
            String time = "";
            boolean stringHasEnded = false;

            for (int i = 1; i < array.length; i++) {
                //repeatedly append strings in the array until the time is found
                String currentArrayElement = array[i];
                if (stringHasEnded) {
                    time = currentArrayElement;
                    break;
                }
                if (currentArrayElement.equals("/by")) {
                    stringHasEnded = true;
                    continue;
                }
                str.append(currentArrayElement).append(" ");
            }
            //check if a time was entered
            if (!stringHasEnded) {
                System.out.println("Do you not have a deadline? If you do, you might as well enter it right?");
                return;
            }

            //check if a valid time was entered
            try {
                LocalDate date = LocalDateParser.parse(time);
                tempTask = new Deadlines(str.toString(), date);
            } catch (IllegalArgumentException exception) {
                System.out.println("Enter a valid date format! (yyyy-mm-dd)");
                return;
            }
            break;


        case EVENT:
            String eventTime = "";
            boolean stringHasTerminated = false;
            for (int i = 1; i < array.length; i++) {
                //repeatedly append strings in the array until the eventTime is found
                String currentArrayElement = array[i];
                if (stringHasTerminated) {
                    eventTime = currentArrayElement;
                    break;
                }
                if (currentArrayElement.equals("/at")) {
                    stringHasTerminated = true;
                    continue;
                }
                str.append(currentArrayElement).append(" ");
            }
            //check if a duration was entered
            if (!stringHasTerminated) {
                System.out.println("So you have an event, but not a time period when it is happening?");
                return;
            }

            //check if a valid time was entered
            try {
                LocalDate date = LocalDateParser.parse(eventTime);
                tempTask = new Events(str.toString(), date);
            } catch (IllegalArgumentException exception) {
                System.out.println("Enter a valid date format! (yyyy-mm-dd)");
                return;
            }
            break;
        }

        taskArray.add(tempTask);
        listIndex += 1;

        System.out.println("Ok can, sure. I have added this task as you wanted.");
        System.out.println(tempTask.toString());
        System.out.println("Now you have only " + listIndex + " tasks in the list. Try being more hardworking!");
        Storage.saveData();

    }

}
