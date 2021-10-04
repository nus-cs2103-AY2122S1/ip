package seedu.duke.timetable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import seedu.duke.commands.Ui;
import seedu.duke.tasks.ScheduledTask;
import seedu.duke.tasks.Task;

/**
 * A Class which helps to save all the user's daily schedule.
 */
public class Timetable {
    private final HashMap<String, DayPlan> timetable;

    /**
     * Class primary constructor.
     */
    public Timetable() {
        this.timetable = new HashMap<String, DayPlan>();
    }

    /**
     * Initialises all saved data into the application from previous saves.
     * 
     * @param savedTasks is the {@code ArrayList<Task>} which previously saved in
     *                   the application.
     */
    public void initialise(ArrayList<Task> savedTasks) {
        ArrayList<Task> savedTasksList = new ArrayList<Task>((savedTasks));
        savedTasksList.removeIf(task -> !task.getSymbol().equals("ST"));
        savedTasksList.forEach(task -> {
            this.addPlanToDay((ScheduledTask) task);
        });
    }

    /**
     * Adds the current {@code ScheduledTask} into this {@code Timetable}.
     * 
     * @param currTask is the {@code ScheduledTask} which will be added into the
     *                 {@code Timetable}.
     * @return a message from {@code Duke} stating if the {@code ScheduledTask} is
     *         added into the {@code Timetable}.
     */
    public String addPlanToDay(ScheduledTask currTask) {
        if (this.timetable.get(currTask.getDate()) == null) {
            DayPlan dayPlan = new DayPlan();
            dayPlan.addSchedule(currTask);
            this.timetable.put(currTask.getDate(), dayPlan);
            return Ui.TIMETABLE_TASK_ADDED;
        }
        return this.timetable.get(currTask.getDate()).addSchedule(currTask);
    }

    /**
     * Deletes the current {@code ScheduledTask} into this {@code Timetable}.
     * 
     * @param currTask is the {@code ScheduledTask} which will be deleted from the
     *                 {@code Timetable}.
     * @return a message from {@code Duke} stating if the {@code ScheduledTask} is
     *         deleted from the {@code Timetable}.
     */
    public String deletePlanFromDay(ScheduledTask currTask) {
        String UIMessage = this.timetable.get(currTask.getDate()).removeSchedule(currTask);
        if (!this.timetable.get(currTask.getDate()).hasSchedule()) {
            this.timetable.remove(currTask.getDate());
        }
        return UIMessage;
    }

    /**
     * Retrieves the schedule of the given date.
     * 
     * @param date is the date which the scheduled will be retrieved.
     * @return a {@code DayPlan} which contains the schedule for that date.
     */
    public DayPlan getDayPlan(String date) {
        return this.timetable.get(date);
    }

    /**
     * Clears all {@code ScheduledTask} for the given date.
     * 
     * @param date is the date which all {@code ScheduledTask} will be deleted.
     * @return a message from {@code Duke} stating if all the {@code ScheduledTask}
     *         for that date is deleted from the {@code Timetable}.
     */
    public String clearDayPlan(String date) {
        this.timetable.remove(date);
        return Ui.TIMETABLE_CLEARED_DAY_PLAN;
    }

    /**
     * Views all {@code ScheduledTask} added in an order from earliest to the
     * latest.
     * 
     * @return a message from {@code Duke} stating all the {@code ScheduledTask} in
     *         {@code Timetable}.
     */
    public String viewAllScheduledTasks() {
        if (this.timetable.size() == 0) {
            return Ui.TIMETABLE_NO_SCEDULE_SET;
        }
        Map<String, DayPlan> sortedMap = new TreeMap<>(this.getComparator());
        sortedMap.putAll(this.timetable);
        String allScheduledTasks = "";
        Iterator<String> activeDates = sortedMap.keySet().iterator();

        while (activeDates.hasNext()) {
            String currDate = activeDates.next();
            allScheduledTasks += currDate + "\n" + this.timetable.get(currDate).viewDayPlan();
        }
        return allScheduledTasks;
    }

    /**
     * Checks if the current {@code ScheduledTask} clashes with the
     * {@code ScheduledTask} which are already added in this {@code Timetable}.
     * 
     * @param currTask is the {@code ScheduledTask} which will be checked if clash
     *                 happens.
     * @return boolean, true if there is a clash in {@code Timetable}.
     */
    public boolean isClash(ScheduledTask currTask) {
        try {
            return this.timetable.get(currTask.getDate()).isClash(currTask);
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Marks the given {@code ScheduledTask} as done.
     * 
     * @param currTask is the {@code ScheduledTask} which will be marked as done.
     */
    public void markDone(ScheduledTask currTask) {
        this.timetable.get(currTask.getDate()).markeDone(currTask);
    }

    private Comparator<String> getComparator() {
        return new Comparator<String>() {
            @Override
            public int compare(String firstDate, String secondDate) {
                String[] firstDateArr = firstDate.split("-");
                String[] secondDateArr = secondDate.split("-");
                int firstIntDate = Integer.parseInt(firstDateArr[2] + firstDateArr[1] + firstDateArr[1]);
                int secondIntDate = Integer.parseInt(secondDateArr[2] + secondDateArr[1] + secondDateArr[1]);
                return (firstIntDate > secondIntDate) ? -1 : 1;
            }
        };
    }
}
