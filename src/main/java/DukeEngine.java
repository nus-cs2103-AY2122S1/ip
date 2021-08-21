package main.java;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DukeEngine {
    private final DukeMessages messages = new DukeMessages();
    private final DatabaseEngine databaseEngine = new DatabaseEngine();

    private List<Task> taskList = new ArrayList<Task>();

    /**
     * Main engine to run the program.
     */
    public void runProgram() {

        this.loadData();

        //initialises the scanner
        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {

            //saves the latest copy of the task list
            this.saveData();

            //removes additional space from the input
            input = sc.nextLine().trim();

            //removes space in the command input and stores the strings in an array
            String[] inputArr = input.split(" ");

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                messages.displayListItems(this.taskList);
            } else if (inputArr[0].equals("done")) {
                
                //obtains the task number which we want to mark as done
                Integer index = Integer.parseInt(inputArr[1]);

                try {
                    this.markAsDone(index);
                } catch (DukeException e) {
                    messages.displayText(e.toString());
                }
                
            } else if (inputArr[0].equals("delete")) {

                //obtains the task number which we want to delete
                Integer index = Integer.parseInt(inputArr[1]);

                try {
                    this.deleteTask(index);
                } catch (DukeException e) {
                    messages.displayText(e.toString());
                }
            }
            else {

                //adds item input by the user into the inputList
                try {
                    this.addTask(inputArr);
                } catch (DukeException e) {
                    messages.displayText(e.toString());
                }
            }
        }
        //terminates scanner
        sc.close();
    }

    public void loadData() {

        databaseEngine.createDirectory();
        databaseEngine.createDatabase();
        this.taskList = databaseEngine.readFromDatabase();
    }


    /**
     * Adds a TooDo Task into the List containing Tasks.
     * @param inputArr String array containing input by the user.
     * @throws InvalidCommandException if inputArr length < 2.
     */
    public void addTask(String[] inputArr) throws InvalidCommandException {
        try {
            if (inputArr[0].equals("todo")) {
                this.addTodo(inputArr);

            } else if (inputArr[0].equals("deadline")) {
                this.addDeadline(inputArr);

            } else if (inputArr[0].equals("event")) {
                this.addEvent(inputArr);

            } else {
                throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            messages.displayText(e.toString());
        }

    }

    /**
     * Adds a Todo Task into the List containing Tasks.
     * @param inputArr String array containing input by the user.
     * @throws DescriptionException if inputArr length < 2.
     */
    public void addTodo(String[] inputArr) throws DescriptionException {
        if (inputArr.length < 2) {
            throw new DescriptionException("todo");
        }

        String[] descriptionArray = Arrays.copyOfRange(inputArr, 1, inputArr.length);

        String description = String.join(" ", descriptionArray);

        Todo todoTask = new Todo(description);
        this.taskList.add(todoTask);
        messages.taskAddMessage(todoTask.toString(), this.taskList.size());
    }

    /**
     * Adds a Deadline Task into the List containing Tasks.
     * @param inputArr String array containing input by the user.
     * @throws DescriptionException if inputArr length < 2.
     * @throws CommandException if "/by" is absent from input.
     */
    public void addDeadline(String[] inputArr) throws DescriptionException, CommandException {
        if (inputArr.length < 2) {
            throw new DescriptionException("deadline");
        }

        boolean commandAbsent = true;
        int commandIndex = 1;
        for (int i = 0; i < inputArr.length; i++) {
            String currentStr = inputArr[i];
            if (currentStr.equals("/by")) {
                commandAbsent = false;
                commandIndex = i;
                break;
            }
        }
        if (commandAbsent) {
            throw new CommandException("deadline", "/by");
        }

        String[] descriptionArray = Arrays.copyOfRange(inputArr, 1, commandIndex);
        String description = String.join(" ", descriptionArray);

        try {
            if (commandIndex + 1 <= inputArr.length - 1) {
                String[] byArray = Arrays.copyOfRange(inputArr, commandIndex + 1, inputArr.length);

                if (byArray.length >= 3) {
                    throw new DukeException("Command after /by should at most only have 2 parts for date and time!");
                }

                /*
                System.out.println("byArray's Length: " + byArray.length);
                if (byArray.length == 2) {
                    //first input was a time
                    if (byArray[0].length() == 4) {
                        LocalTime time = LocalTime.parse(byArray[0]);
                        LocalDate date = LocalDate.parse(byArray[1]);
                        by =  LocalDateTime.of(date, time);
                    } else {
                        //first input is a date
                        LocalTime time = LocalTime.parse(byArray[1]);
                        LocalDate date = LocalDate.parse(byArray[0]);
                        by =  LocalDateTime.of(date, time);
                    }

                } else {
                    if (byArray[0].length() == 4) {
                        throw new DukeException("Input has to be a date and not time!");
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

                    }
                }
                */

                if (byArray.length == 1) {
                    LocalDate by = LocalDate.parse(byArray[0]);
                    Deadline deadlineTask = new Deadline(description, by);
                    this.taskList.add(deadlineTask);
                    messages.taskAddMessage(deadlineTask.toString(), this.taskList.size());
                }


            } else {
                throw new DukeException("Command after /by cannot be empty!");
            }
        } catch (DateTimeParseException e) {
            messages.wrongDateInputMessage();
        } catch (DukeException e) {
            messages.displayText(e.toString());
        }
    }

    /**
     * Adds an Event Task into the List containing Tasks.
     * @param inputArr String array containing input by the user.
     * @throws DescriptionException if inputArr length < 2.
     * @throws CommandException if "/at" is absent from input.
     */
    public void addEvent(String[] inputArr) throws DescriptionException, CommandException {
        if (inputArr.length < 2) {
            throw new DescriptionException("event");
        }

        boolean commandAbsent = true;
        int commandIndex = 1;
        for (int i = 0; i < inputArr.length; i++) {
            String currentStr = inputArr[i];
            if (currentStr.equals("/at")) {
                commandAbsent = false;
                commandIndex = i;
                break;
            }
        }
        if (commandAbsent) {
            throw new CommandException("event", "/at");
        }

        String[] descriptionArray = Arrays.copyOfRange(inputArr, 1, commandIndex);
        String description = String.join(" ", descriptionArray);

        String at;
        if (commandIndex + 1 <= inputArr.length - 1) {
            String[] atArray = Arrays.copyOfRange(inputArr, commandIndex + 1, inputArr.length);
            at = String.join(" ", atArray);
        } else {
            //TODO: might need to throw error here because this case is /by and nothing behind?
            at = "No data was inputted";
        }

        Event eventTask = new Event(description, at);
        this.taskList.add(eventTask);
        messages.taskAddMessage(eventTask.toString(), this.taskList.size());

    }

    /**
     * Marks a task in the taskList as done.
     * @param taskNumber task number to be marked as done.
     * @throws TaskNumberException if the number is < 0 or > taskList size.
     */
    public void markAsDone(Integer taskNumber) throws TaskNumberException {
        if (taskNumber > this.taskList.size() || taskNumber < 0) {
            throw new TaskNumberException();

        } else {
            //because our list starts from index 0 instead of index 1
            int realIndex = taskNumber - 1;
            this.taskList.get(realIndex).markAsDone();

            messages.markAsDoneMessage(this.taskList.get(realIndex).toString());
        }
    }

    /**
     * Deletes a task from the taskList.
     * @param taskNumber task number to be deleted.
     * @throws TaskNumberException if the number is < 0 or > taskList size.
     */
    public void deleteTask(Integer taskNumber) throws TaskNumberException {
        if (taskNumber > this.taskList.size() || taskNumber < 0) {
            throw new TaskNumberException();

        } else {
            //because our list starts from index 0 instead of index 1
            int realIndex = taskNumber - 1;
            String removedTask = this.taskList.get(realIndex).toString();
            this.taskList.remove(realIndex);
            messages.taskDeleteMessage(removedTask, this.taskList.size());
        }
    }

    /**
     * Saves the current task list to local memory's json.
     */
    public void saveData() {
        databaseEngine.writeToDatabase(this.taskList);
    }
}
