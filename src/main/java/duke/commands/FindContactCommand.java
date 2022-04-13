package main.java.duke.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import main.java.duke.MainWindow;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.tasks.Task;

/**
 * A command that looks for contacts with keyword given from the contact list.
 */
public class FindContactCommand extends ContactCommand {
    private final String keyword;

    public FindContactCommand(String keyword) {
        this.keyword = keyword;
    }

    public String execute(TaskList contacts, MainWindow gui, Storage storage) {
        return searchForContact(contacts);
    }

    private String searchForContact(TaskList contacts) {
        ArrayList<Task> taskList = contacts.getTaskList();
        Stream<Task> taskStream = taskList.stream();
        taskStream = taskStream.filter(contact -> contact.getName().contains(this.keyword));
        List<Task> tasksWithKeyword = taskStream.collect(Collectors.toList());
        StringBuilder message = new StringBuilder(("Here are the matching contacts in your list: \n"));
        for (Task contact : tasksWithKeyword) {
            message.append(contact.toString());
        }
        return message.toString();
    }
}
