package duke;

public class Parser {
    public static boolean parse(String input, Ui ui, Storage storage, TaskList tasks) {
        if (input.equals("bye")) {
            ui.exit();
            return true;
        } else {
            try {
                if (input.equals("list")) {
                    ui.displayList(tasks);
                } else if (input.contains("done")) {
                    tasks.handleDone(input, storage, ui);
                } else if (input.contains("todo")) {
                    tasks.handleTodo(input, storage, ui);
                } else if (input.contains("deadline")) {
                    tasks.handleDeadline(input, storage, ui);
                } else if (input.contains("event")) {
                    tasks.handleEvent(input, storage, ui);
                } else if (input.contains("delete")) {
                    tasks.handleDelete(input, storage, ui);
                } else {
                    System.out.println("can type properly pls");
                }
            } catch (DukeException err) {
                ui.showLoadingError(err);
            }
            return false;
        }
    }
}
