package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList {
    private List<Task> tasks;
    private List<Task> archives;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.archives = new ArrayList<>();
    }

    public TaskList(List<Task> tasks, List<Task> archives) {
        this.tasks = tasks;
        this.archives = archives;
    }

    @Override
    public String toString() {
        return "Yiyang-bot's duke.TaskList";
    }

    /**
     * Returns all the tasks in this instance.
     * @return the task list.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    public List<Task> getArchives() {
        return archives;
    }

    /**
     * Displays all the tasks in the list.
     */
    public ArrayList<String> displayList() {
        ArrayList<String> resp = new ArrayList<>();
        if (this.tasks.size() == 0) {
            resp.add("\tYou don't have any task now.");
            return resp;
        }

        resp.add("\tHere are the tasks in your list:");

        IntStream.range(0, tasks.size())
                .forEach(x -> {
                    resp.add(String.format("\t%d. %s", x+1, this.tasks.get(x)));
                });

        return resp;
    }

    /**
     * Marks one task in the list as done.
     * @param id the id of the task to be marked as done.
     * @throws IndexOutOfBoundsException the task id > task list length.
     */
    public ArrayList<String> markAsDone(int id) throws IndexOutOfBoundsException {
        ArrayList<String> resp = new ArrayList<>();

        if (id > this.tasks.size()) {
            throw new IndexOutOfBoundsException("Do not have such a task in the list.");
        }

        this.tasks.get(id-1).setDone(true);

        resp.add("\tNice! I've marked this task as done: ");
        resp.add("\t  " + this.tasks.get(id-1));

        return resp;
    }

    /**
     * Deletes one task in the list.
     * @param id the id of the task to be deleted.
     * @throws IndexOutOfBoundsException the task id > task list length.
     */
    public ArrayList<String> deleteTask(int id) throws IndexOutOfBoundsException {
        ArrayList<String> resp = new ArrayList<>();

        if (id > this.tasks.size()) {
            throw new IndexOutOfBoundsException("Do not have such a task in the list.");
        }

        Task currTask = this.tasks.remove(id-1);

        resp.add("\tNoted. I've removed this task:");
        resp.add("\t  " + currTask);
        resp.add(String.format("\tNow you have %d tasks in the list.", this.tasks.size()));

        return resp;
    }

    /**
     * Adds one task to the end of the list.
     * @param newItem the task to be added.
     */
    public ArrayList<String> addTask(Task newItem) {
        ArrayList<String> resp = new ArrayList<>();

        assert tasks != null : "The tasks List should be initialised before adding items";

        this.tasks.add(newItem);

        resp.add("\tGot it. I've added this task: ");
        resp.add("\t  " + newItem);
        resp.add(String.format("\tNow you have %d tasks in the list.", this.tasks.size()));

        return resp;
    }

    public ArrayList<String> displayFind(String target) {
        ArrayList<String> resp = new ArrayList<>();

        resp.add("\tHere are the matching tasks in your list:");

        IntStream.range(0, tasks.size())
                .filter(x -> tasks.get(x).isFound(target))
                .forEach(x -> {
                    int index = resp.size();
                    String tmp = "\t" + index + "." + tasks.get(x);
                    resp.add(tmp);
                });

        // find in the archive
        List<Task> foundInArchives = archives.stream().filter(x -> x.isFound(target)).collect(Collectors.toList());
        if (foundInArchives.size() > 0) {
            resp.add("\tHere are the matching tasks in your archive:");

            IntStream.range(0, foundInArchives.size())
                    .forEach(x -> {
                        String tmp = "\t" + (x+1) + "." + foundInArchives.get(x);
                        resp.add(tmp);
                    });
        }

        return resp;
    }

    public ArrayList<String> archiveTask(int id) throws IndexOutOfBoundsException {
        ArrayList<String> resp = new ArrayList<>();

        if (id > this.tasks.size()) {
            throw new IndexOutOfBoundsException("Do not have such a task in the list.");
        }

        Task currTask = this.tasks.remove(id-1);
        this.archives.add(currTask);

        resp.add("\tNoted. I've archived this task:");
        resp.add("\t  " + currTask);
        resp.add(String.format("\tNow you have %d tasks in the list.", this.tasks.size()));

        return resp;
    }

    public ArrayList<String> unarchiveTask(int id) throws IndexOutOfBoundsException {
        ArrayList<String> resp = new ArrayList<>();

        if (id > this.archives.size()) {
            throw new IndexOutOfBoundsException("Do not have such a task in the archive.");
        }

        Task currTask = this.archives.remove(id-1);
        this.tasks.add(currTask);

        resp.add("\tNoted. I've get back this task from archive:");
        resp.add("\t  " + currTask);
        resp.add(String.format("\tNow you have %d tasks in the list.", this.tasks.size()));

        return resp;
    }

    /**
     * Displays all the tasks in the archive.
     */
    public ArrayList<String> displayArchive() {
        ArrayList<String> resp = new ArrayList<>();
        if (this.archives.size() == 0) {
            resp.add("\tYou don't have any task in archive now.");
            return resp;
        }

        resp.add("\tHere are the tasks you have archived:");

        IntStream.range(0, archives.size())
                .forEach(x -> {
                    resp.add(String.format("\t%d. %s", x+1, this.archives.get(x)));
                });

        return resp;
    }


}
