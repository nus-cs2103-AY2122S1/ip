package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        Scanner sc = new Scanner(input);
        String filePath = "data/duke.txt";
        String command = Parser.parseCommand(sc.next());
        switch(command) {
        case "bye":
            return ui.showBye();
        case "list":
            String list = "";
            int listNum = 1;
            for (int i = 0; i < tasks.size(); i++) {
                list += listNum + "." + tasks.get(i) + "\n";
                listNum++;
            }
            return ui.list(list);
        case "done":
            int doneNum = sc.nextInt() - 1;
            try {
                tasks.get(doneNum).markAsDone();
                return ui.done(tasks.get(doneNum));
            } catch (IndexOutOfBoundsException e) {
                return ui.showDoneError();
            }
        case "todo":
            try {
                String todoDescription = sc.nextLine().trim();
                Task todo = new Todo(todoDescription);
                if (todoDescription.isEmpty()) {
                    throw new DukeException(ui.emptyDescriptionError());
                }
                tasks.add(todo);
                save.writeToFile(filePath, tasks);
                return ui.todo(tasks, todo);
            } catch (DukeException e) {
                return e.getMessage();
            }
        case "delete":
            int delNum = sc.nextInt() - 1;
            try {
                Task delete = tasks.get(delNum);
                tasks.remove(delNum);
                save.writeToFile(filePath, tasks);
                return ui.delete(tasks, delete);
            } catch (IndexOutOfBoundsException e) {
                return ui.showDeleteError();
            }
        case "deadline":
            try {
                String[] deadlineArr = sc.nextLine().split("/by");
                if (deadlineArr[0].strip().isEmpty()) {
                    throw new DukeException(ui.emptyDescriptionError());
                }
                LocalDate d1 = LocalDate.parse(deadlineArr[1].trim());
                Task deadline = new Deadline(deadlineArr[0].trim(),
                        d1.format(DateTimeFormatter.ofPattern("MMM dd YYYY")));
                tasks.add(deadline);
                save.writeToFile(filePath, tasks);
                return ui.deadline(tasks, deadline);
            } catch (DateTimeParseException e) {
                ui.showDeadlineError();
                break;
            } catch (DukeException e) {
                return e.getMessage();
            }
        case "event":
            try {
                String[] eventArr = sc.nextLine().split("/at");
                if (eventArr[0].strip().isEmpty()) {
                    throw new DukeException(ui.emptyDescriptionError());
                }
                Task event = new Event(eventArr[0].trim(), eventArr[1].trim());
                tasks.add(event);
                save.writeToFile(filePath, tasks);
                return ui.event(tasks, event);
            } catch (DukeException e) {
                return e.getMessage();
            }
        case "find":
            try {
                String keyword = sc.nextLine().trim();
                String findList = "\n";
                if (keyword.strip().isEmpty()) {
                    throw new DukeException(ui.emptyDescriptionError());
                }
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i).getDescription().contains(keyword)) {
                        findList += tasks.get(i) + "\n";
                    }
                }
                return ui.find(findList);
            } catch (DukeException e) {
                return e.getMessage();
            }
        default:
            return ui.defaultError();
        }
        return null;
    }
}
