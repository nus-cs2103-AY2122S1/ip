import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

import java.nio.file.Path;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    public static void updateText(ArrayList<Task> listArray, int count, Path filePath) throws IOException {
        //function to update the text each time after any new action is done
        BufferedWriter botList = new BufferedWriter(
                new FileWriter(filePath.toString()));
            for (int i = 0; i < count; i++) {
                botList.write(listArray.get(i).write() + "\n");
            }
            botList.close();
        }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String welcomeLine = "Hello! I'm Duke\n" + "What can I do for you?\n";
        String goodbyeLine = "Bye. Hope to see you again soon!";
        System.out.println(welcomeLine);
        boolean isRunning = true;
        String input;
        ArrayList<Task> listArray = new ArrayList<Task>(100);
        int count = 0;
        int index;
        String[] split;
        String home = System.getProperty("user.home");
        Path path = java.nio.file.Paths.get(
                home, "Downloads", "School Stuff", "CS2103", "Githubs", "ip", "data");
        Path filePath = java.nio.file.Paths.get(
                home, "Downloads", "School Stuff", "CS2103", "Githubs", "ip", "data", "duke.txt");
        boolean directoryExist = java.nio.file.Files.exists(path);
        Scanner sc = new Scanner(System.in);
        do {
            try {
                if (!directoryExist) {
                    new File(path.toString()).mkdirs();
                }
                //creates the duke.txt file to be written into
                input = sc.next();
                Task task;
                //Reads the next input
                switch (input) {
                case "bye":
                    //Shuts down the bot when it gets a "bye" prompt
                    isRunning = false;
                    System.out.println(goodbyeLine);
                    updateText(listArray, count, filePath);
                    break;
                case "list":
                    //Returns the list when it gets a "list" prompt
                    if (count == 0) {
                        //When the list is empty
                        System.out.println("The list is empty!");
                    } else {
                        for (int i = 0; i < count; i++) {
                            System.out.println(i + 1 + ". " + listArray.get(i));
                        }
                    }
                    break;
                case "done":
                    //Marks the task as done when it gets the "done x" prompt and updates the task as well.
                    input = sc.nextLine();
                    if (input.isEmpty()) {
                        //Catch if there is no number after the command
                        throw new DukeException("☹ OOPS!!! done will require a task number to update.");
                    }
                    index = Integer.parseInt(input.trim());
                    if (index > count || index <= 0) {
                        //Catches if the number is > than the number of task or if its negative
                        throw new DukeException("☹ OOPS!!! The number is not in within the number of tasks!");
                    }
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + listArray.get(index - 1).done());
                    break;
                case "todo":
                    //Inputs a todo task when given the "todo" prompt
                    input = sc.nextLine();
                    if (input.isEmpty()) {
                        //Catch if todo description is empty
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    task = new Todo(input.trim());
                    listArray.add(count++, task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + listArray.get(count - 1));
                    System.out.println("Now you have " + count + " in the list.");
                    updateText(listArray, count, filePath);
                    break;
                case "deadline":
                    //Inputs a Deadline task when given the "deadline" prompt
                    input = sc.nextLine();
                    if (input.isEmpty()) {
                        //Catch if deadline description is empty
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    split = input.split(" /by ");
                    if (split.length <= 1) {
                        throw new DukeException("☹ OOPS!!! Your deadline input format is not valid!");
                    }
                    task = new Deadline(split[0].trim(), split[1]);
                    listArray.add(count++, task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + listArray.get(count - 1));
                    System.out.println("Now you have " + count + " in the list.");
                    updateText(listArray, count, filePath);
                    break;
                case "event":
                    //Inputs an Event task when given the "event" prompt
                    input = sc.nextLine();
                    if (input.isEmpty()) {
                        //Catch if event description is empty
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    split = input.split(" /at ");
                    if (split.length <= 1) {
                        throw new DukeException("☹ OOPS!!! Your event input format is not valid!");
                    }
                    task = new Event(split[0].trim(), split[1]);
                    listArray.add(count++, task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + listArray.get(count - 1));
                    System.out.println("Now you have " + count + " in the list.");
                    updateText(listArray, count, filePath);
                    break;
                case "delete":
                    //deletes the task when it gets the "delete x" prompt and updates the task as well.
                    input = sc.nextLine();
                    if (input.isEmpty()) {
                        //Catch if there is no number after the command
                        throw new DukeException("☹ OOPS!!! delete will require a task number to update.");
                    }
                    index = Integer.parseInt(input.trim());
                    if (index > count || index <= 0) {
                        //Catches if the number is > than the number of task or if its negative
                        throw new DukeException("☹ OOPS!!! The number is not in within the number of tasks!");
                    }
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + listArray.remove(index - 1));
                    System.out.println("Now you have " + --count + " in the list.");
                    updateText(listArray, count, filePath);
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e2) {
                //For catching any invalid number after the done command
                System.out.println("☹ OOPS!!! done will require a valid integer value input to update.");
            } catch (IOException e3) {
                System.out.println("☹ OOPS!!! It seems like I am unable to create a list in your directory");
            } catch (DateTimeParseException e4) {
                System.out.println("☹ OOPS!!! It seems like your date/time format is wrong!");
                System.out.println("Please input date in this format: YYYY-MM-DD");
                System.out.println("Please input any time in this format: HH:MM");
            }
        } while (isRunning);
    }
}
