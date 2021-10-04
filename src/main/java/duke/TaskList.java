package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.Storage.Storage;

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
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (i != tasks.size() - 1) {
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
     * @param isDone Whether task is done or not Bool.
     * @param localDateTime Datetime object.
     * @return duke.Task of requisite type.
     */
    public static Task initialiseByType(String task, Type type, Boolean isDone, LocalDateTime localDateTime) {
        if (type == Type.TODO) {
            return new Todo(task, type, isDone);
        } else if (type == Type.EVENT) {
            return new Event(task, type, isDone, localDateTime);
        } else {
            return new Deadline(task, type, isDone, localDateTime);
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

        if (scanner.hasNext()) {
            String input = scanner.nextLine();
            try {
                Parser.interpretInput(input, this.tasks);
            } catch (Exception err) {
                System.out.println(err.getMessage());
                run(scanner);
            }
            if (input.equals("bye")) {
                scanner.close();
            } else {
                run(scanner);
            }
        }
    }

    /**
    * Method to run chatbot with javafx UI.
     */
    public String runWithGraphicUI(String input) {
        List<String> tasks = Storage.readFile();
        try {
            this.tasks = Parser.loadTasks(tasks);
        } catch (FileParseErrorException err) {
            System.out.println(err);
        }

        String response = "";
        try {
            response = Parser.interpretInput(input, this.tasks);
        } catch (Exception err) {
            System.out.println(err.getMessage() + "THIS IS THE ERROR");
        }

        return response;
    }
}
