import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class Duke {
    ArrayList<Task> listOfTasks = new ArrayList<>();
    String[] commandWords = {"bye", "list", "done", "todo", "deadline", "event", "delete", "check"};

    public Duke() {

    }

    /**
     * Returns the index of any found keywords in the given string.
     * If no keywords are found, return -1.
     *
     * @param s  The user input string.
     * @return The integer index.
     */
    public int determineKeyword (String s) {

        for (int i = 0; i < commandWords.length; i++) {
            if (s.contains(commandWords[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Handles the keyword "done".
     * Scans the input string to determine which task to mark as done.
     *
     * @param s  The user input string.
     */
    public void chooseDone(String s) {
        try {
            char indexDone = s.charAt(5);
            if (Character.isDigit(indexDone)) {
                int numberIndex = Character.getNumericValue(indexDone) - 1;
                if (numberIndex < listOfTasks.size() && numberIndex >= 0) {
                    listOfTasks.get(numberIndex).markAsDone();
                    System.out.println("Nice, I've marked this task as done!\n" + listOfTasks.get(numberIndex).toString());
                } else {
                    throw new DukeException("This task does not exist!");
                }
            } else {
                throw new DukeException("Invalid task number!");
            }
        } catch (NullPointerException err) {
            throw new DukeException("This task does not exist!");
        } catch (StringIndexOutOfBoundsException err) {
            throw new DukeException("Task number needed!");
        }
    }

    /**
     * Handles the keyword "to-do".
     * Scans the input string and adds a to-do task to the listOfTasks array.
     *
     * @param s  The user input string.
     */
    public void chooseTodo(String s) {
        try {
            String desc = s.substring(5);
            Todo newTodo = new Todo(desc);
            listOfTasks.add(newTodo);

            System.out.println("Got it. I'll add this task:");
            System.out.println(newTodo);
            System.out.println("Now you've got " + listOfTasks.size() + " tasks in your list.");
        } catch (StringIndexOutOfBoundsException err) {
            throw new DukeException("Todo command needs a description!");
        }
    }

    /**
     * Handles the keyword "deadline".
     * Scans the input string and adds a deadline task to the listOfTasks array.
     *
     * @param s  The user input string.
     */
    public void chooseDeadline(String s) {
        if (s.contains("/by")) {
            try {
                int slashIndex = s.indexOf("/by");
                String desc = s.substring(9, slashIndex - 1);
                String time = s.substring(slashIndex + 4);
                Deadline newDeadline = new Deadline(desc, time);
                try {
                    String checkValid = newDeadline.toString();
                    System.out.println("Got it. I'll add this task:");
                    System.out.println(newDeadline);
                    listOfTasks.add(newDeadline);
                    System.out.println("Now you've got " + listOfTasks.size() + " tasks in your list.");
                } catch (Exception err) {
                    throw new DukeException("Wrong date/time format! YYYY-MM-DD HH:MM, (time optional!)");
                }
            } catch (StringIndexOutOfBoundsException err) {
                throw new DukeException("Date/time is needed!");
            }
        } else {
            throw new DukeException("Deadline command needs date/time!");
        }
    }

    /**
     * Handles the keyword "event".
     * Scans the input string and adds an event task to the listOfTasks array.
     *
     * @param s  The user input string.
     */
    public void chooseEvent(String s) {
        if (s.contains("/at")) {
            try {
                int slashIndex = s.indexOf("/at");
                String desc = s.substring(6, slashIndex - 1);
                String time = s.substring(slashIndex + 4);
                Event newEvent = new Event(desc, time);

                try {
                    String checkValid = newEvent.toString();
                    System.out.println("Got it. I'll add this task:");
                    System.out.println(newEvent);
                    listOfTasks.add(newEvent);
                    System.out.println("Now you've got " + listOfTasks.size() + " tasks in your list.");
                } catch (Exception err) {
                    throw new DukeException("Wrong date/time format! YYYY-MM-DD HH:MM-HH:MM");
                }
            } catch (StringIndexOutOfBoundsException err) {
                throw new DukeException("Date/time is needed!");
            }
        } else {
            throw new DukeException("Event command needs date/time!");
        }
    }

    /**
     * Handles the keyword "delete".
     * Scans the input string and deletes the specified task from the listOfTasks array.
     *
     * @param s  The user input string.
     */
    public void chooseDelete(String s) {
        try {
            char indexDone = s.charAt(7);
            if (Character.isDigit(indexDone)) {
                int numberIndex = Character.getNumericValue(indexDone) - 1;
                if (numberIndex < listOfTasks.size() && numberIndex >= 0) {
                    System.out.println("Noted, I've removed this task\n" + listOfTasks.get(numberIndex).toString());
                    listOfTasks.remove(numberIndex);
                    System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
                } else {
                    throw new DukeException("This task does not exist!");
                }
            } else {
                throw new DukeException("Invalid task number!");
            }
        } catch (NullPointerException err) {
            throw new DukeException("This task does not exist!");
        } catch (StringIndexOutOfBoundsException err) {
            throw new DukeException("Task number needed!");
        }
    }

    /**
     * Handles the keyword "list".
     * Iterates through the listOfTasks array and prints each task description out.
     *
     */
    public void chooseList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println((i + 1) + "." + listOfTasks.get(i).toString());
        }

    }

    /**
     * Handles the keyword "check".
     *
     *
     */
    public void chooseCheck(String s) {
        try {
            String[] segments = s.split(" ");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter dateFormatterOutput = DateTimeFormatter.ofPattern("MMM dd yyyy");
            LocalDate inputDate = LocalDate.parse(segments[1], dateFormatter);
            String inputDateOutput = inputDate.format(dateFormatterOutput);

            int counter = 1;

            System.out.println("Showing important deadlines/events on " + inputDateOutput);
            for (int i = 0; i < listOfTasks.size(); i++) {
                String taskDate = listOfTasks.get(i).getDate();
                if (inputDateOutput.equals(taskDate)) {
                    System.out.println(counter + "." + listOfTasks.get(i).toString());
                    counter++;
                }
            }

        } catch (Exception e) {
            throw new DukeException("Wrong date format! YYYY-MM-DD");
        }
    }


    public static void main(String[] args) {
        System.out.println("Hello, I'm Duke!\nWhat can I do for you?");

        //initialise scanner
        Scanner sc = new Scanner(System.in);
        boolean finished = false;

        Duke duke = new Duke();

        while(!finished) {
            String userResponse = sc.nextLine();
            int choice = duke.determineKeyword(userResponse);

            try {
                //bye
                if (choice == 0) {
                    System.out.println("Bye. Hope to see you again soon!");
                    finished = true;
                    break;
                    //list
                } else if (choice == 1) {
                    duke.chooseList();
                    //done
                } else if (choice == 2) {
                    duke.chooseDone(userResponse);
                    //to do
                } else if (choice == 3) {
                    duke.chooseTodo(userResponse);
                    //deadline
                } else if (choice == 4) {
                    duke.chooseDeadline(userResponse);
                    //event
                } else if (choice == 5) {
                    duke.chooseEvent(userResponse);
                    //delete
                } else if (choice == 6) {
                    duke.chooseDelete(userResponse);
                } else if (choice == 7) {
                    duke.chooseCheck(userResponse);
                } else {
                    throw new DukeException("Unrecognised command!");
                }
            } catch (DukeException dukeerr) {
                System.out.println(dukeerr.getMessage());
            }

        }

    }

}