package duke;

import duke.classes.TaskList;
import duke.exceptions.DukeException;
import duke.tasks.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class DukeParser {
    //List of enum keywords
    enum Keywords {
        list,
        bye,
        todo,
        deadline,
        event,
        done,
        delete,
        error,
    }

    private TaskList taskList;
    private DukeUI ui;

    public DukeParser(TaskList taskList, DukeUI ui) {
        this.taskList = taskList;
        this.ui = ui;
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
     * Binary Function that processes String into separate Desc and Time
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

    public boolean parse(String str) throws DukeException {
        String[] words = str.split(" ");
        String desc = str;

        //DateTime variables
        DateTimeFormatter standard = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate time = LocalDate.now();

        //Switch variables
        Keywords key = Keywords.error;
        int index = 0;

        //Logic that looks for keywords
        if (str.equals("bye")) {
            key = Keywords.bye;
        } else if (str.equals("list")) {
            key = Keywords.list;
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
            } else if (temp.equals("todo")) {
                key = Keywords.todo;
                desc = str.replaceFirst("todo ", "");
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
            }
        } else {
            //Error Handling (Can be improved)
            if (str.equals("todo") || str.equals("deadline") || str.equals("event")) {
                throw new DukeException("!!! The description cannot be empty. !!!");
            }
        }

        switch (key) {
            case list:
                ui.printList(taskList);
                break;
            case todo:
                taskList.add(new ToDo(desc));
                ui.printTask(taskList);
                break;
            case deadline:
                taskList.add(new Deadline(desc, time));
                ui.printTask(taskList);
                break;
            case event:
                taskList.add(new Event(desc, time));
                ui.printTask(taskList);
                break;
            case done:
                if (index >= taskList.size()) {
                    throw new DukeException("!!! The number you input exceeds the size of the list !!!");
                }
                taskList.completeTask(index);
                ui.completeTask(taskList.get(index));
                break;
            case delete:
                if (index >= taskList.size()) {
                    throw new DukeException("!!! The number you input exceeds the size of the list !!!");
                }
                Task task = taskList.remove(index);
                ui.removeTask(task, taskList.size());
                break;
            case bye:
                return true;
            case error:
                throw new DukeException("!!! I'm sorry, but I don't know what that means. !!!");
        }
    return false;
    }
}
