/**
 * This class handles the parsing of the user input into Duke
 *
 * Generally, it parses the command and returns a Command object.
 *
 */


public class DukeParser {

    public Command parse(String input) {
        String[] parsedInput = input.split(" ", 2);
        String command = parsedInput[0];
        if (command.equals("list")) {
            return new DisplayTasksCommand();
        } else if (command.equals("bye")) {
            return new QuitCommand();
        } else if (command.equals("help")) {
            return new QuitCommand(); //TODO
        } else if (command.equals("done")) {
            // assume command is in the form "done x"
            Integer index = Integer.parseInt(parsedInput[1].trim());

            // subtract 1 as task list is 0-indexed but on display it is 1-indexed.
            return new MarkDoneCommand(index - 1);
        } else if (command.equals("todo")) {
            String desc = parsedInput[1];
            return new AddTodoCommand(desc, false);
        } else if (command.equals("event")) {

        }
        return new AddTaskCommand("read book", false);
    }

    /*
    String[] next = sc.nextLine().split(" ", 2);
            String command = next[0];
            if (command.equals("done")) {
                System.out.println(sepLine);
                try {
                    // Mark a task as done
                    // We assume the command is of the form "done xxx" where xxx is an integer
                    Integer taskNum = Integer.parseInt(next[1]);
                    Task taskToComplete = taskList.get(taskNum - 1);
                    taskToComplete.markAsDone();
                    System.out.println("The task has been marked as done!");
                    System.out.println(taskToComplete);
                    System.out.println(sepLine);
                    StringBuilder textString = new StringBuilder(100);
                    for (Task t : taskList) {
                        textString.append(t.saveText());
                    }
                    Duke.writeLineToFile(textString.toString(), taskFile);
                } catch (NumberFormatException e) {
                    System.out.println("It seems like you have entered an invalid number for done.");
                    System.out.println("Please enter the task number as shown in the list.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("That task number does not exist in your list of tasks.");
                } catch (IOException e) {
                    System.out.println("Failed to write to file");
                }
            } else if (command.equals("delete")) {
                System.out.println(sepLine);
                try {
                    Integer taskNum = Integer.parseInt(next[1]);
                    Task taskToDelete = taskList.get(taskNum - 1);
                    System.out.println("The task has been deleted!");
                    System.out.println(taskToDelete);
                    taskList.remove(taskNum - 1);
                    System.out.println(sepLine);
                    StringBuilder textString = new StringBuilder(100);
                    for (Task t : taskList) {
                        textString.append(t.saveText());
                    }
                    Duke.writeLineToFile(textString.toString(), taskFile);
                } catch (NumberFormatException e) {
                    System.out.println("It seems like you have entered an invalid number to delete.");
                    System.out.println("Please enter the task number as shown in the list.");
                    System.out.println(sepLine);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("That task number does not exist in your list of tasks.");
                    System.out.println(sepLine);
                } catch (IOException e) {
                    System.out.println("Failed to write to file.");
                }
            } else if (command.equals("help")) {
                String helpMessage = sepLine + "\n HELP \n" + sepLine + "\n"
                        + "Available commands: \n"
                        + "'help' - Opens this dialog. \n"
                        + "'list' - Opens your list of tasks. \n"
                        + "'todo (desc)' - Adds a todo item with the given description. \n"
                        + "'deadline (desc) /by (due date)' - Adds a deadline item to your task list "
                        + "with the given description and due date. \n"
                        + "'event (desc) /at (timing)' - Adds a event item to your task list "
                        + "with the given description and timing. \n"
                        + "'done (x)' - Marks the task with number x as done "
                        + "according to the list given by the command 'list' \n"
                        + "'delete (x)' - Deletes the task with number x "
                        + "according to the list given by the command 'list' \n"
                        + "'bye' - Quits this program. \n"
                        + sepLine + "\n"
                        + "To use any command, follow the structure as shown, entering your values \n"
                        + "in place of anything in brackets. \n"
                        + sepLine;
                System.out.println(helpMessage);
            } else {
                // Task is added to task list
                try {
                    if (command.equals("todo")) {
                        Todo todo = new Todo(next[1], false);
                        taskList.add(todo);
                        System.out.println(sepLine + "\n added: " + todo + "\n");
                        System.out.println("You now have " + taskList.size() + " tasks");
                        System.out.println(sepLine);
                    } else if (command.equals("deadline")) {
                        // Add a deadline to the task list
                        String[] text = next[1].split(" /by ");
                        try {
                            String desc = text[0];
                            LocalDate dueDate = LocalDate.parse(text[1]);
                            Deadline dl = new Deadline(desc, false, dueDate);
                            taskList.add(dl);
                            System.out.println(sepLine + "\n added: " + dl + "\n");
                            System.out.println("You now have " + taskList.size() + " tasks");
                            System.out.println(sepLine);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new MissingFieldException();
                        } catch (DateTimeParseException e) {
                            System.out.println("Failed to parse date given");
                        }
                    } else if (command.equals("event")) {
                        // Add an event to the task list
                        String[] text = next[1].split(" /at ");
                        try {
                            String desc = text[0];
                            String time = text[1];
                            Event event = new Event(desc, false, time);
                            taskList.add(event);
                            System.out.println(sepLine + "\n added: " + event + "\n");
                            System.out.println("You now have " + taskList.size() + " tasks");
                            System.out.println(sepLine);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new MissingFieldException();
                        }
                    } else {
                        System.out.println(sepLine + "\n I did not understand that command."
                                + " Type 'help' for more info \n" + sepLine);
                    }
     */
}
