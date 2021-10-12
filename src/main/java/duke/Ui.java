package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Ui {
    private String line = "---------------------------------------------";

    private TaskList taskList;

    Ui(TaskList list) {
        this.taskList = list;
    }

    int countTasks() {
        int count = 0;
        for (int i = 0; i < taskList.size(); i++) {
            if (!taskList.get(i).isDone) {
                count++;
            }
        }
        return count;
    }

    /**
     * Prints welcome message when bot is first started
     */
    public static void welcomeUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Generates statistics for user to see about different kind of tasks stored
     * @param item
     * @return String with task breakdown
     */
    public String statsReponse(String item) {
        String output = "";
        List<Integer> distribution = taskList.taskDistribution();
        String percentageCompleted = taskList.taskCompetedPercentage();
        output += distribution.get(0).toString() + " ToDos\n";
        output += distribution.get(1).toString() + " Deadlines\n";
        output += distribution.get(2).toString() + " Events\n";
        output += percentageCompleted + "% done\n";
        return output;
    }

    /**
     * Marks item as done on the list
     *
     * @param item User input to complete item from list
     */
    public String doneResponse(String item) {
        String output = "";
        try {
            item = item.replaceAll("\\D+", ""); //Extracts number from input
            int completedItem = Integer.parseInt(item);
            taskList.get(completedItem - 1).markAsDone(); //Set the task to done
            System.out.println(line);
            System.out.println("     " + "Nice! I've marked this task as done:");
            System.out.println("     " + taskList.get(completedItem - 1));
            System.out.println(line);
            output += line + "\n     Nice! I've marked this task as done:\n" + "     "
                    + taskList.get(completedItem - 1) + '\n' + line;

        } catch (IndexOutOfBoundsException e) {
            System.out.println(line);
            System.out.println("     " + "OOPS You dont have this many items in the list :)");
            System.out.println(line);

        }
        return output;
    }

    /**
     * Removes task from TaskList and updates user with number of tasks left to do
     *
     * @param item User input to delete item from list
     */
    public String deleteResponse(String item) {
        String output = "";
        item = item.replaceAll("\\D+", ""); //Extracts number from input
        int removeItem = Integer.parseInt(item);

        System.out.println(line);
        System.out.println("     " + "Noted. I've removed this task:");
        System.out.println("     " + taskList.get(removeItem - 1));
        System.out.println("     Now you have " + countTasks() + " task to be done on your list!");
        System.out.println(line);
        output += line + "\n     " + "Noted. I've removed this task:" + "     \n"
                + taskList.get(removeItem - 1) + "\n     Now you have " + countTasks()
                + " task to be done on your list!\n" + line;
        taskList.removeTask(removeItem - 1); //Removes item at the corresponding index
        return output;
    }

    /**
     * Generates standard response to user input bye
     * @return String
     */
    public String byeResponse() {
        String output = "";
        System.out.println(line);
        System.out.println("     " + "Bye. Hope to see you again soon!");
        System.out.println(line);
        output += line + "\n     " + "Bye. Hope to see you again soon!\n" + line;
        return output;
    }

    /**
     * Called when uses the list command and generates a list of items stored in the {@code taskList}
     * @return String with responses
     */
    public String listResponse() {
        String output = "";
        System.out.println(line);
        output += line + '\n';
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + String.valueOf(i + 1) + ". " + taskList.get(i).toString());
            output += "     " + String.valueOf(i + 1) + ". " + taskList.get(i).toString() + '\n';
        }
        System.out.println(line);
        output += line;
        return output;
    }

    /**
     *Response generated when user adds a todo item to the list
     * @param input
     * @return String message for user
     */
    public String todoResponse(String input) {
        System.out.println(line);
        String output = "";
        output += line;
        try {
            String description = input.substring(5, input.length());
            System.out.println("     added: " + new ToDo(input)); //Added item
            taskList.addTask(new ToDo(description)); //Added new task to arraylist
            System.out.println("     Now you have " + countTasks() + " task to be done on your list!");
            System.out.println(line);
            output += "\n     added: " + new ToDo(input) + '\n'
                    + "     Now you have " + countTasks()
                    + " task to be done on your list!\n" + line;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("     " + "Please tell us the todo task :)");
            output += "\n" + "Please tell us the todo task :)\n" + line;
            System.out.println(line);
        } catch (Exception e) {
            System.out.println(e);
        }
        return output;
    }

    /**
     *Response generated when user adds a deadline item to the list
     * @param input
     * @return String message for user
     */
    public String deadlineResponse(String input) {
        String output = "";
        try {
            String by = input.substring(input.lastIndexOf("/") + 1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localTimeObj = LocalDate.parse(by, formatter);
            String description = input.substring(9, input.lastIndexOf("/")); //Extract description
            System.out.println(line);
            System.out.println("     added: " + new Deadline(description, localTimeObj.toString())); //Added item
            taskList.addTask(new Deadline(description, localTimeObj.toString())); //Added new task to arraylist
            System.out.println("     Now you have " + countTasks() + " task to be done on your list!");
            System.out.println(line);
            output += line + "\n" + "     added: " + new Deadline(description, localTimeObj.toString())
                    + "\n" + "     Now you have " + countTasks() + " task to be done on your list!\n"
                    + line;
        } catch (Exception e) {
            String by = input.substring(input.lastIndexOf("/") + 1);
            String description = input.substring(9, input.lastIndexOf("/")); //Extract description
            System.out.println(line);
            System.out.println("     added: " + new Deadline(description, by.toString())); //Added item
            taskList.addTask(new Deadline(description, by)); //Added new task to arraylist
            System.out.println("     Now you have " + countTasks() + " task to be done on your list!");
            System.out.println(line);
            output += line + "\n" + "     added: " + new Deadline(description, by)
                    + "\n" + "     Now you have " + countTasks() + " task to be done on your list!\n"
                    + line;
        }
        return output;
    }

    /**
     *Response generated when user adds a event item to the list
     * @param input
     * @return String message for user
     */
    public String eventResponse(String input) {
        String output = "";
        try {
            String by = input.substring(input.lastIndexOf("/") + 1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localTimeObj = LocalDate.parse(by, formatter);
            String description = input.substring(6, input.lastIndexOf("/"));
            System.out.println(line);
            System.out.println("     added: " + new Event(description, localTimeObj.toString())); //Added item
            taskList.addTask(new Event(description, localTimeObj.toString())); //Added new task to arraylist
            System.out.println("     Now you have " + countTasks() + " task to be done on your list!");
            System.out.println(line);
            output += line + "\n     added: " + new Event(description, localTimeObj.toString()) + "\n"
                    + "     Now you have " + countTasks() + " task to be done on your list!\n"
                    + line;
        } catch (Exception e) {
            String by = input.substring(input.lastIndexOf("/") + 1);
            String description = input.substring(6, input.lastIndexOf("/"));
            System.out.println(line);
            System.out.println("     added: " + new Event(description, by)); //Added item
            taskList.addTask(new Event(description, by)); //Added new task to arraylist
            System.out.println("     Now you have " + countTasks() + " task to be done on your list!");
            System.out.println(line);
            output += line + "\n     added: " + new Event(description, by) + "\n"
                    + "     Now you have " + countTasks() + " task to be done on your list!\n"
                    + line;
        }
        return output;
    }
    /**
     *Response generated when user searches for an item in the list
     * @param input the item being looked for
     * @return String message for user
     */
    public String findResponse(String input) {
        String output = "";
        String keyWord = input.substring(input.lastIndexOf("find") + 5);
        TaskList results = taskList.findTask(keyWord);
        for (int i = 0; i < results.size(); i++) {
            Task result = results.get(i);
            System.out.println(result + "\n");
            output += result + "\n";
        }
        return output;
    }

    /**
     * Output generated when user types an invalid command
     * @return a String with standard response
     */
    public String invalidInput() {
        String output = "";
        System.out.println(line);
        System.out.println("     " + "OOPS You have entered an invalid input :)");
        System.out.println(line);
        output += line + "     \n" + "OOPS You have entered an invalid input :)\n" + line;
        return output;
    }

}
