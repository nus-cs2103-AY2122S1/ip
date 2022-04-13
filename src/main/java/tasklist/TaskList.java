package tasklist;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import exception.CommandException;
import exception.DescriptionException;
import exception.DukeException;
import exception.InvalidCommandException;
import exception.TaskNumberException;
import parser.Parser;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.Ui;

/**
 * The TaskList class provides the functionality of editing the tasks list.
 */
public class TaskList {
    public static final int DESCRIPTION_START = 1;
    public static final int INVALID = -1;
    public static final int MINIMUM_DESCRIPTION = 2;
    public static final int COMMAND_INDEX = 0;


    private final Ui messages = new Ui();
    private final Storage databaseEngine = new Storage();

    private List<Task> taskList = new ArrayList<Task>();

    /**
     * Runs the program.
     */
    public void runProgram() {
        this.loadData();

        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {
            this.saveData();

            //removes additional space from the input
            input = sc.nextLine().trim();

            //removes space in the command input and stores the strings in an array
            String[] inputArr = Parser.sanitizeInput(input);
            String firstCommand = inputArr[COMMAND_INDEX];

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                messages.displayListTasks(this.taskList);
            } else if (firstCommand.equals("done")) {
                //obtains the task number which we want to mark as done
                this.markAsDone(inputArr[1]);
            } else if (firstCommand.equals("delete")) {
                //obtains the task number which we want to delete, Could throw error here if input is not Integer
                this.deleteTask(inputArr[1]);
            } else if (firstCommand.equals("find")) {
                this.findTasks(inputArr);
            } else {
                //adds task
                this.addTask(inputArr);
            }
        }
        sc.close();
    }

    /**
     * Runs the GUI program.
     */
    public String readGuiInput(String input) {
        this.loadData();
        String responseMessage;

        //removes space in the command input and stores the strings in an array
        String[] inputArr = Parser.sanitizeInput(input);

        if (input.equals("bye")) {
            responseMessage = messages.goodbyeMessageGui();
        } else if (input.equals("list")) {
            responseMessage = messages.displayListTasksGui(this.taskList);
        } else if (inputArr[COMMAND_INDEX].equals("done")) {
            responseMessage = this.markAsDoneGui(inputArr[1]);
        } else if (inputArr[COMMAND_INDEX].equals("delete")) {
            responseMessage = this.deleteTaskGui(inputArr[1]);

        } else if (inputArr[COMMAND_INDEX].equals("find")) {
            responseMessage = this.findTasksGui(inputArr);
        } else {
            //adds item input by the user into the inputList
            responseMessage = this.addTaskGui(inputArr);
        }

        //saves the latest copy of the task list
        this.saveData();
        return responseMessage;
    }

    /**
     * Loads data from local storage.
     */
    public void loadData() {
        databaseEngine.createDirectory();
        databaseEngine.createDatabase();
        this.taskList = databaseEngine.readFromDatabase();
    }

    /**
     * Adds a TooDo Task into the List containing Tasks.
     *
     * @param inputArr String array containing input by the user.
     * @throws InvalidCommandException if inputArr length less than 2.
     */
    public void addTask(String[] inputArr) {
        try {
            if (inputArr[COMMAND_INDEX].equals("todo")) {
                this.addTodo(inputArr);

            } else if (inputArr[COMMAND_INDEX].equals("deadline")) {
                this.addDeadline(inputArr);

            } else if (inputArr[COMMAND_INDEX].equals("event")) {
                this.addEvent(inputArr);

            } else {
                throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            messages.displayText(e.toString());
        }
    }

    /**
     * Adds a TooDo Task into the List containing Tasks.
     *
     * @param inputArr String array containing input by the user.
     * @return reply to be displayed on GUI.
     * @throws InvalidCommandException if inputArr length less than 2.
     */
    public String addTaskGui(String[] inputArr) {
        try {
            if (inputArr[COMMAND_INDEX].equals("todo")) {
                return this.addTodoGui(inputArr);

            } else if (inputArr[COMMAND_INDEX].equals("deadline")) {
                return this.addDeadlineGui(inputArr);

            } else if (inputArr[COMMAND_INDEX].equals("event")) {
                return this.addEventGui(inputArr);

            } else {
                throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            return messages.displayTextGui(e.toString());
        }
    }

    /**
     * Adds a Todo Task into the List containing Tasks.
     *
     * @param inputArr String array containing input by the user.
     * @throws DescriptionException if inputArr length less than 2.
     */
    public void addTodo(String[] inputArr) throws DescriptionException {
        if (this.descriptionInvalid(inputArr)) {
            throw new DescriptionException("todo");
        }

        // -1 indicates there are no tags in the command
        int tagStart = Parser.getTagsStart(inputArr);

        String[] descriptionArray;
        if (tagStart == INVALID) {
            descriptionArray = Arrays.copyOfRange(inputArr, DESCRIPTION_START, inputArr.length);
        } else {
            descriptionArray = Arrays.copyOfRange(inputArr, DESCRIPTION_START, tagStart);
        }
        String description = String.join(" ", descriptionArray);
        Todo todoTask = new Todo(description);

        if (tagStart != INVALID) {
            for (int j = tagStart; j < inputArr.length; j++) {
                Parser.addTag(todoTask, inputArr[j]);
            }
        }

        this.taskList.add(todoTask);
        messages.taskAddMessage(todoTask.toString(), this.taskList.size());
    }

    /**
     * Adds a Todo Task into the List containing Tasks.
     *
     * @param inputArr String array containing input by the user.
     * @return reply to be displayed on GUI.
     * @throws DescriptionException if inputArr length less than 2.
     */
    public String addTodoGui(String[] inputArr) throws DescriptionException {
        if (this.descriptionInvalid(inputArr)) {
            throw new DescriptionException("todo");
        }

        // -1 indicates there are no tags in the command
        int tagStart = Parser.getTagsStart(inputArr);

        String[] descriptionArray;
        if (tagStart == INVALID) {
            descriptionArray = Arrays.copyOfRange(inputArr, DESCRIPTION_START, inputArr.length);
        } else {
            descriptionArray = Arrays.copyOfRange(inputArr, DESCRIPTION_START, tagStart);
        }
        String description = String.join(" ", descriptionArray);
        Todo todoTask = new Todo(description);

        if (tagStart != INVALID) {
            for (int j = tagStart; j < inputArr.length; j++) {
                Parser.addTag(todoTask, inputArr[j]);
            }
        }

        this.taskList.add(todoTask);
        return messages.taskAddMessageGui(todoTask.toString(), this.taskList.size());
    }

    /**
     * Adds a Deadline Task into the List containing Tasks.
     *
     * @param inputArr String array containing input by the user.
     * @throws DescriptionException if inputArr length less than 2.
     * @throws CommandException if "/by" is absent from input.
     */
    public void addDeadline(String[] inputArr) throws DescriptionException, CommandException {
        if (this.descriptionInvalid(inputArr)) {
            throw new DescriptionException("deadline");
        }

        int commandIndex = Parser.getCommandIndex(inputArr, "/by");
        if (commandIndex == INVALID) {
            throw new CommandException("deadline", "/by");
        } else if (commandIndex == DESCRIPTION_START) {
            throw new DescriptionException("deadline");
        }
        int tagStart = Parser.getTagsStart(inputArr);
        String description = Parser.getDescription(inputArr, commandIndex);

        try {
            LocalDate by = LocalDate.parse(inputArr[commandIndex + 1]);
            Deadline deadlineTask = new Deadline(description, by);
            Parser.addTags(deadlineTask, inputArr, tagStart);
            this.taskList.add(deadlineTask);
            messages.taskAddMessage(deadlineTask.toString(), this.taskList.size());
        } catch (DateTimeParseException e) {
            messages.wrongDateInputMessage();
        } catch (Exception e) {
            messages.displayText("Need to have a yyyy-mm-dd after /by and # should not come before yyyy-mm-dd");
        }
    }

    /**
     * Adds a Deadline Task into the List containing Tasks.
     *
     * @param inputArr String array containing input by the user.
     * @return reply to be displayed on GUI.
     * @throws DescriptionException if inputArr length less than 2.
     * @throws CommandException if "/by" is absent from input.
     */
    public String addDeadlineGui(String[] inputArr) throws DescriptionException, CommandException {
        if (this.descriptionInvalid(inputArr)) {
            throw new DescriptionException("deadline");
        }

        int commandIndex = Parser.getCommandIndex(inputArr, "/by");
        if (commandIndex == INVALID) {
            throw new CommandException("deadline", "/by");
        } else if (commandIndex == DESCRIPTION_START) {
            throw new DescriptionException("deadline");
        }
        int tagStart = Parser.getTagsStart(inputArr);
        String description = Parser.getDescription(inputArr, commandIndex);

        try {
            LocalDate by = LocalDate.parse(inputArr[commandIndex + 1]);
            Deadline deadlineTask = new Deadline(description, by);
            Parser.addTags(deadlineTask, inputArr, tagStart);
            this.taskList.add(deadlineTask);
            return messages.taskAddMessageGui(deadlineTask.toString(),
                    this.taskList.size());
        } catch (DateTimeParseException e) {
            return messages.wrongDateInputMessageGui();
        } catch (Exception e) {
            return messages.displayTextGui("Need to have a yyyy-mm-dd after /by and # "
                    + "should not come before yyyy-mm-dd");
        }
    }

    /**
     * Adds an Event Task into the List containing Tasks.
     *
     * @param inputArr String array containing input by the user.
     * @throws DescriptionException if inputArr length less than 2.
     * @throws CommandException if "/at" is absent from input.
     */
    public void addEvent(String[] inputArr) throws DescriptionException, CommandException {
        if (this.descriptionInvalid(inputArr)) {
            throw new DescriptionException("event");
        }

        int commandIndex = Parser.getCommandIndex(inputArr, "/at");
        if (commandIndex == INVALID) {
            throw new CommandException("event", "/at");
        } else if (commandIndex == DESCRIPTION_START) {
            throw new DescriptionException("event");
        }

        int tagStart = Parser.getTagsStart(inputArr);
        String description = Parser.getDescription(inputArr, commandIndex);

        try {
            String at;
            if ((tagStart > commandIndex) && (commandIndex + 1 != inputArr.length)) {
                String[] atArray = Arrays.copyOfRange(inputArr, commandIndex + 1, tagStart);
                at = String.join(" ", atArray);
            } else if (tagStart == INVALID && (commandIndex + 1 != inputArr.length)) {
                String[] atArray = Arrays.copyOfRange(inputArr, commandIndex + 1, inputArr.length);
                at = String.join(" ", atArray);
            } else if ((tagStart < commandIndex) && (commandIndex + 1 != inputArr.length)) {
                throw new DukeException("Tags must be after your /at location!");
            } else {
                throw new DukeException("NO LOCATION DATA INPUT");
            }
            Event eventTask = new Event(description, at);
            if (tagStart > commandIndex) {
                Parser.addTags(eventTask, inputArr, tagStart);
            }
            this.taskList.add(eventTask);
            messages.taskAddMessage(eventTask.toString(), this.taskList.size());
        } catch (DukeException e) {
            messages.displayText(e.toString());
        }
    }

    /**
     * Adds an Event Task into the List containing Tasks.
     *
     * @param inputArr String array containing input by the user.
     * @return reply to be displayed on GUI.
     * @throws DescriptionException if inputArr length less than 2.
     * @throws CommandException if "/at" is absent from input.
     */
    public String addEventGui(String[] inputArr) throws DescriptionException, CommandException {

        if (this.descriptionInvalid(inputArr)) {
            throw new DescriptionException("event");
        }

        int commandIndex = Parser.getCommandIndex(inputArr, "/at");
        if (commandIndex == INVALID) {
            throw new CommandException("event", "/at");
        } else if (commandIndex == DESCRIPTION_START) {
            throw new DescriptionException("event");
        }

        int tagStart = Parser.getTagsStart(inputArr);
        String description = Parser.getDescription(inputArr, commandIndex);

        try {
            String at;
            if ((tagStart > commandIndex) && (commandIndex + 1 != inputArr.length)) {
                String[] atArray = Arrays.copyOfRange(inputArr, commandIndex + 1, tagStart);
                at = String.join(" ", atArray);
            } else if (tagStart == INVALID && (commandIndex + 1 != inputArr.length)) {
                String[] atArray = Arrays.copyOfRange(inputArr, commandIndex + 1, inputArr.length);
                at = String.join(" ", atArray);
            } else if ((tagStart < commandIndex) && (commandIndex + 1 != inputArr.length)) {
                System.out.println(commandIndex);
                System.out.println(inputArr.length);
                throw new DukeException("Tags must be after your /at location!");
            } else {
                throw new DukeException("NO LOCATION DATA INPUT");
            }
            Event eventTask = new Event(description, at);
            if (tagStart > commandIndex) {
                Parser.addTags(eventTask, inputArr, tagStart);
            }
            this.taskList.add(eventTask);
            return messages.taskAddMessageGui(eventTask.toString(), this.taskList.size());
        } catch (DukeException e) {
            return messages.displayTextGui(e.toString());
        }
    }

    /**
     * Marks a task in the taskList as done.
     *
     * @param taskString String task to be marked as done.
     * @throws TaskNumberException if the number is less than 0 or more than taskList size.
     */
    public void markAsDone(String taskString) {
        try {
            Integer taskNumber = Integer.parseInt(taskString);

            if (taskNumber > this.taskList.size() || taskNumber < 0) {
                messages.displayText(new TaskNumberException().toString());
                return;
            }

            //because our list starts from index 0 instead of index 1
            int realIndex = taskNumber - 1;
            this.taskList.get(realIndex).markDone();

            messages.markDoneMessage(this.taskList.get(realIndex).toString());
        } catch (Exception e) {
            messages.displayText("Input must be an Integer!");
        }
    }

    /**
     * Marks a task in the taskList as done.
     *
     * @param taskString task number to be marked as done.
     * @return reply to be displayed on GUI.
     */
    public String markAsDoneGui(String taskString) {
        try {
            Integer taskNumber = Integer.parseInt(taskString);
            if (taskNumber > this.taskList.size() || taskNumber < 0) {
                return new TaskNumberException().toString();
            }

            //because our list starts from index 0 instead of index 1
            int realIndex = taskNumber - 1;
            this.taskList.get(realIndex).markDone();

            return messages.markDoneMessageGui(this.taskList.get(realIndex).toString());
        } catch (Exception e) {
            return "Input must be an Integer!";
        }
    }

    /**
     * Deletes a task from the taskList.
     *
     * @param numberString string of task number to be deleted.
     */
    public void deleteTask(String numberString) {
        try {
            Integer taskNumber = Integer.parseInt(numberString);
            if (taskNumber > this.taskList.size() || taskNumber < 0) {
                messages.displayText(new TaskNumberException().toString());
                return;
            }

            //because our list starts from index 0 instead of index 1
            int realIndex = taskNumber - 1;
            String removedTask = this.taskList.get(realIndex).toString();
            this.taskList.remove(realIndex);
            messages.taskDeleteMessage(removedTask, this.taskList.size());
        } catch (Exception e) {
            messages.displayText("Input must be an Integer!");
        }
    }

    /**
     * Deletes a task from the taskList.
     *
     * @param taskString String number to be deleted.
     * @return reply to be displayed on GUI.
     */
    public String deleteTaskGui(String taskString) {
        try {
            Integer taskNumber = Integer.parseInt(taskString);
            if (taskNumber > this.taskList.size() || taskNumber < 0) {
                return new TaskNumberException().toString();
            }

            //because our list starts from index 0 instead of index 1
            int realIndex = taskNumber - 1;
            String removedTask = this.taskList.get(realIndex).toString();
            this.taskList.remove(realIndex);
            return messages.taskDeleteMessageGui(removedTask, this.taskList.size());
        } catch (Exception e) {
            return "Input must be an Integer!";
        }

    }

    /**
     * Saves the current task list to local memory's json.
     */
    public void saveData() {
        databaseEngine.writeToDatabase(this.taskList);
    }

    /**
     * Displays the tasks that have the keywords inputted.
     *
     * @param inputArr the array containing the keywords.
     */
    public void findTasks(String[] inputArr) {
        List<Task> filteredList = this.taskList
                .stream()
                .filter(task -> {
                    String description = task.getDescription();
                    for (String s: inputArr) {
                        if (description.contains(s)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());

        messages.displayFilteredTasks(filteredList);
    }

    /**
     * Displays the tasks that have the keywords inputted.
     *
     * @param inputArr the array containing the keywords.
     * @return reply to be displayed on GUI.
     */
    public String findTasksGui(String[] inputArr) {
        assert(inputArr.length != 0);

        List<Task> filteredList = this.taskList
                .stream()
                .filter(task -> {
                    String description = task.getDescription();
                    for (String s: inputArr) {
                        if (description.contains(s)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());

        return messages.displayFilteredTasksGui(filteredList);
    }

    /**
     * Checks if a description input is invalid.
     *
     * @param inputArr array containing user input.
     * @return boolean indicating if the input is valid or not.
     */
    public boolean descriptionInvalid(String[] inputArr) {
        return inputArr.length < MINIMUM_DESCRIPTION;
    }

}
