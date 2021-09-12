package seedu.duke.timetable;

import java.util.ArrayList;
import java.util.Comparator;

import seedu.duke.tasks.ScheduledTask;

public class DayPlan {
    private final ArrayList<ScheduledTask> scheduledTasks;

    public DayPlan() {
        this.scheduledTasks = new ArrayList<>();
    }

    public DayPlan(ArrayList<ScheduledTask> scheduledTasks) {
        this.scheduledTasks = scheduledTasks;
    }

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

    public String addSchedule(ScheduledTask currTask) {
        if (this.isClash(currTask)) {
            return "Timetable clash, unable to add" + currTask.toString();
        }
        scheduledTasks.add(currTask);
        return currTask.toString() + " is added into the timetable";
    }

    public String removeSchedule(ScheduledTask currTask) {
        int currTimeFrom = currTask.getTimeFrom();
        int currTimeTo = currTask.getTimeTo();

        boolean isRemoved = scheduledTasks
                .removeIf(task -> (task.getTimeFrom() == currTimeFrom && task.getTimeTo() == currTimeTo));
        return isRemoved ? currTask.toString() + " is removed from timetable."
                : currTask.toString() + " is not found in timetable.";
    }

    public String viewDayPlan() {
        if (this.scheduledTasks.size() == 0) {
            return "You have no scheduled tasks today!";
        }
        this.scheduledTasks.sort(new Comparator<ScheduledTask>() {

            @Override
            public int compare(ScheduledTask firstTask, ScheduledTask secondTask) {
                return (firstTask.getTimeFrom() > secondTask.getTimeFrom()) ? 1 : -1;
            }
        });
        String dayPlans = "";
        for (int i = 0; i < this.scheduledTasks.size(); i++) {
            dayPlans += this.scheduledTasks.get(i).toString() + "\n";
        }
        return dayPlans;
    }

    public String moveSchedule(ScheduledTask from, ScheduledTask to) {
        DayPlan copy = new DayPlan(new ArrayList<>(this.scheduledTasks));
        copy.removeSchedule(from);

        if (copy.isClash(to)) {
            return "Unable to make changes to the Timetable due to clashes. Please try again.";
        }
        this.removeSchedule(from);
        this.addSchedule(to);
        return "Changes have been made successfully.";
    }

    public void markeDone(ScheduledTask currTask) {
        int index = isExist(currTask);
        if (index < 0) {
            return;
        }
        scheduledTasks.set(index, currTask.markAsDone());
    }

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

    public boolean hasSchedule() {
        return this.scheduledTasks.size() != 0;
    }

    private boolean isSameTask(ScheduledTask pointerTask, int currTimeFrom, int currTimeTo, String description) {
        int pointerFrom = pointerTask.getTimeFrom();
        int pointerTo = pointerTask.getTimeTo();
        String pointerDescription = pointerTask.getDescription();

        return (pointerFrom == currTimeFrom && currTimeTo == pointerTo && description.equals(pointerDescription));
    }

}
