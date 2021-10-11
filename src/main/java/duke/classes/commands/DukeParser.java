package duke.classes.commands;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.classes.TaskList;
import duke.classes.tasks.Deadline;
import duke.classes.tasks.Event;
import duke.classes.tasks.Task;
import duke.classes.tasks.ToDo;
import duke.classes.exceptions.DukeException;


/**
 * Parser class handling the logic of the program
 */
public class DukeParser {
    //List of enum keywords
    enum Keywords {
        bye("bye ; exits the program"),
        deadline("deadline [task] /by [date] ; Creates a deadline task"),
        delete("delete [#] ; deletes task [#] in the list"),
        done("done [#] ; marks task [#] as done"),
        event("event [task] /at [date] ; Creates a event task"),
        find("find [keyword] ; looks for tasks containing [keyword]"),
        help("help ; you've used this already"),
        list("list ; lists the tasks in the list currently"),
        todo("todo [task] ; Creates a todo task"),
        error("");

        private final String helpText;
        Keywords(String helpText) {
            this.helpText = helpText;
        }

        public String getHelpText() {
            return this.helpText;
        }
    }

    private TaskList taskList;

    /**
     * Class Constructor
     *
     * @param taskList The tasklist to be modified through user-input
     */
    public DukeParser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Checks if String is an int
     *
     * @param str Target string to check
     */
    static boolean checkForInt(String str) {
        try {
            parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Binary Function that processes String into separate Desc and Time.
     * The Desc and Time will be separated into 2 strings that will be returned in a list
     *
     * @param divider The pivot string to divide the input (\by or \at)
     * @param input   Array of Strings containing individual words that was input
     * @return Returns a list of 2 Strings,
     * index 0 contains description,
     * index 1 contains the time of the event or the deadline
     */
    static List<String> processDesc(String divider, String[] input) {
        List<String> list = Arrays.asList(input);
        int index = list.indexOf(divider);
        String desc = "";
        String time = "";
        for (int i = 1; i < index; i++) {
            desc += input[i] + " ";
        }
        for (int i = index + 1; i < input.length; i++) {
            time += input[i] + " ";
        }
        List<String> temp = new ArrayList<>();
        temp.add(desc.trim());
        temp.add(time.trim());
        return temp;
    }

    /**
     * Main Parser function. Contains the logic to read and resolve user input,
     * then directs Duke to the appropriate response in the backend and frontend
     *
     * @param str Input entered by the user
     * @return Boolean to indicate the closing of the programme
     *         as a direct result of the "bye" command (True if hasQuit)
     * @throws DukeException Exception thrown when any issues are encountered
     */
    public String parse(String str) throws DukeException {
        String[] words = str.split(" ");
        assert str != null : "empty string";
        String desc = str;

        //DateTime variables
        DateTimeFormatter standard = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate time = LocalDate.now();

        //Filter
        List<Task> filtered = null;

        //Switch variables
        Keywords key = Keywords.error;
        int index = 0;

        //Logic that looks for keywords
        if (str.equals("bye")) {
            key = Keywords.bye;
        } else if (str.equals("list")) {
            key = Keywords.list;
        } else if (str.equals("help")) {
            key = Keywords.help;
        } else if (words.length > 1) {
            String temp = words[0];
            if (temp.equals("done")) {
                if (checkForInt(words[1])) {
                    index = parseInt(words[1]) - 1;
                    if (index < 0) {
                        throw new DukeException("!!! Please input a number greater than 0 !!!");
                    }
                    key = Keywords.done;
                }
            } else if (temp.equals("delete")) {
                if (checkForInt(words[1])) {
                    index = parseInt(words[1]) - 1;
                    if (index < 0) {
                        throw new DukeException("!!! Please input a number greater than 0 !!!");
                    }
                    key = Keywords.delete;
                }
            } else if (temp.equals("find")) {
                key = Keywords.find;
            } else if (temp.equals("todo")) {
                key = Keywords.todo;
            } else if (temp.equals("deadline")) {
                key = Keywords.deadline;
                List<String> postFilter = processDesc("/by", words);
                desc = postFilter.get(0);
                if (postFilter.get(1).equals("")) {
                    throw new DukeException("!!! The date of a deadline cannot be empty. Use /by to input date!!!");
                } else if (desc.equals("")) {
                    throw new DukeException("!!! Input the description then use /by to input date !!!");
                }
                time = LocalDate.parse(postFilter.get(1), standard);
            } else if (temp.equals("event")) {
                key = Keywords.event;
                List<String> postFilter = processDesc("/at", words);
                desc = postFilter.get(0);
                if (postFilter.get(1).equals("")) {
                    throw new DukeException("!!! The date of a event cannot be empty. Use /at to input date !!!");
                } else if (desc.equals("")) {
                    throw new DukeException("!!! Input the description then use /at to input date !!!");
                }
                time = LocalDate.parse(postFilter.get(1), standard);
            } else if (temp.equals("help")) {
                key = Keywords.help;
            }
        } else {
            if (str.equals("todo") || str.equals("deadline") || str.equals("event")) {
                throw new DukeException("!!! The description cannot be empty. !!!");
            }
        }

        assert key != Keywords.error : "Invalid Key";

        switch (key) {
        case list:
            return DukeUI.printList(taskList);
        case help:
            String temp = null;
            if (words.length > 1) {
                temp = words[1];
            }
            return DukeUI.printHelp(temp);
        case todo:
            desc = str.replaceFirst("todo ", "");
            taskList.add(new ToDo(desc));
            return DukeUI.printTask(taskList);
        case deadline:
            taskList.add(new Deadline(desc, time));
            return DukeUI.printTask(taskList);
        case event:
            taskList.add(new Event(desc, time));
            return DukeUI.printTask(taskList);
        case done:
            if (index >= taskList.size()) {
                throw new DukeException("!!! The number you input exceeds the size of the list !!!");
            }
            taskList.completeTask(index);
            return DukeUI.completeTask(taskList.get(index));
        case delete:
            if (index >= taskList.size()) {
                throw new DukeException("!!! The number you input exceeds the size of the list !!!");
            }
            Task task = taskList.remove(index);
            return DukeUI.removeTask(task, taskList.size());
        case find:
            filtered = taskList.filter(str.replaceFirst("find", "").trim());
            return DukeUI.printFiltered(filtered);
        case bye:
            return "Good Bye";
        case error:
            throw new DukeException("!!! I'm sorry, but I don't know what that means. !!!");
        default:
            throw new IllegalStateException("Unexpected value: " + key);
        }
    }
}
