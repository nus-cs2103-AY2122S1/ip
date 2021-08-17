import java.util.Scanner;

public class Duke {
    //To check if input is an integer, used as a helper function when user inputs "done 2"
    public static boolean isInteger(String userInput) {
        if (userInput == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        //Statement to show the user upon exit
        String byeStatement = "    ____________________________________________________________\n" +
            "     Bye. Hope to see you again soon!\n" +
            "    ____________________________________________________________";

        //Statement to show the user upon running Duke
        String greetingStatement = "    ____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________";
        //Array to store whatever text entered by the user
        Task[] userList = new Task[100];
        //Pointer for array
        int pointer = 0;

        Scanner userInput = new Scanner(System.in);
        System.out.println(greetingStatement);

        while (true) {
            String description = userInput.nextLine();
            String[] descriptionArray = description.split(" ");
            String keyword = descriptionArray[0];
            try {
                //Added a check to ensure only valid inputs are recorded
                if (description.equals("")) {
//                    System.out.println("    ____________________________________________________________\n" +
//                        "     Please insert a valid input!\n" +
//                        "    ____________________________________________________________");
                    throw new DukeException("Please insert a valid input!");
                }
                //Stop duke if user types "bye"
                else if (keyword.equals("bye")) {
                    //Checks if description is left empty
                    if (descriptionArray.length == 1) {
                        break;
                    } else {
                        throw new DukeException("The description of a bye MUST be empty.");
                    }
                }
                //prints out all the stored tasks if user types "list"
                else if (keyword.equals("list")) {
                    //Checks if description is left empty
                    if (descriptionArray.length == 1) {
                        if (pointer == 0) {
                            System.out.println("    ____________________________________________________________\n" +
                                "    Oops, you have no tasks currently." +
                                "    ____________________________________________________________");
                        } else {
                            System.out.println("    ____________________________________________________________\n    " + "Here are the tasks in your list:");
                            for (int i = 0; i < pointer; i++) {
                                System.out.println("    " + (i + 1) + ". " + userList[i].toString());
                            }
                            System.out.println("    ____________________________________________________________");
                        }
                    } else {
                        throw new DukeException("The description of a list MUST be empty.");
                    }
                }
                //checks if keyword is todo and there is a description of the todo
                else if (keyword.equals("todo")) {
                    //checks if description is empty
                    if (descriptionArray.length > 1) {
                        userList[pointer] = new ToDo(description.replace(keyword, ""));
                        System.out.println("    ____________________________________________________________\n    " +
                            "Got it. I've added this task:\n    " + userList[pointer].toString() + "\n    " + "Now you have " + (pointer + 1) + " tasks in the list.\n" +
                            "    ____________________________________________________________");
                        pointer++;
                    } else {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                }
                //checks if keyword is deadline and there is a description of the deadline
                else if (keyword.equals("deadline")) {
                    //checks if description is empty
                    if (descriptionArray.length == 1 || description.endsWith("/by")) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    } else {

                        //checks if there is a "/by" to separate the description
                        if (description.contains("/by")) {
                            //Removes the "deadline" string and splits the description using "/by"
                            String[] updatedDeadline = description.replace(keyword, "").split("/by");
                            //Returns error if user enters more than one "/by"
                            if (updatedDeadline.length > 2) {
                                throw new DukeException("I'm sorry, please only have ONE '/by' in your description!");
                            } else {
                                String deadlineDescription = updatedDeadline[0];
                                String deadlineBy = updatedDeadline[1];
                                userList[pointer] = new Deadline(deadlineDescription, deadlineBy);
                                System.out.println("    ____________________________________________________________\n    " +
                                    "Got it. I've added this task:\n    " + userList[pointer].toString() + "\n    " + "Now you have " + (pointer + 1) + " tasks in the list.\n" +
                                    "    ____________________________________________________________");
                                pointer++;
                            }
                        } else {
                            throw new DukeException("I'm sorry, please add a '/by' in your description!");
                        }
                    }
                }
                //checks if keyword is event and there is a description of the event
                else if (keyword.equals("event")) {
                    //checks if description is empty
                    if (descriptionArray.length == 1 || description.endsWith("/at")) {
                        throw new DukeException("The description of an event cannot be empty.");
                    } else {
                        //checks if there is an "/at" to separate the description
                        if (description.contains("/at")) {
                            //Removes the "event" string and splits the description using "/at"
                            String[] updatedEvent = description.replace(keyword, "").split("/at");
                            //Returns error if user enters more than one "/at"
                            if (updatedEvent.length > 2) {
                                throw new DukeException("I'm sorry, please only have ONE '/at' in your description!");
                            } else {
                                String eventDescription = updatedEvent[0];
                                String eventBy = updatedEvent[1];
                                userList[pointer] = new Event(eventDescription, eventBy);
                                System.out.println("    ____________________________________________________________\n    " +
                                    "Got it. I've added this task:\n    " + userList[pointer].toString() + "\n    " + "Now you have " + (pointer + 1) + " tasks in the list.\n" +
                                    "    ____________________________________________________________");
                                pointer++;
                            }
                        } else {
                            throw new DukeException("I'm sorry, please add an '/at' in your description!");
                        }
                    }
                }
                //marks task as done if user types "done" and a number
                else if (keyword.equals("done")) {
                    //checks if there is a 2nd input(completed task number)
                    if (descriptionArray.length == 2) {
                        //check if 2nd input is an integer
                        if (isInteger(descriptionArray[1])) {
                            int taskNumber = Integer.parseInt(descriptionArray[1]);
                            //Checks for a valid task number
                            if (taskNumber > pointer || taskNumber <= 0) {
                                throw new DukeException("Please insert a valid Task Number!");
                            }
                            //If task number is valid mark task as done
                            else {
                                userList[taskNumber - 1].markAsDone();
                                System.out.println("    ____________________________________________________________\n    " + "Nice! I've marked this task as done:");
                                System.out.println("    " + userList[taskNumber - 1].toString());
                                System.out.println("    ____________________________________________________________");
                            }
                        }
                        //else throw DukeException for invalid scenarios ie "done two" instead of "done 2"
                        else {
                            throw new DukeException("I'm sorry, please input a number instead!");
                        }
                    } else {
                        throw new DukeException("I'm sorry, please input the number of the completed task");
                    }

                }
                //else throw DukeException for invalid keyword
                else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println("    ____________________________________________________________\n" +
                    " ☹ OOPS!!! " + e.getMessage() + "\n" +
                    "    ____________________________________________________________");
            }
        }
        System.out.println(byeStatement);
    }
}
