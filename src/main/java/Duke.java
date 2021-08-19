import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private enum Tasks {DEADLINE, EVENT, TODO}

    private static ArrayList<Task> taskArray = new ArrayList<>();
    private static int listIndex = 0;

    public static void main(String[] args) {
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
                continue;
            }

            //case if user wants to exit the program
            if (firstString.equals("bye")) {
                break;
            }

            //cases for specified keywords
            if (firstString.equals("list")) {
                if (listIndex == 0) {
                    System.out.println("There are currently no tasks! Stop being so lazy and start doing your tasks!");
                    continue;
                }
                for (int i = 0; i < listIndex; i++) {
                    int listNumber = i + 1;
                    System.out.println(listNumber + ". " + taskArray.get(i).toString());
                }
                continue;
            }

            if (firstString.equals("done") ) {
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
                        System.out.println("Sorry, that task does not exist!");
                        continue;
                    }
                    Task currentTask = taskArray.get(arrayIndex);
                    currentTask.setCompleted();
                    System.out.println("Ok, very nice. I have set the following task as completed.\n" + currentTask.toString());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number!");
                } finally {
                    continue;
                }
            }

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
            System.out.println("Whoops, I could not understand your input. Please try again!");
        }

        //exit from the program
        scan.close();
        System.out.println("See you again next time!");
    }

    private static void createTask(Tasks taskType, String[] array) {
        //preliminary check if more than 1 string was entered
        if (array.length < 2) {
            //case if no name is entered for the task
            System.out.println("Whoops! Please enter a name for your Task!");
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
                System.out.println("Please enter a time for your deadline!");
                return;
            }

            tempTask = new Deadlines(str.toString(), time);
            break;


        case EVENT:
            String eventTime = "";
            boolean stringHasTerminated = false;

            for (int i = 1; i < array.length; i++) {
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
                System.out.println("Please enter a duration for your event!");
                return;
            }

            tempTask = new Events(str.toString(), eventTime);
            break;

        }

        taskArray.add(tempTask);
        listIndex += 1;

        System.out.println("Ok can, sure. I have added this task as you wanted.");
        System.out.println(tempTask.toString());
        System.out.println("Now you have only " + listIndex + " tasks in the list. Try being more hardworking!");

    }

}
