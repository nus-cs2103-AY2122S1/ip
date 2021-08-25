package duke;

import java.time.LocalDate;

public class Parser {
    TaskList taskList = new TaskList();

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

    public void commands(String input) {
        try {
            if (input.equals("list")) {
                taskList.printList();
            } else if (input.length() > 5 && input.substring(0, 5).equals("done ")) {
                String[] split = input.split("\\s+");
                int number = Integer.parseInt(split[1]);
                taskList.changeStatus(number);
            } else if (input.length() > 9 && input.substring(0, 9).equals("deadline ")) { //date then time
                String[] split = input.substring(9).split(" /by ");
                String[] split2 = split[1].split("\\s+"); //split into date and time
                LocalDate ld_D = LocalDate.parse(split2[0]);
                taskList.list(new Deadline(split[0], ld_D, split2[1]));
            } else if (input.length() > 5 && input.substring(0, 5).equals("todo ")) { //empty
                taskList.list(new ToDo(input.substring(5)));
            } else if (input.length() > 6 && input.substring(0, 6).equals("event ")) { //date
                String[] split = input.substring(6).split(" /at ");
                LocalDate ld_E = LocalDate.parse(split[1]);
                taskList.list(new Event(split[0], ld_E));
            } else if (input.length() > 7 && input.substring(0, 7).equals("delete ")) {
                String[] split = input.substring(6).split("\\s+");
                taskList.deleteTask(Integer.parseInt(split[1]));
            } else if (checkInvalidDescription(input)) {
                throw new InvalidDescription(input);
            } else if (input.length() > 0) {
                throw new InvalidArguement();
            }

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
