package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.ui.Ui;
import duke.util.Parser;
import duke.util.TaskList;
import duke.task.ToDo;

import java.io.*;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;


    private static final String tab = "      ";
    private static final String line = "------------------------------------------------------------";


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws NoSuchTaskException, InvalidInputException, FileNotFoundException {

        ui.showWelcome();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine().trim().replaceAll(" +", " ");

            // POLYMORPHISM! make a general method in the Task class and have each
            // different task handle the input differently!

            parser.parse(input);

            if (input.startsWith("delete ")) {
                String[] strings = input.split(" ");
                if (tasks.size() == 0) {
                    ui.showEmptyList();
                } else {
                    try {
                        if (strings.length < 2) {
                            ui.textBox("You did not specify which task to delete. Please wake up.");
                        } else {
                            int taskNumber = Integer.parseInt(strings[1]) - 1;
                            if (taskNumber < tasks.size()) {
                                ui.showDelete(tasks.get(taskNumber).toString());
                                // todo: make this a try catch
                                tasks.delete(taskNumber);
                            } else {
                                ui.textBox("You have entered an invalid task number. Fool.");
                            }
                        }
                    } catch (NumberFormatException ex) {
                        System.err.println(ex);
                    }
                }

            } else if (input.startsWith("done ")) {
                String[] strings = input.split(" ");
                if (tasks.size() == 0) {
                    ui.showEmptyList();
                } else {
                    try {
                        if (strings.length < 2) {
                            ui.textBox("You did not specify which task to complete.");
                        } else {
                            int taskNumber = Integer.parseInt(strings[1]) - 1;
                            if (taskNumber < tasks.size()) {
                                if (tasks.get(taskNumber).checkCompletion()) {
                                    ui.textBox("The task has already been completed, please be more attentive.");
                                } else {
                                    tasks.get(taskNumber).complete();
                                    ui.showComplete(tasks.get(taskNumber).toString());
                                }
                            } else {
                                System.out.println("You have entered an invalid task number. Fool.");
                            }
                        }
                    } catch (NumberFormatException ex) {
                        System.err.println(ex);
                    }
                }
            } else if (input.equals("list")) {
                if (tasks.size() == 0) {
                    ui.showEmptyList();
                } else {
                    System.out.println(tab + line);
                    System.out.println(tab + "Here are the tasks in your list:");
                    for (int j = 0; j < tasks.size(); j++) {
                        System.out.println(tab + " " + (j + 1) + ". " + tasks.get(j).toString());
                    }
                    System.out.println(tab + line);
                }
            } else if (input.equals("bye")) {
                storage.write(tasks);
                ui.showGoodbye();
                break;
            } else {
                String output;
                if (input.startsWith("todo ") && input.charAt(5) != 97) {
                    String[] strings = input.split(" ", 2);
                    ToDo toDo = new ToDo(strings[1]);
                    output = toDo.toString();
                    tasks.add(toDo);
                } else if (input.startsWith("deadline ") && input.charAt(9) != 97) {
                    String[] strings = input.split("/", 2);
                    try {
                        if (!input.contains("/") || strings[1].contains("/") || strings[1].length() < 1) {
                            throw new InvalidInputException("You fool. You cannot create a task without a description, " +
                                    "or you have formatted your description wrongly. Check your spaces and try again.");
                        }
                    } catch (InvalidInputException ex) {
                        System.err.println(ex);
                        continue;
                    }
                    String[] strings1 = strings[0].split(" ", 2);
                    String[] strings2 = strings[1].split(" ", 2);
                    Deadline deadline = new Deadline(strings1[1], strings2[1]);
                    output = deadline.toString();
                    tasks.add(deadline);
                } else if (input.startsWith("event ") && input.charAt(6) != 97) {
                    String[] strings = input.split("/", 2);
                    try {
                        if (!input.contains("/") || strings[1].contains("/") || strings[1].length() < 1) {
                            throw new InvalidInputException("You fool. You cannot create a task without a description, " +
                                    "or you have formatted your description wrongly. Check your spaces and try again.");
                        }
                    } catch (InvalidInputException ex) {
                        System.err.println(ex);
                        continue;
                    }
                    String[] strings1 = strings[0].split(" ", 2);
                    String[] strings2 = strings[1].split(" ", 2);
                    Event event = new Event(strings1[1], strings2[1]);
                    output = event.toString();
                    tasks.add(event);
                } else {
                    // Check if there are any spaces first. if there are no spaces, means that it is either an invalid input or no such task

                    // Level-5
                    try {
                        if ((input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) && !input.contains(" ")) {
                            throw new InvalidInputException("You fool. You cannot create a task without a description, " +
                                    "or you have formatted your description wrongly. Check your spaces and try again.");
                        } else {
                            try {
                                throw new NoSuchTaskException("You have entered an invalid message. Please do not commit this idiocy again.");
                            } catch (NoSuchTaskException ex) {
                                System.err.println(ex);
                                continue;
                            }
                        }
                    } catch (InvalidInputException ex) {
                        System.err.println(ex);
                        continue;
                    }

                }
                ui.showAdd(output, tasks.size());
            }
        }

    }

    public static void main(String[] args) throws InvalidInputException, NoSuchTaskException, FileNotFoundException {
        new Duke("./data/tasks.json").run();
    }
}
