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
            } else if (input.length() > 5 && input.substring(0, 5).equals("done ")) { //checks for done
                String[] split = input.split("\\s+");
                int number = Integer.parseInt(split[1]);
                return taskList.changeStatus(number);
            } else if (input.length() > 9 && input.substring(0, 9).equals("deadline ")) { //checks for deadline

                try {
                    String[] split = input.substring(9).split(" /by ");
                    String[] split2 = split[1].split("\\s+"); //split into date and time
                    LocalDate ld_D = LocalDate.parse(split2[0]);
                    return taskList.list(new Deadline(split[0], ld_D, split2[1]));
                } catch (Exception e) {
                    return "Invalid description/date/time";
                }

            } else if (input.length() > 5 && input.substring(0, 5).equals("todo ")) { //checks for todo
                return taskList.list(new ToDo(input.substring(5)));
            } else if (input.length() > 6 && input.substring(0, 6).equals("event ")) { //checks for event

                try {
                    String[] split = input.substring(6).split(" /at ");
                    LocalDate ld_E = LocalDate.parse(split[1]);
                    return taskList.list(new Event(split[0], ld_E));
                } catch (Exception e) {
                    return e.getMessage();
                }

            } else if (input.length() > 7 && input.substring(0, 7).equals("delete ")) {
                String[] split = input.substring(6).split("\\s+");
                return taskList.deleteTask(Integer.parseInt(split[1]));
            } else if (input.length() > 5 && input.substring(0, 5).equals("find ")) {
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
