package virushade;

import java.util.ArrayList;

import virushade.tasks.Task;

public class Sorter {
    /**
     * Sorts the given Task Array by task name.
     * @param tasks ArrayList to sort the task by.
     */
    public static void sortByName(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return;
        }

        ArrayList<Task> sortedTasks = new ArrayList<>();
        int numberOfTasks = tasks.size();
        assert numberOfTasks != 0;

        // Load in the first task before starting the sort.
        sortedTasks.add(tasks.get(0));
        for (int pointer = 1; pointer < numberOfTasks; pointer++) {
            insertTaskIntoPosition(tasks, sortedTasks, pointer);
        }

        // Replace the array with the sorted array.
        tasks.clear();
        tasks.addAll(sortedTasks);
    }

    private static void insertTaskIntoPosition(ArrayList<Task> tasks, ArrayList<Task> sortedTasks, int pointer) {
        // Note that pointer can be both the pointer to the index of the task to be inserted into sortedTask,
        // and the size of sortedTask before addition of the task to be inserted.
        Task taskToBeInserted = tasks.get(pointer);

        // Put task to be inserted into sortedTasks. This inserted task has index pointer.
        sortedTasks.add(taskToBeInserted);
        String insertedName = convertTaskToName(taskToBeInserted);

        for (int i = pointer - 1; i > -1; i--) {
            Task comparedTask = sortedTasks.get(i);
            String comparedName = convertTaskToName(comparedTask);
            int compareResult = StringManipulator.compareString(insertedName, comparedName);

            // If the insertedName is larger than compared Name, no need to bubble swap anymore.
            if (compareResult < 1) {
                break;
            } else {
                swapTask(sortedTasks, i, i + 1);
            }
        }
    }

    /**
     * Sorts the given Task Array by task type.
     * @param tasks ArrayList to sort the task by.
     */
    public static void sortByType(ArrayList<Task> tasks) throws VirushadeException {
        ArrayList<Task> deadlineTasks = new ArrayList<>();
        ArrayList<Task> todoTasks = new ArrayList<>();
        ArrayList<Task> eventTasks = new ArrayList<>();
        ArrayList<Task> resultTasks = new ArrayList<>();

        sortTasksByType(tasks, deadlineTasks, todoTasks, eventTasks);
        sortByName(deadlineTasks);
        sortByName(todoTasks);
        sortByName(eventTasks);

        resultTasks.addAll(todoTasks);
        resultTasks.addAll(deadlineTasks);
        resultTasks.addAll(eventTasks);

        tasks.clear();
        tasks.addAll(resultTasks);
    }

    private static void sortTasksByType(ArrayList<Task> tasks, ArrayList<Task> deadlineTasks,
            ArrayList<Task> todoTasks, ArrayList<Task> eventTasks) throws VirushadeException {
        for (int i = 0; i < tasks.size(); i++) {
            Task taskToBeInserted = tasks.get(i);
            char insertedType = convertTaskToType(taskToBeInserted);

            assert insertedType == 'D' || insertedType == 'E' || insertedType == 'T';
            switch (insertedType) {
            case 'T':
                todoTasks.add(taskToBeInserted);
                break;

            case 'D':
                deadlineTasks.add(taskToBeInserted);
                break;

            case 'E':
                eventTasks.add(taskToBeInserted);
                break;

            default:
                throw new VirushadeException("Some problem occurred when sorting!");
            }
        }
    }

    private static String convertTaskToName(Task task) {
        return task.toString().substring(7);
    }

    private static char convertTaskToType(Task task) {
        return task.toString().charAt(1);
    }

    private static void swapTask(ArrayList<Task> tasks, int firstIndex, int secondIndex) {
        Task temporaryPlaceholder = tasks.get(firstIndex);
        tasks.set(firstIndex, tasks.get(secondIndex));
        tasks.set(secondIndex, temporaryPlaceholder);
    }
}
