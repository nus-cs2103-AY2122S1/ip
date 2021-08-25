package duke;

import java.io.IOException;
import java.util.List;

public class Parser {
    public TaskList taskList;
    public UserInterface userInterface;
    public Storage storage;

    public Parser(TaskList taskList, UserInterface userInterface, Storage storage) {
        this.storage = storage;
        this.taskList = taskList;
        this.userInterface = userInterface;
    }

    public void parse(String input) throws InvalidCommandException,
            MissingToDoDescriptionException, MissingDeadlineDescriptionException,
            MissingEventDescriptionException {
        try {
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (input.equals("list")){
                userInterface.showList();
            } else if (input.contains("done")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.markAsDone(index);
                userInterface.showCompletedTask(taskList.getTask(index));
                storage.write();
            } else if (input.contains("todo") || input.contains("deadline") || input.contains("event")){
                if (input.contains("todo") && input.length() == 4) {
                    throw new MissingToDoDescriptionException();
                } else if (input.contains("deadline") && input.length() == 8) {
                    throw new MissingDeadlineDescriptionException();
                } else if (input.contains("event") && input.length() == 5) {
                    throw new MissingEventDescriptionException();
                } else {
                    Task t = taskList.addItem(input);
                    userInterface.showAddedTask(t);
                    storage.write();
                }
            } else if (input.contains("delete")) {
                int index = Integer.parseInt(input.split(" " )[1]) - 1;
                Task t = taskList.deleteItem(index);
                userInterface.showDeletedTask(t);
                storage.write();
            } else if (input.contains("find")) {
                String keyword = input.split(" ")[1];
                List<Task> results = taskList.find(keyword);
                userInterface.showResults(results);
            } else {
                throw new InvalidCommandException();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
