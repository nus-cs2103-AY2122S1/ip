package duke.command;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Utils;

import java.time.LocalDate;

public class ListCommand extends Command {

    private static final String SORT_BY_DATE_ARGUMENT = "sortByDate";
    private static final String DESCENDING_ARGUMENT = "desc";

    public ListCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    public String execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            return "Currently no tasks!";
        }

        if (userArgument.equals("")) {
            return showTaskList(tasks);
        }

        String[] userArguments = userArgument.split(" ");

        assert userArguments.length > 0;

        boolean isSortedByDate = userArguments[0].equals(SORT_BY_DATE_ARGUMENT);

        boolean isDescending = false;

        boolean hasMoreThanOneArgument = userArguments.length > 1;

        if (hasMoreThanOneArgument) {
            isDescending = userArguments[1].equals(DESCENDING_ARGUMENT);
        }

        if (isSortedByDate) {
            return sortTaskListByDate(tasks, isDescending);
        }

        return showTaskList(tasks);
    }

    private String showTaskList(TaskList tasks) {
        return "Here are your tasks:\n" + Utils.showTasks(tasks);
    }

    private String sortTaskListByDate(TaskList tasks, boolean isDescending) {
        int descending = isDescending ? 1 : -1;

        return showTaskList(tasks.sortTasks((firstTask, secondTask) -> {
            LocalDate firstTaskDate = firstTask.getDate();
            LocalDate secondTaskDate = secondTask.getDate();

            if (firstTaskDate == null && secondTaskDate == null) {
                return 0;
            }

            if (firstTaskDate == null) {
                return 1;
            }

            if (secondTaskDate == null) {
                return -1;
            }

            return firstTaskDate.isBefore(secondTaskDate)
                    ? descending
                    : firstTaskDate.isAfter(secondTaskDate)
                        ? -descending
                        : 0;
        }));
    }
}
