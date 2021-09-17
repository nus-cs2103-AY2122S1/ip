package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * interprets string input from the user and translates it into commands, and uses Duke to execute them.
     * @param input string input from the user of Duke.
     * @return a boolean indicating whether Duke should continue or whether it should go to sleep based on the input.
     */
    public static boolean interpretCommand(String input) {

        boolean cont = true;

        if (input.split(" ")[0].equals("done")) {
            try {
                String[] splitString = input.split(" ");
                if (splitString.length == 1) {
                    throw new DukeException(
                            "____________________________________________________________\n"
                                + "☹ OOPS!!! Choose the task number to be considered done.\n"
                                + "____________________________________________________________"
                    );
                }

                String taskItemNumber = splitString[1];
                if (Integer.parseInt(taskItemNumber) > Duke.getTaskList().size()) {

                    throw new DukeException(
                            "____________________________________________________________\n"
                                    + "☹ OOPS!!! Choose a valid task number to be considered done.\n"
                                    + "____________________________________________________________"
                    );
                }
                Duke.markAsFinished(taskItemNumber);

                Storage.rewriteFile(Duke.getTaskList());
            } catch (DukeException e) {
                Ui.customErrorMessage(e.getMessage());
            }
        } else if (input.split(" ")[0].equals("todo")) {
            //input = input.replace(input.split(" ")[0], "");
            try {
                String actualToDo = input.replace(input.split(" ")[0], "");
                if (actualToDo.equals("")) {
                    throw new DukeException(
                            "____________________________________________________________\n"
                                    + "☹ OOPS!!! The description of a todo cannot be empty.\n"
                                    + "____________________________________________________________"
                    );
                }
                ToDo newToDo = new ToDo(actualToDo);
                Duke.addToList(newToDo);
                Storage.appendToFile(newToDo.toString());


            } catch (DukeException e) {
                Ui.customErrorMessage(e.getMessage());
            }
        } else if (input.split(" ")[0].equals("deadline")) {
            try {
                input = input.replace(input.split(" ")[0], "");
                if (input.split("/").length == 1) {
                    throw new DukeException(
                            "____________________________________________________________\n"
                                    + "☹ OOPS!!! The description or by-date (or both) cannot be empty.\n"
                                    + "____________________________________________________________"
                    );
                }
                String by = input.split("/")[1].split(" ", 2)[1];
                String description = input.split("/")[0];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                try {
                    LocalDateTime byDateAndTime = LocalDateTime.parse(by, formatter);
                    Deadline dead = new Deadline(description, byDateAndTime);
                    Duke.addToList(dead);
                    Storage.appendToFile(dead.toFileString());
                } catch (DateTimeParseException e) {
                    Ui.dateTimeError();
                }

            } catch (DukeException e) {
                Ui.customErrorMessage(e.getMessage());
            }
        } else if (input.split(" ")[0].equals("event")) {
            try {
                input = input.replace(input.split(" ")[0], "");

                if (input.split("/").length == 1) {
                    throw new DukeException(
                            "____________________________________________________________\n"
                                    + "☹ OOPS!!! The description of an event,"
                                    + " as well as its date and time, cannot be empty.\n"
                                    + "____________________________________________________________"
                    );
                }
                String date = input.split("/")[1].split(" ")[1];
                String time = input.split("/")[1].split(" ")[2];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                try {
                    LocalDateTime byDateAndTime = LocalDateTime.parse(date + " " + time, formatter);
                    String description = input.split("/")[0];
                    Event someEvent = new Event(description, byDateAndTime);
                    Duke.addToList(someEvent);
                    Storage.appendToFile(someEvent.toFileString());
                } catch (DateTimeParseException e) {
                    Ui.dateTimeError();
                }

            } catch (DukeException e) {
                Ui.customErrorMessage(e.getMessage());
            }
        } else if (input.equals("list")) {
            Duke.printList();
        } else if (input.equals("bye")) {
            Duke.makeInactive();
            Ui.sendEndMessage();

            //save txt file here or something idk
            Storage.rewriteFile(Duke.getTaskList());
            cont = false;
        } else if (input.split(" ")[0].equals("delete")) {

            try {
                if (input.split(" ").length == 1) {
                    throw new DukeException(
                            "____________________________________________________________\n"
                                    + "☹ OOPS!!! Please state the task number that you want to delete.\n"
                                    + "____________________________________________________________"
                    );
                }
                String taskToBeDeleted = input.split(" ")[1];
                Duke.deleteTask(taskToBeDeleted);

                //rewrite whole txt file
                Storage.rewriteFile(Duke.getTaskList());
            } catch (DukeException e) {
                Ui.customErrorMessage(e.getMessage());
            }

        } else if (input.split(" ")[0].equals("find")) {
            try {
                if (input.split(" ").length == 1) {
                    throw new DukeException(
                            "____________________________________________________________\n"
                                    + "☹ OOPS!!! Please tell me what you want to find!\n"
                                    + "____________________________________________________________"
                    );
                }

                String keyword = input.split(" ")[1];
                TaskList matchingTasks = Duke.findTask(keyword);

                if (matchingTasks.size() == 0) {
                    System.out.println("____________________________________________________________\n"
                            + "☹ OOPS!!! No tasks match your search.\n"
                            + "____________________________________________________________");
                } else {
                    int number = 1;
                    System.out.println("____________________________________________________________");
                    System.out.println("Here is what I found:");
                    for (int i = 0; i < matchingTasks.size(); i++) {
                        System.out.println(number + "." + matchingTasks.get(i).toString());
                        number++;
                    }
                }
            } catch (DukeException e) {
                Ui.customErrorMessage(e.getMessage());
            }
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println("____________________________________________________________");
        }

        return cont;
    }

    /**
     * Interprets string input like the above interpretCommand method, but this method instead returns a String
     * @param input string input from the user of Duke.
     * @return a String containing the response from Duke.
     */
    public static String getResponse(String input) {

        String response = "No response from Duke for some reason!";

        if (input.split(" ")[0].equals("done")) {
            try {
                String[] splitString = input.split(" ");
                if (splitString.length == 1) {
                    response = "____________________________________________________________\n"
                            + "☹ OOPS!!! Choose the task number to be considered done.\n"
                            + "____________________________________________________________";
                    throw new DukeException(
                            "____________________________________________________________\n"
                                    + "☹ OOPS!!! Choose the task number to be considered done.\n"
                                    + "____________________________________________________________"
                    );
                }

                String taskItemNumber = splitString[1];
                if (Integer.parseInt(taskItemNumber) > Duke.getTaskList().size()) {
                    response = "____________________________________________________________\n"
                            + "☹ OOPS!!! Choose a valid task number to be considered done.\n"
                            + "____________________________________________________________";
                    throw new DukeException(
                            "____________________________________________________________\n"
                                    + "☹ OOPS!!! Choose a valid task number to be considered done.\n"
                                    + "____________________________________________________________"
                    );
                }
                response = Duke.markAsFinished(taskItemNumber);

                Storage.rewriteFile(Duke.getTaskList());
            } catch (DukeException e) {
                Ui.customErrorMessage(e.getMessage());
            }
        } else if (input.split(" ")[0].equals("todo")) {
            try {
                String actualToDo = input.replace(input.split(" ")[0], "");
                if (actualToDo.equals("")) {
                    response = "____________________________________________________________\n"
                            + "☹ OOPS!!! The description of a todo cannot be empty.\n"
                            + "____________________________________________________________";
                    throw new DukeException(
                            "____________________________________________________________\n"
                                    + "☹ OOPS!!! The description of a todo cannot be empty.\n"
                                    + "____________________________________________________________"
                    );
                }
                ToDo newToDo = new ToDo(actualToDo);
                response = Duke.addToList(newToDo);
                Storage.appendToFile(newToDo.toString());


            } catch (DukeException e) {
                Ui.customErrorMessage(e.getMessage());
            }
        } else if (input.split(" ")[0].equals("deadline")) {
            try {
                input = input.replace(input.split(" ")[0], "");
                if (input.split("/").length == 1) {
                    response = "____________________________________________________________\n"
                            + "☹ OOPS!!! The description or by-date (or both) cannot be empty.\n"
                            + "____________________________________________________________";
                    throw new DukeException(
                            "____________________________________________________________\n"
                                    + "☹ OOPS!!! The description or by-date (or both) cannot be empty.\n"
                                    + "____________________________________________________________"
                    );
                }
                String by = input.split("/")[1].split(" ", 2)[1];
                String description = input.split("/")[0];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                try {
                    LocalDateTime byDateAndTime = LocalDateTime.parse(by, formatter);
                    Deadline dead = new Deadline(description, byDateAndTime);
                    response = Duke.addToList(dead);
                    Storage.appendToFile(dead.toFileString());
                } catch (DateTimeParseException e) {
                    Ui.dateTimeError();
                }

            } catch (DukeException e) {
                Ui.customErrorMessage(e.getMessage());
            }
        } else if (input.split(" ")[0].equals("event")) {
            try {
                input = input.replace(input.split(" ")[0], "");
                if (input.split("/").length == 1) {
                    response = "____________________________________________________________\n"
                            + "☹ OOPS!!! The description of an event,"
                            + " as well as its date and time, cannot be empty.\n"
                            + "____________________________________________________________";
                    throw new DukeException(
                            "____________________________________________________________\n"
                                    + "☹ OOPS!!! The description of an event,"
                                    + " as well as its date and time, cannot be empty.\n"
                                    + "____________________________________________________________"
                    );
                }
                String date = input.split("/")[1].split(" ")[1];
                String time = input.split("/")[1].split(" ")[2];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                try {
                    LocalDateTime byDateAndTime = LocalDateTime.parse(date + " " + time, formatter);
                    String description = input.split("/")[0];
                    Event someEvent = new Event(description, byDateAndTime);
                    response = Duke.addToList(someEvent);
                    Storage.appendToFile(someEvent.toFileString());
                } catch (DateTimeParseException e) {
                    Ui.dateTimeError();
                }

            } catch (DukeException e) {
                Ui.customErrorMessage(e.getMessage());
            }
        } else if (input.equals("list")) {
            response = Duke.printList();
        } else if (input.equals("bye")) {
            Duke.makeInactive();
            response = Ui.sendEndMessage();
            Storage.rewriteFile(Duke.getTaskList());
        } else if (input.split(" ")[0].equals("delete")) {

            try {
                if (input.split(" ").length == 1) {
                    response = "____________________________________________________________\n"
                            + "☹ OOPS!!! Please state the task number that you want to delete.\n"
                            + "____________________________________________________________";
                    throw new DukeException(
                            "____________________________________________________________\n"
                                    + "☹ OOPS!!! Please state the task number that you want to delete.\n"
                                    + "____________________________________________________________"
                    );
                }
                String taskToBeDeleted = input.split(" ")[1];
                response = Duke.deleteTask(taskToBeDeleted);
                Storage.rewriteFile(Duke.getTaskList());
            } catch (DukeException e) {
                Ui.customErrorMessage(e.getMessage());
            }

        } else if (input.split(" ")[0].equals("find")) {
            try {
                if (input.split(" ").length == 1) {
                    response = "____________________________________________________________\n"
                            + "☹ OOPS!!! Please tell me what you want to find!\n"
                            + "____________________________________________________________";
                    throw new DukeException(
                            "____________________________________________________________\n"
                                    + "☹ OOPS!!! Please tell me what you want to find!\n"
                                    + "____________________________________________________________"
                    );
                }

                String keyword = input.split(" ")[1];
                TaskList matchingTasks = Duke.findTask(keyword);

                if (matchingTasks.size() == 0) {
                    response = "____________________________________________________________\n"
                            + "☹ OOPS!!! No tasks match your search.\n"
                            + "____________________________________________________________";
                } else {
                    int number = 1;
                    response = "____________________________________________________________\n"
                            + "Here is what I found:\n";
                    for (int i = 0; i < matchingTasks.size(); i++) {
                        response += number + "." + matchingTasks.get(i).toString() + "\n";
                        number++;
                    }
                }
            } catch (DukeException e) {
                Ui.customErrorMessage(e.getMessage());
            }
        } else {
            response = "____________________________________________________________\n"
                    + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "____________________________________________________________";
        }

        return response;
    }
}
