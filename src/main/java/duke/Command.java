package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Command class will execute the different commands input from the user
 */
public class Command {

    private String input;

    Command(String input) {
        this.input = input;
    }
    /**
     * executes different commands from user and save tasks into a file
     *
     * @param tasks takes in TaskList object containing the list of tasks
     * @param ui takes in Ui object containing the list of message to user
     * @param save Storage object to store the TaskList
     */
    public String execute(TaskList tasks, Ui ui, Storage save) {
        try {
            Scanner sc = new Scanner(input);
            String filePath = "data/duke.txt";
            String command = Parser.parseCommand(sc.next());
            switch(command) {
            case "bye":
                return ui.showBye();
            case "list":
                return ui.list(tasks.list());
            case "done":
                return ui.done(tasks.doneTask(sc.nextInt() - 1));
            case "todo":
                String todoDescription = sc.nextLine().trim();
                Task todo = Parser.todoHelper(todoDescription, ui);
                tasks.add(todo);
                save.writeToFile(filePath, tasks);
                return ui.todo(tasks, todo);
            case "delete":
                int delNum = sc.nextInt() - 1;
                assert delNum < tasks.size() && delNum > -1
                        : "Task number should be in range";
                Task delete = tasks.get(delNum);
                tasks.remove(delNum);
                save.writeToFile(filePath, tasks);
                return ui.delete(tasks, delete);
            case "deadline":
                String[] deadlineArr = sc.nextLine().split("/by");
                Task deadline = Parser.deadlineHelper(deadlineArr, ui);
                tasks.add(deadline);
                save.writeToFile(filePath, tasks);
                return ui.deadline(tasks, deadline);
            case "event":
                String[] eventArr = sc.nextLine().split("/at");
                Task event = Parser.eventHelper(eventArr, ui);
                tasks.add(event);
                save.writeToFile(filePath, tasks);
                return ui.event(tasks, event);
            case "find":
                String keyword = sc.nextLine().trim();
                return ui.find(tasks.find(keyword, ui));
            case "doafter":
                String[] afterArr = sc.nextLine().split("/after");
                Task doAfter = Parser.doAfterHelper(afterArr, ui);
                tasks.add(doAfter);
                save.writeToFile(filePath, tasks);
                return ui.after(tasks, doAfter);
            default:
                return ui.defaultError();
            }
        } catch (DukeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return ui.showDeadlineError();
        } catch (IndexOutOfBoundsException e) {
            return ui.showNoSuchTaskError();
        }
    }
}
