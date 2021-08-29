package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {

    /** Initialising an empty array into which tasks can be added/manipulated/deleted */
    private List<Task> tasks = new ArrayList<>();

    /**
     * This method provides a string that is the visual representation of the tasks seen by the user.
     * @return The visual representation of task list.
     */
    public static String taskListString(List<Task> tasks) {
        StringBuilder ans = new StringBuilder();
        ans.append("Here are the tasks in your list:\n");
        for(int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if(i != tasks.size()-1) {
                ans.append(String.format("%d. %s\n", i + 1, task.toString()));
            } else {
                ans.append(String.format("%d. %s", i + 1, task.toString()));
            }
        }
        return ans.toString();
    }

    /**
     * Initialise event subclass by type.
     * @param task duke.Task string.
     * @param type duke.Type enum.
     * @param done Whether task is done or not Bool.
     * @param localDateTime Datetime object.
     * @return duke.Task of requisite type.
     */
    public static Task initialiseByType(String task, Type type, Boolean done, LocalDateTime localDateTime) {
        if(type == Type.TODO) {
            return new Todo(task, type, done);
        } else if(type == Type.EVENT) {
            return new Event(task, type, done, localDateTime);
        } else {
            return new Deadline(task, type, done, localDateTime);
        }
    }

    /**
     * The string that is outputted when user terminates bot.
     * @return Message to say bye to user.
     */
    public String byeString() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Assesses the input and activates the necessary response.
     * @param input The string of input command.
     * @throws DukeException Exceptions specific to this chatbot.
     */
    public void interpretInput(String input) throws DukeException{
        String task;
        Type type;
        LocalDateTime localDateTime;
        if(input.equals("bye")) {
            System.out.println(byeString());
        } else if(input.equals("list")) {
            System.out.println(taskListString(tasks));
        } else if(input.equals("hello")) {
            System.out.println("Hello! I'm duke.Duke\n" +
                    "What can I do for you?");
        } else if (input.startsWith("done ")) {
            Ui.doneTask(Integer.parseInt(input.substring(5)), tasks);
        } else if(input.startsWith("todo ")) {
            // Remove all whitespaces to test if it is empty
            String testInput = input.replaceAll("\\s+","");
            if(testInput.equals("todo")) {
                throw new EmptyTodoException();
            }
            task = input.substring(5);
            type = Type.TODO;
            localDateTime = null;
            Ui.addTask(task, type, localDateTime, tasks);
        } else if (input.startsWith("deadline ")) {
            task = input.substring(9);
            type = Type.DEADLINE;
            String[] tokens = task.split(" /by ");
            localDateTime = Parser.parseDate(tokens[1]);
            task = tokens[0];
            Ui.addTask(task, type, localDateTime, tasks);
        } else if (input.startsWith("event ")) {
            task = input.substring(6);
            type = Type.EVENT;
            String[] tokens = task.split(" /at ");
            localDateTime = Parser.parseDate(tokens[1]);
            task = tokens[0];
            Ui.addTask(task, type, localDateTime, tasks);
        } else if (input.startsWith("delete ")) {
            Ui.deleteTask(Integer.parseInt(input.substring(7)), tasks);
        } else if (input.startsWith("find ")) {
            List<Task> filteredTasks = Ui.findTasks(input.substring(5), tasks);
            System.out.println(taskListString(filteredTasks));
        } else {
            throw new CommandNotFoundException();
        }
        try {
            Storage.writeToFile(tasks);
        } catch(IOException err) {
            System.out.println(err);
        }

    }

    /**
     * Method call to activate the chatbot.
     * If command bye is given, the chatbot terminates.
     * @param scanner Scanner that takes in the input.
     */
    public void run(Scanner scanner) {
        List<String> tasks = Storage.readFile();
        try {
            this.tasks = Parser.loadTasks(tasks);
        } catch (FileParseErrorException err) {
            System.out.println(err);
        }

        if(scanner.hasNext()) {
            String input = scanner.nextLine();
            try {
                interpretInput(input);
            } catch(DukeException dukeException) {
                System.out.println(dukeException.getMessage());
                run(scanner);
            }
            if(input.equals("bye"))  {
                scanner.close();
            } else {
                run(scanner);
            }
        }
    }
}
