package seedu.duke.timetable;

import java.util.HashMap;
import java.util.Iterator;

import seedu.duke.tasks.ScheduledTask;

public class Timetable {
    private final HashMap<String, DayPlan> timetable;

    public Timetable() {
        this.timetable = new HashMap<String, DayPlan>();
    }

    public String addPlanToDay(ScheduledTask currTask) {
        if (this.timetable.get(currTask.getDate()) == null) {
            DayPlan dayPlan = new DayPlan();
            dayPlan.addSchedule(currTask);
            this.timetable.put(currTask.getDate(), dayPlan);
            return "Task added to schedule";
        }
        return this.timetable.get(currTask.getDate()).addSchedule(currTask);
    }

    public String deletePlanFromDay(ScheduledTask currTask) {
        return this.timetable.get(currTask.getDate()).removeSchedule(currTask);
    }

    public DayPlan getDayPlan(String date) {
        return this.timetable.get(date);
    }

    public String clearDayPlan(String date) {
        this.timetable.remove(date);
        return "Plans for the day is cleared";
    }

    public String viewAllScheduledTasks() {
        if (this.timetable.size() == 0) {
            return "You have not set any schedule yet!";
        }
        String allScheduledTasks = "";
        Iterator<String> activeDates = this.timetable.keySet().iterator();

        while (activeDates.hasNext()) {
            String currDate = activeDates.next();
            allScheduledTasks += currDate + "\n" + this.timetable.get(currDate).toString();
        }
        return allScheduledTasks;
    }
}
