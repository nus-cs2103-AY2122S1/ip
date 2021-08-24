package Duke;

import java.io.IOException;
import java.time.LocalDate;

public class Parser {
    private TaskList tasks;
    private Storage storage;

    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

//    public ArrayList<Duke.Task> getTasks() {
//        return this.tasks.inArrayList();
//    }
//
//    public String getFilePath() {
//        return this.storage.getPath();
//    }

    public Storage getStorage() {
        return this.storage;
    }

    public void endSession() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void done(String input) throws InvalidTaskIndexException {
        if (input.length() > 5 && Character.isDigit(input.charAt(5))) {
            try {
                int pos = Integer.parseInt(input.substring(5)) - 1;
                if (pos < 0) {
                    throw new InvalidTaskIndexException("Duke.Task list index starts from 1!");
                }

                else if (pos < tasks.arrSize()) {
                    tasks.markDone(pos);
                    try {
                        storage.writeToFile(tasks.inArrayList());
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }

                }
                else {
                    throw new InvalidTaskIndexException("There are only " + pos + " tasks!");
                }
            } catch (NumberFormatException nfe) {
                throw new InvalidTaskIndexException("A task index should only contain numbers!");
            }

        } else {
            throw new InvalidTaskIndexException("Please specify the task index to be marked as done!");
        }
    }

    public void add(String input) throws InvalidFormatException, DukeException, InvalidTaskIndexException {
        if (input.startsWith("todo ") || input.equals("todo")) {
            if (input.length() > 5 ) {
                tasks.addTodo(input.substring(5));
                try {
                    storage.writeToFile(tasks.inArrayList());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
            else {
                throw new InvalidFormatException("☹ OOPS!!! The description of a todo cannot be empty.");
            }

        }
        else if (input.startsWith("deadline ") || input.equals("deadline")) {
            if (!input.contains(" /by ")) {
                throw new InvalidFormatException("OOPS!! To add a Duke.Deadline, type -> deadline <Description> /by <deadline>!");
            }
            if (input.length() > 12 && input.contains("/by")) {
                String[] spl = input.substring(9).split("/");
                if (spl[0].length() < 2 || spl[1].length() < 4) {
                    throw new InvalidFormatException("☹ OOPS!!! The descriptions of a deadline cannot be empty.");
                }
                else {
                    LocalDate date = LocalDate.parse(spl[1].substring(3));
                    if (date.isBefore(LocalDate.now())) {
                        throw new DukeException("The deadline was in the past!");
                    } else {
                        tasks.addDeadline(spl[0], date);
                        try {
                            storage.writeToFile(tasks.inArrayList());
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                    }
                }
            }
            else {
                throw new InvalidFormatException("☹ OOPS!!! The descriptions of a deadline cannot be empty.");
            }

        }
        else if (input.startsWith("event ") || input.equals("event") ) {
            if (!input.contains(" /at ")) {
                throw new InvalidTaskIndexException("OOPS!! To add an Duke.Event, type -> event <Description> /at <details>!");
            }
            if (input.length() > 9 && input.contains("/at")) {
                String[] spl = input.substring(6).split("/");
                if (spl[0].length() < 2 || spl[1].length() < 4) {
                    throw new InvalidFormatException("☹ OOPS!!! The descriptions of an event cannot be empty.");
                }
                else {
                    tasks.addEvent(spl[0], spl[1].substring(3));

                    try {
                        storage.writeToFile(tasks.inArrayList());
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                }
            }
            else {
                throw new InvalidFormatException("☹ OOPS!!! The descriptions of an event cannot be empty.");
            }

        }
        else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public void delete(String input) throws InvalidTaskIndexException {
        if (input.length() > 7 && Character.isDigit(input.charAt(7))) {
            try {
                int pos = Integer.parseInt(input.substring(7)) - 1;

                if (pos < 0) {
                    throw new InvalidTaskIndexException("Duke.Task list index starts from 1!");
                } else if (pos < tasks.arrSize()) {
                    String temp = tasks.inArrayList().get(pos).toString();

                    tasks.deleteTask(pos);
                    try {
                        storage.writeToFile(tasks.inArrayList());
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }

                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(temp);

                    System.out.println("Now you have " + tasks.arrSize() + " task(s) in the list.");
                } else {
                    throw new InvalidTaskIndexException("There are only " + pos + " tasks!");
                }
            } catch (NumberFormatException nfe) {
                throw new InvalidTaskIndexException("A task index should only contain numbers!");
            }
        }
    }

    public void list() {
        tasks.listTasks();
    }
}
