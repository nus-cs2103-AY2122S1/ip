package seedu.duke.timetable;

import java.util.ArrayList;
import java.util.Comparator;

import seedu.duke.commands.Ui;
import seedu.duke.tasks.ScheduledTask;

/**
 * Class which saves and manages all the {@code ScheduledTask}. Makes sure that
 * no {@code ScheduledTask} clashes one another.
 */
public class DayPlan {
    private final ArrayList<ScheduledTask> scheduledTasks;

    /**
     * Class Primary Constructor.
     */
    public DayPlan() {
        this.scheduledTasks = new ArrayList<>();
    }

    /**
     * Class Secondary Constructor with the {@code ArrayList<ScheduledTask>}
     * initialised.
     * 
     * @param scheduledTasks is an {@code ArrayList<ScheduledTask>} which will be
     *                       use to initialise this {@code DayPlan}.
     */
    public DayPlan(ArrayList<ScheduledTask> scheduledTasks) {
        this.scheduledTasks = scheduledTasks;
    }

    /**
     * Checks if the current {@code ScheduledTask} clashes with any
     * {@code ScheduledTask} which are already added in the {@code DayPlan}.
     * 
     * @param currTask is the {@code ScheduledTask} which will be checked if clash
     *                 happens.
     * @return boolean, true if there is a clash in {@code Timetable}.
     */
    public boolean isClash(ScheduledTask currTask) {
        int currTimeFrom = currTask.getTimeFrom();
        int currTimeTo = currTask.getTimeTo();

        ArrayList<ScheduledTask> clashList = new ArrayList<ScheduledTask>();

        this.scheduledTasks.stream()
                .filter(task -> (currTimeFrom > task.getTimeFrom() && currTimeFrom < task.getTimeTo())
                        || (currTimeTo > task.getTimeFrom() && currTimeTo < task.getTimeTo())
                        || (task.getTimeFrom() == currTimeFrom) || (task.getTimeTo() == currTimeTo))
                .forEach(task -> clashList.add(task));
        return clashList.size() != 0;
    }

    /**
     * Adds the current {@code ScheduledTask} into the {@code DayPlan}.
     * 
     * @param currTask the {@code ScheduledTask} which will be added into this
     *                 {@code DayPlan}.
     * @return a message from {@code Duke} stating if the {@code ScheduledTask} is
     *         added into this {@code DayPlan}.
     */
    public String addSchedule(ScheduledTask currTask) {
        if (this.isClash(currTask)) {
            return "Timetable clash, unable to add " + currTask.toString();
        }
        scheduledTasks.add(currTask);
        return currTask.toString() + " is added into the timetable";
    }

    /**
     * Deletes the current {@code ScheduledTask} from the {@code DayPlan}.
     * 
     * @param currTask the {@code ScheduledTask} which will be deleted from this
     *                 {@code DayPlan}.
     * @return a message from {@code Duke} stating if the {@code ScheduledTask} is
     *         deleted from this {@code DayPlan}.
     */
    public String removeSchedule(ScheduledTask currTask) {
        int currTimeFrom = currTask.getTimeFrom();
        int currTimeTo = currTask.getTimeTo();

        boolean isRemoved = scheduledTasks
                .removeIf(task -> (task.getTimeFrom() == currTimeFrom && task.getTimeTo() == currTimeTo));
        return isRemoved ? currTask.toString() + " is removed from timetable."
                : currTask.toString() + " is not found in timetable.";
    }

    /**
     * Views all the {@code ScheduledTask} which are already added into this
     * {@code DayPlan}.
     * 
     * @return all the {@code ScheduledTask} which are already in this
     *         {@code DayPlan}.
     */
    public String viewDayPlan() {
        if (this.scheduledTasks.size() == 0) {
            return Ui.DAYPLAN_NO_SCHEDULED_TASKS;
        }
        this.scheduledTasks.sort(getComparatorEarlyToLate());
        String dayPlans = "";
        for (int i = 0; i < this.scheduledTasks.size(); i++) {
            dayPlans += this.scheduledTasks.get(i).toString() + "\n";
        }
        return dayPlans;
    }

    /**
     * Edits the already added {@code ScheduledTask} in this {@code DayPlan}.
     * 
     * @param from is the {@code ScheeduledTask} which require amendments.
     * @param to   is the {@code ScheduledTask} after amendments.
     * @return a message from {@code Duke} stating if the {@code ScheduledTask} has
     *         successfully changed.
     */
    public String moveSchedule(ScheduledTask from, ScheduledTask to) {
        DayPlan copyScheduledTasks = new DayPlan(new ArrayList<>(this.scheduledTasks));
        copyScheduledTasks.removeSchedule(from);

        if (copyScheduledTasks.isClash(to)) {
            return Ui.DAYPLAN_EDIT_FAILED;
        }
        this.removeSchedule(from);
        this.addSchedule(to);
        return Ui.DAYPLAN_EDIT_SUCCESSFULLY;
    }

    /**
     * Marks the given {@code ScheduledTask} as done.
     * 
     * @param currTask is the {@code ScheduledTask} which will be marked as done.
     */
    public void markeDone(ScheduledTask currTask) {
        int index = isExist(currTask);
        if (index < 0) {
            return;
        }
        this.scheduledTasks.set(index, currTask.markAsDone());
    }

    /**
     * Checks if the give {@code ScheduledTask} exists in this {@code DayPlan}.
     * 
     * @param currTask is the {@code ScheduledTask} which will be searching.
     * @return the index position of the {@code ScheduledTask} if exists, else
     *         return -1.
     */
    public int isExist(ScheduledTask currTask) {
        int currTimeFrom = currTask.getTimeFrom();
        int currTimeTo = currTask.getTimeTo();
        String description = currTask.getDescription();

        for (int i = 0; i < this.scheduledTasks.size(); i++) {
            ScheduledTask pointerTask = this.scheduledTasks.get(i);
            if (isSameTask(pointerTask, currTimeFrom, currTimeTo, description)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if there is any {@code ScheduledTask} in this {@code DayPlan}.
     * 
     * @return true is there is a {@code ScheduledTask} in this {@code DayPlan}.
     */
    public boolean hasSchedule() {
        return this.scheduledTasks.size() != 0;
    }

    private boolean isSameTask(ScheduledTask pointerTask, int currTimeFrom, int currTimeTo, String description) {
        int pointerFrom = pointerTask.getTimeFrom();
        int pointerTo = pointerTask.getTimeTo();
        String pointerDescription = pointerTask.getDescription();
        return (pointerFrom == currTimeFrom && currTimeTo == pointerTo && description.equals(pointerDescription));
    }

    private Comparator<ScheduledTask> getComparatorEarlyToLate() {
        return new Comparator<ScheduledTask>() {
            @Override
            public int compare(ScheduledTask firstTask, ScheduledTask secondTask) {
                return (firstTask.getTimeFrom() > secondTask.getTimeFrom()) ? 1 : -1;
            }
        };
    }

}
