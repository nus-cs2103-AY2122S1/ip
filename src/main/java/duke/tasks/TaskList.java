package duke.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.commands.SortCommand;
import duke.io.Parser;
import duke.util.Query;

/**
 * The {@code TaskList} contains and has methods for interacting with {@code Task}s.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes an instance of {@code TaskList} with an {@code ArrayList} containing {@code Task}s.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns an {@code ArrayList} of {@code Task}s in this {@code TaskList},
     *
     * @return {@code ArrayList} containing all tasks in this {@code TaskList}.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the number of {@code Task}s in this {@code TaskList},
     *
     * @return {@code int} - the number of tasks in this {@code TaskList}.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a single {@code Task} to this {@code TaskList}.
     *
     * @param t {@code Task} to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Adds a single {@code Task} to this {@code TaskList},
     * then forms a String with information about the {@code TaskList}.
     *
     * @param taskType {@code String}. One of: 'todo', 'event', 'deadline'.
     * @param t {@code Task} to be added.
     * @return {@code String} containing information about the {@code TaskList} after the operation.
     */
    public String addTaskAndAnnounce(String taskType, Task t) {
        String res = "";
        tasks.add(t);
        res += "I've added your " + taskType.toLowerCase() + ":\n\t\t" + t;
        res += "\n\t " + tasks.size() + " tasks in total.";
        return res;
    }

    /**
     * Marks a given {@code listOfTasks} as done,
     * then forms a String with information about the {@code TaskList}.
     *
     * @param listOfTasks {@code ArrayList<Integer>} representing the index of {@code Task}s to be marked as done.
     * @return {@code String} containing information about the {@code TaskList} after the operation.
     */
    public String markAsDoneAndAnnounce(ArrayList<Integer> listOfTasks) {
        StringBuilder res = new StringBuilder("Nice! I've marked these tasks as done: \n\t\t");

        for (int i = 0; i < listOfTasks.size(); i++) {
            // have to decrement by one since duke.tasks ArrayList is 0-indexed,
            // but the user-provided arguments uses a 1-indexed list
            int index = listOfTasks.get(i) - 1;
            tasks.get(index).markAsDone();
            res.append(tasks.get(index));
            if (i != listOfTasks.size() - 1) {
                // do not append newline to the last item
                res.append("\n\t\t");
            }
        }
        return res.toString();
    }

    /**
     * Deletes a given {@code listOfTasks},
     * then forms a String with information about the {@code TaskList}.
     *
     * @param listOfTasks {@code ArrayList<Integer>} representing the index of {@code Task}s to be deleted.
     * @return {@code String} containing information about the {@code TaskList} after the operation.
     */
    public String deleteTasksAndAnnounce(ArrayList<Integer> listOfTasks) {
        StringBuilder res = new StringBuilder("I've removed these tasks: \n\t\t");

        // reverse-sort the tasks and remove duplicates
        Set<Integer> s = new LinkedHashSet<>(listOfTasks);
        listOfTasks.clear();
        listOfTasks.addAll(s);
        Collections.sort(listOfTasks);
        Collections.reverse(listOfTasks);

        for (int i = 0; i < listOfTasks.size(); i++) {
            // have to decrement by one since tasks ArrayList is 0-indexed,
            // but the user-provided arguments uses a 1-indexed list
            int index = listOfTasks.get(i) - 1;

            res.append(tasks.get(index));
            tasks.remove(index);

            if (i != listOfTasks.size() - 1) {
                // do not append newline to the last item
                res.append("\n\t\t");
            }
        }
        res.append("\n\t ").append(tasks.size()).append(" tasks remain.");
        return res.toString();
    }

    /**
     * Returns a {@code String} containing the {@code Task}s that match the given
     * {@code String query}.
     *
     * @param userQueries {@code String} search query of the user.
     * @returns {@code String} - Newlined list of {@code Task}s.
     */
    public String findAndAnnounce(String userQueries) {
        ArrayList<Query> queries = Parser.parseFindQueries(userQueries);
        Stream<Task> matchedTasks = tasks.stream();

        String resultTasks = matchedTasks.filter(t -> queries.stream().allMatch(q -> q.isMatch(t)))
            .map(t -> t.toString()).collect(Collectors.joining("\n\t  "));

        if (resultTasks.length() == 0) {
            return "Your search didn't match any tasks.";
        } else {
            StringBuilder res = new StringBuilder("Your search matched these tasks:\n\t  ");
            res.append(resultTasks);
            return res.toString();
        }
    }

    /**
     * Checks if there is a {@code Task} in this {@code TaskList} which matches the provided description.
     * @param description {@code String} to check against
     * @return {@boolean} value
     */
    public boolean hasTaskWithDescription(String description) {
        return tasks.stream().anyMatch(t -> t.getDescription().equalsIgnoreCase(description));
    }

    /**
     * Sorts the {@code TaskList} according to a given {@sode SortOption}.
     *
     * @param option Sorting method to use.
     */
    public void sort(SortCommand.SortOptions option) {
        Comparator<Task> compareDescription = Comparator.comparing((Task t) -> t.description);
        Comparator<Task> compareType = Comparator.comparing(Task::getType);
        Comparator<Task> compareStatus = (Task t1, Task t2) -> Boolean.compare(t1.isDone, t2.isDone);
        Comparator<Task> compareTime = Comparator.comparing(Task::getTime);

        switch(option) {
        case LEXICOGRAPHIC_DESCENDING:
            tasks.sort(compareDescription.reversed());
            break;
        case TASK_TYPE:
            tasks.sort(compareType);
            break;
        case TASK_STATUS:
            tasks.sort(compareStatus);
            break;
        case TASK_STATUS_DESCENDING:
            tasks.sort(compareStatus.reversed());
            break;
        case TASK_TIME:
            tasks.sort(compareTime);
            break;
        case TASK_TIME_DESCENDING:
            tasks.sort(compareTime.reversed());
            break;
        default:
            tasks.sort(compareDescription);
            break;
        }
    }

    /**
     * Returns an indented, newlined, 1-indexed {@code String} of the
     * {@code Task}s contained in this {@code TaskList}.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("Here are your tasks:\n\t ");
        for (int i = 0; i < tasks.size(); i++) {
            res.append(i + 1).append(". ").append(tasks.get(i));
            if (i != tasks.size() - 1) {
                // do not append a newline to the last item
                res.append("\n\t ");
            }
        }
        return res.toString();
    }
}
