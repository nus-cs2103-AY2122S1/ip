package duke.command;


import duke.exception.DukeException;
import duke.taskTypes.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class FindCommand extends Command {

    private final String key;

    public FindCommand(Storage storage, TaskList taskList, Ui ui, String key) {
        super(storage, taskList, ui);
        this.key = key;
    }

    @Override
    public boolean exec() throws DukeException {

        String[] keyFoundArr = taskList.find(key);

        if (keyFoundArr.length == 0){
            ui.listMsg(new String[]{"None Found"}, taskList);
        } else {
            ui.listMsg(keyFoundArr, taskList);
        }

        return true;
    }
}
