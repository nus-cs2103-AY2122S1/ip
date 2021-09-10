package seedu.duke.timetable;

import java.util.ArrayList;

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

        this.scheduledTasks.stream().filter(task -> task.getTimeFrom() < currTimeTo || task.getTimeTo() > currTimeFrom)
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
}
