package duke;

import java.time.LocalDate;

/**
 * Takes in command from user and checks what to do with it
 */
public class Parser {
    TaskList taskList = new TaskList();
    Ui ui = new Ui();

    Parser() {
    }

    /**
     * Check if description is missing
     *
     * @param input
     * @return validity
     */
    private boolean checkInvalidDescription(String input) {
        if (input.trim().equals("todo") || input.trim().equals("deadline") || input.trim().equals("event")) {
            return true;
        }
        return false;
    }

    public void load() {
        taskList.load();
    }

    /**
     * Take in input from user and checks what commands to run
     *
     * @param input from user
     */
    public String commands(String input) {
        try {
            if (input.equals("list")) { //checks for list
                return taskList.printList();
            } else if (input.length() > 5 && input.startsWith("done ")) { //checks for done
                String[] splitBySpace = input.split("\\s+");
                int number = Integer.parseInt(splitBySpace[1]);
                assert(splitBySpace[1].length() > 0): "Index is empty";
                return taskList.changeStatus(number);
            } else if (input.length() > 9 && input.startsWith("deadline ")) { //checks for deadline

                try {
                    String[] split = input.substring(9).split(" /by ");
                    String[] splitBySpace = split[1].split("\\s+"); //split into date and time
                    LocalDate ld_D = LocalDate.parse(splitBySpace[0]);
                    return taskList.list(new Deadline(split[0], ld_D, splitBySpace[1]));
                } catch (Exception e) {
                    throw new InvalidArgument();
                }

            } else if (input.length() > 5 && input.startsWith("todo ")) { //checks for todo
                assert (input.substring(5).length() > 0): "Description is empty";
                return taskList.list(new ToDo(input.substring(5)));
            } else if (input.length() > 6 && input.startsWith("event ")) { //checks for event

                try {
                    String[] split = input.substring(6).split(" /at ");
                    LocalDate ld_E = LocalDate.parse(split[1]);
                    return taskList.list(new Event(split[0], ld_E));
                } catch (Exception e) {
                    throw new InvalidArgument();
                }

            } else if (input.length() > 7 && input.startsWith("delete ")) {
                String[] split = input.substring(6).split("\\s+");
                assert (split[1].length() > 0): "Description is empty";
                return taskList.deleteTask(Integer.parseInt(split[1]));
            } else if (input.length() > 5 && input.startsWith("find ")) {
                String toFind = input.substring(5);
                return taskList.find(toFind);
            } else if (checkInvalidDescription(input)) {
                throw new InvalidDescription(input);
            } else if (input.equals("bye")) {
                return ui.bye();
            } else {
                throw new InvalidArgument();
            }

        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
