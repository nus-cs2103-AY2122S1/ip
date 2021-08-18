import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Duke!\nWhat can I do for you?");

        //initialise scanner
        Scanner sc = new Scanner(System.in);
        boolean finished = false;

        //array to hold tasks
        ArrayList<Task> listOfTasks = new ArrayList<>();

        //string array to hold keywords
        String[] commandWords = {"bye", "list", "done", "todo", "deadline", "event", "/by", "/at", "delete"};

        while(!finished) {
            String userResponse = sc.nextLine();

            try {
                //bye
                if (userResponse.contains(commandWords[0])) {
                    System.out.println("Bye. Hope to see you again soon!");
                    finished = true;
                    break;
                //list
                } else if (userResponse.contains(commandWords[1])) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < listOfTasks.size(); i++) {
                        System.out.println((i + 1) + "." + listOfTasks.get(i).toString());
                    }
                //done
                } else if (userResponse.contains(commandWords[2])) {
                    try {
                        char indexDone = userResponse.charAt(5);
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
                //to do
                } else if (userResponse.contains(commandWords[3])) {
                    try {
                        String desc = userResponse.substring(5);
                        Todo newTodo = new Todo(desc);
                        listOfTasks.add(newTodo);

                        System.out.println("Got it. I'll add this task:");
                        System.out.println(newTodo);
                        System.out.println("Now you've got " + listOfTasks.size() + " tasks in your list.");
                    } catch (StringIndexOutOfBoundsException err) {
                        throw new DukeException("Todo command needs a description!");
                    }
                //deadline
                } else if (userResponse.contains(commandWords[4])) {
                    if (userResponse.contains(commandWords[6])) {
                        try {
                            int slashIndex = userResponse.indexOf("/by");
                            String desc = userResponse.substring(9, slashIndex - 1);
                            String time = userResponse.substring(slashIndex + 4);
                            Deadline newDeadline = new Deadline(desc, time);
                            listOfTasks.add(newDeadline);

                            System.out.println("Got it. I'll add this task:");
                            System.out.println(newDeadline);
                            System.out.println("Now you've got " + listOfTasks.size() + " tasks in your list.");
                        } catch (StringIndexOutOfBoundsException err) {
                            throw new DukeException("Date/time is needed!");
                        }
                    } else {
                        throw new DukeException("Deadline command needs date/time!");
                    }
                //event
                } else if (userResponse.contains(commandWords[5])) {
                    if (userResponse.contains(commandWords[7])) {
                        try {
                            int slashIndex = userResponse.indexOf("/at");
                            String desc = userResponse.substring(6, slashIndex - 1);
                            String time = userResponse.substring(slashIndex + 4);
                            Event newEvent = new Event(desc, time);
                            listOfTasks.add(newEvent);

                            System.out.println("Got it. I'll add this task:");
                            System.out.println(newEvent);
                            System.out.println("Now you've got " + listOfTasks.size() + " tasks in your list.");
                        } catch (StringIndexOutOfBoundsException err) {
                            throw new DukeException("Date/timeis needed!");
                        }
                    } else {
                        throw new DukeException("Event command needs date/time!");
                    }
                //delete
                } else if (userResponse.contains(commandWords[8])) {
                    try {
                        char indexDone = userResponse.charAt(7);
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
                } else {
                    throw new DukeException("Unrecognised command!");
                }
            } catch (DukeException dukeerr) {
                System.out.println(dukeerr.getMessage());
            }

        }

    }

}