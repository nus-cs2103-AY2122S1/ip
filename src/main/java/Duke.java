import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void printError(Exception e) throws DialogException {
        if (Dialog.have(e.toString())) {
            System.out.println(Dialog.getDialog(e.toString()));
        } else {
            Dialog errorMessage = Dialog.generate(e.toString());
            errorMessage.add("☹ OOPS!!! " + e.getMessage());
            System.out.println(errorMessage);
        }
    }


    public static void main(String[] args) throws DialogException {
        Scanner sc = new Scanner(System.in);

        Dialog greeting = Dialog.generate("greeting");


        // not sure if we are allowed to change the file name
        String logo =
                "     ___       __       __    ______  _______\n" +
                "        /   \\     |  |     |  |  /      ||   ____|\n" +
                "       /  ^  \\    |  |     |  | |  ,----'|  |__\n" +
                "      /  /_\\  \\   |  |     |  | |  |     |   __|\n" +
                "     /  _____  \\  |  `----.|  | |  `----.|  |____\n" +
                "    /__/     \\__\\ |_______||__|  \\______||_______|\n";

        greeting.add(logo);
        greeting.add("Hello! I'm Alice, your personal assistant");
        greeting.add("What can I do for you?");
        Dialog commandsList = Dialog.generate("commands");
        commandsList.add("This is the following commands, I can perform:\n");
        commandsList.add("1. 'todo <task description>' - add a todo task to the list");
        commandsList.add("2. 'deadline <task description> /by <by when>' - add a deadline task with specific deadline");
        commandsList.add("3. 'event <task description> /at <at when>' - add an event task with specific time");
        commandsList.add("2. 'list' - show the current task list");
        commandsList.add("3. 'done <task index>' - mark that task as done");
        commandsList.add("4. 'commands' - show this current command window");
        commandsList.add("5. 'bye' - end session");
        System.out.println(greeting);
        System.out.println(commandsList);
        System.out.print("> ");
        String input = sc.nextLine();
        TaskDialog list = (TaskDialog) TaskDialog.generate("list");
        while (!input.equals("bye")) {
            String command = input.split(" ")[0];
            switch(command) {
                case "list":
                    System.out.println(list);
                    break;
                case "todo": {
                    try {
                        if (input.split(" ").length == 1) {
                            throw new EmptyDescriptionException("The description of a todo cannot be empty.");
                        }
                        list.addTask(new Todo(input.substring(("todo ").length())));
                    } catch (DialogException | EmptyDescriptionException e) {
                        Duke.printError(e);
                    }
                    break;
                }
                case "deadline": {
                    try {
                        if (input.split(" ").length == 1) {
                            throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
                        } else if (!input.contains("/by")) {
                            throw new EmptyTaggerException("No /by tagger found.");
                        }
                        String dDescription = input.substring(("deadline ").length(), input.indexOf("/"));
                        String by = input.substring(input.indexOf("/by ") + "/by ".length());
                        list.addTask(new Deadline(dDescription, by));
                    } catch (DialogException | EmptyDescriptionException | EmptyTaggerException e) {
                      Duke.printError(e);
                    }
                    break;
                }
                case "event": {
                    try {
                        if (input.split(" ").length == 1) {
                            throw new EmptyDescriptionException("The description of an event cannot be empty.");
                        } else if (!input.contains("/at")) {
                            throw new EmptyTaggerException("No /at tagger found.");
                        }
                        String eDescription = input.substring(("event ").length(), input.indexOf("/"));
                        String at = input.substring(input.indexOf("/at ") + "/at ".length());
                        list.addTask(new Event(eDescription, at));
                    } catch (DialogException | EmptyDescriptionException | EmptyTaggerException e) {
                        Duke.printError(e);
                    }
                    break;
                }
                case "done": {
                    try {
                        if (input.split(" ").length == 1) {
                            throw new EmptyIndexException("The index of done cannot be empty.");
                        } else if (Integer.parseInt(input.split(" ")[1]) <= 0 || Integer.parseInt(input.split(" ")[1]) > list.taskLength()) {
                            if (list.taskLength() == 0) {
                                throw new InvalidIndexException("Looks like your list is currently empty.");
                            } else {
                                throw new InvalidIndexException("Your list index can only be from 1 to " + list.taskLength() + ".");
                            }
                        } else if (input.split(" ").length > 2) {
                            throw new InvalidArgumentException("The number of arguments seems to exceed for command done.");
                        }
                        list.markTaskAsDone(Integer.parseInt(input.substring(("done ").length())) - 1);
                    } catch (EmptyIndexException | InvalidArgumentException | InvalidIndexException e) {
                        Duke.printError(e);
                    }
                    break;
                }

                case "commands":
                    System.out.println(commandsList);
                    break;
                default:
                    printError(new DukeException("I'm sorry, but I don't know what that means :-("));
                    break;
            }
            System.out.print("> ");
            input = sc.nextLine();
        }

        Dialog bye = Dialog.generate("bye");
        bye.add("Bye. Hope to see you again soon!");
        System.out.println(bye);
    }
}
