package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static boolean interpretCommand(String input) {

        boolean cont = true;

        if (input.split(" ")[0].equals("done")) {
            try {
                String[] splitString = input.split(" ");
                if (splitString.length == 1) {
                    throw new DukeException(
                            "____________________________________________________________\n" +
                                    "☹ OOPS!!! Choose the task number to be considered done.\n" +
                                    "____________________________________________________________"
                    );
                }

                String taskItemNumber = splitString[1];
                if (Integer.parseInt(taskItemNumber) > Duke.taskList.size()) {
                    throw new DukeException(
                            "____________________________________________________________\n" +
                                    "☹ OOPS!!! Choose a valid task number to be considered done.\n" +
                                    "____________________________________________________________"
                    );
                }
                Duke.markAsFinished(taskItemNumber);

                Storage.rewriteFile(Duke.taskList);
            } catch (DukeException e) {
                Ui.customErrorMessage(e.getMessage());
            }
        }   else if (input.split(" ")[0].equals("todo")) {
            //input = input.replace(input.split(" ")[0], "");
            try {
                String actualToDo = input.replace(input.split(" ")[0], "");
                if (actualToDo.equals("")) {
                    throw new DukeException(
                            "____________________________________________________________\n" +
                                    "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                                    "____________________________________________________________"
                    );
                }
                ToDo newToDo = new ToDo(actualToDo);
                Duke.addToList(newToDo);
//                System.out.println(actualToDo);
//                System.out.println(newToDo.toString());
                Storage.appendToFile(newToDo.toString());


            } catch (DukeException e) {
//                System.out.println(e.getMessage());
                Ui.customErrorMessage(e.getMessage());
            }
        }   else if (input.split(" ")[0].equals("deadline")) {
            try {
                input = input.replace(input.split(" ")[0], "");
                if (input.split("/").length == 1) {
                    throw new DukeException(
                            "____________________________________________________________\n" +
                                    "☹ OOPS!!! The description or by-date (or both) cannot be empty.\n" +
                                    "____________________________________________________________"
                    );
                }
                String by = input.split("/")[1].split(" ", 2)[1];
                String description = input.split("/")[0];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                try {
                    LocalDateTime byDateAndTime = LocalDateTime.parse(by, formatter);
//                        String description = input.split("/")[0];
                    //duke.Deadline dead = new duke.Deadline(description, by);
                    Deadline dead = new Deadline(description, byDateAndTime);
                    Duke.addToList(dead);
                    Storage.appendToFile(dead.toFileString());
                } catch (DateTimeParseException e) {
//                    System.out.println("Please follow the specified format for entering the date and time of the deadline.");
                    Ui.dateTimeError();
                }
//                    System.out.println(byDateAndTime.toString() + " for deadline");

            } catch (DukeException e) {
//                System.out.println(e.getMessage());
                Ui.customErrorMessage(e.getMessage());
            }
        }   else if (input.split(" ")[0].equals("event")) {
            try {
                input = input.replace(input.split(" ")[0], "");

                if (input.split("/").length == 1) {
                    throw new DukeException(
                            "____________________________________________________________\n" +
                                    "☹ OOPS!!! The description of an event, as well as its date and time, cannot be empty.\n" +
                                    "____________________________________________________________"
                    );
                }
                String date = input.split("/")[1].split(" ")[1];
                String time = input.split("/")[1].split(" ")[2];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//                    System.out.println(date);
//                    System.out.println(time);
//                    System.out.println(date + time);
                try {
                    LocalDateTime byDateAndTime = LocalDateTime.parse(date + " " + time, formatter);
//                    System.out.println(byDateAndTime.toString() + " for event");
                    String description = input.split("/")[0];
                    Event someEvent = new Event(description, byDateAndTime);
                    Duke.addToList(someEvent);
                    Storage.appendToFile(someEvent.toFileString());
                } catch (DateTimeParseException e) {
                    Ui.dateTimeError();
                }

            } catch (DukeException e) {
                //System.out.println(e.getMessage());
                Ui.customErrorMessage(e.getMessage());
            }
        }   else if (input.equals("list")) {
            Duke.printList();
        } else if (input.equals("bye")) {
            Duke.active = false;
            Ui.sendEndMessage();

            //save txt file here or something idk
            Storage.rewriteFile(Duke.taskList);
            cont = false;
        } else if (input.split(" ")[0].equals("delete")) {

            try {
                if (input.split(" ").length == 1) {
                    throw new DukeException(
                            "____________________________________________________________\n" +
                                    "☹ OOPS!!! Please state the task number that you want to delete.\n" +
                                    "____________________________________________________________"
                    );
                }
                String taskToBeDeleted = input.split(" ")[1];
                Duke.deleteTask(taskToBeDeleted);

                //rewrite whole txt file
                Storage.rewriteFile(Duke.taskList);
            } catch (DukeException e) {

            }

        } else {
            System.out.println("____________________________________________________________");
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println("____________________________________________________________");
        }

        return cont;
    }
}
