import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        // Introduction message
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        drawLine();

        //Initialise list to store tasks and Scanner to get user input
        List<Task> tasks = new ArrayList<>(100);
        Scanner sc = new Scanner(System.in);

        // Main Code
        while(true) {
            String userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                dukeReply("Bye. Hope to see you again soon!");
                break;
            } else if(userInput.equalsIgnoreCase("list")) {
                showTasks(tasks);
                // cant put done word as a task
            }else if (userInput.toLowerCase().startsWith("done")) {
                markTaskDone(userInput, tasks);
            } else if(userInput.toLowerCase().startsWith("delete")) {
                deleteTask(userInput, tasks);
            } else {
                try {
                    addTask(userInput, tasks);
                }catch(IllegalArgumentException e) {
                    dukeReply("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }catch(StringIndexOutOfBoundsException e) {
                    //Occurs if only type of task is entered. Maybe add a checcValidity funcitno
                    dukeReply("OOPS!!! The description of a todo cannot be empty.");
                }
            }
        }
    }

    public static void deleteTask(String userInput, List<Task> tasks) {
        int taskToDel = Integer.parseInt(userInput.substring(7)) - 1;
        tasks.remove(taskToDel);
        Task task = tasks.get(taskToDel);
        dukeReply(String.format("Noted. I've removed this task:\n%s\nNow you have %s tasks in list"
                , task, tasks.size()));
    }

    /**
     * Adds a task to the List of tasks.
     * @param userInput String of task to add.
     * @param tasks List of current tasks.
     */
    public static void addTask(String userInput, List<Task> tasks) {
        Task taskToAdd;

        if(userInput.toLowerCase().startsWith("todo")) {
            taskToAdd = new ToDo(userInput.substring(5));
        } else if (userInput.toLowerCase().startsWith("deadline")){
            int dateIndex = userInput.indexOf("/by");
            String[] dateAndTask = sepDateFromTask(dateIndex,9, userInput);
            taskToAdd = new Deadline(dateAndTask[0], dateAndTask[1]);
        } else if(userInput.toLowerCase().startsWith("event")) {
            int dateIndex = userInput.indexOf("/at");
            String[] dateAndTask = sepDateFromTask(dateIndex,6, userInput);
            taskToAdd = new Event(dateAndTask[0], dateAndTask[1]);
        } else {
            throw new IllegalArgumentException("Please specify type of task");
        }

        tasks.add(taskToAdd);
        dukeReply(String.format("Got it. I've added this task:\n" +
                "%s\nNumber of tasks: %s", taskToAdd.toString(), tasks.size()));
    }

    /**
     * Seperates the date and task from the users input.
     * @param taskIndex Beginning index of the task in the given String.
     * @param dateIndex Beginning index of the date in the given String.
     * @param userInput String of task containing task and date.
     * @return Array with task at index 0 and date at index 1.
     */
    public static String[] sepDateFromTask(int dateIndex, int taskIndex, String userInput) {
        String task;
        String date;
        if(dateIndex > 0) {
            task = userInput.substring(taskIndex, dateIndex - 1);
            date = userInput.substring(dateIndex + 4);
        }else {
            task = userInput.substring(taskIndex);
            date = "?";
        }
        return new String[] {task, date};
    }

    /**
     * Marks a task as complete.
     * @param userInput Text beginning with 'done' followed by a number.
     * @param tasks List of current tasks.
     */
    public static void markTaskDone(String userInput, List<Task> tasks) {
        // catch .NumberFormatException here and invalid inputs - neg, doesnt exisst
        // StringIndexOutOfBoundsException -- done
        //IndexOutOfBoundsException - done number not there
        int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
        Task task = tasks.get(taskIndex);
        if(task.checkStatus()) {
            dukeReply("Youve already marked this task as done!");
        } else {
            task.setDone();
            dukeReply("Nice! I've marked this task as done:\n" + task.toString());
        }
    }

    /**
     * Prints the list of todos sequentially.
     * @param tasks List of current tasks.
     */
    public static void showTasks(List<Task> tasks) {
        drawLine();
        System.out.println("Here are the tasks in your list:");
        for( int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i).toString());
        }
        drawLine();
    }

    /**
     * Prints dukes reply in between two lines.
     * @param message Duke's reply.
     */
    public static void dukeReply(String message) {
        drawLine();
        System.out.println(message);
        drawLine();
    }

    /**
     * Draws a line on the screen to separate speech.
     */
    public static void drawLine() {
        System.out.println("------------------------");
    }

}
