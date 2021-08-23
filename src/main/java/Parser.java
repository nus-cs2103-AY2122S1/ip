import java.time.LocalDate;

public class Parser {
    private final TaskList taskList;
    private final Ui ui;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
        this.ui = new Ui();
    }

    boolean parseInput(String input) {
        ui.printDivider();
        try {
            if (input.equals("bye")) {
                ui.print("Goodbye human. See you soon!");
                return false;
            } else if (input.equals("list")) {
                printItems();
            } else if (input.contains("done")) {
                markAsDone(input);
            } else if (input.contains("delete")) {
                deleteItem(input);
            } else {
                addItem(input);
            }
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }

        ui.printDivider();
        return true;
    }

    void addItem(String input) throws DukeException {
        Task newItem = null;
        if (input.contains("todo")) {
            String[] parsedInput = input.split(" ", 2); // Splits input into array of [todo, ...]
            if (isIncomplete(parsedInput)) {
                throw new ToDoException();
            }

            newItem = new ToDo(parsedInput[1]);
        } else if (input.contains("deadline")) {
            String[] parsedInput = input.split(" ", 2); // Splits input into array of [deadline, ...]
            if (isIncomplete(parsedInput)) {
                throw new DeadlineException();
            }

            String[] description = parsedInput[1].split("/");
            if (isIncomplete(description)) {
                throw new DeadlineException("The deadline cannot be empty. Please provide a deadline for this task.");
            }

            String name = description[0];
            String deadline = description[1].split(" ")[1];
            newItem = new Deadline(name, LocalDate.parse(deadline));
        } else if (input.contains("event")) {
            String[] parsedInput = input.split(" ", 2); // Splits input into array of [event, ...]
            if (isIncomplete(parsedInput)) {
                throw new EventException();
            }

            String[] description = parsedInput[1].split("/");
            if (isIncomplete(description)) {
                throw new EventException("The time of event cannot be empty. Please provide a time for this task.");
            }
            String name = description[0];
            String time = description[1].split(" ", 2)[1];
            newItem = new Event(name, time);
        } else {
            throw new DukeException("I'm sorry, but I do not quite understand what that means :(");
        }

        taskList.add(newItem);
    }

    void markAsDone(String input) throws DukeException {
        int index = getIndexFromInput(input);
        taskList.markTaskAsDone(index);
    }

    void deleteItem(String input) throws DukeException {
        int index = getIndexFromInput(input);
        taskList.delete(index);
    }

    int getIndexFromInput(String input) throws DukeException {
        String[] parsedInput = input.split(" ");
        if (isIncomplete(parsedInput)) {
            throw new DukeException("I do not know which task you are referring to. Please provide a number.");
        }
        return Integer.parseInt(parsedInput[1]);
    }

    boolean isIncomplete(String[] arr) {
        return arr.length <= 1;
    }

    void printItems() {
        taskList.printItems();
    }
}
