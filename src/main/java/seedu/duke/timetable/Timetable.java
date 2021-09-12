package seedu.duke.timetable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import seedu.duke.tasks.ScheduledTask;
import seedu.duke.tasks.Task;

public class Timetable {
    private final HashMap<String, DayPlan> timetable;

    public Timetable() {
        this.timetable = new HashMap<String, DayPlan>();
    }

    public void initialise(ArrayList<Task> savedTasks) {
        ArrayList<Task> savedTasksList = new ArrayList<Task>((savedTasks));
        savedTasksList.removeIf(task -> !task.getSymbol().equals("ST"));
        savedTasksList.forEach(task -> {
            this.addPlanToDay((ScheduledTask) task);
        });
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
        String UIMessage = this.timetable.get(currTask.getDate()).removeSchedule(currTask);
        if (!this.timetable.get(currTask.getDate()).hasSchedule()) {
            this.timetable.remove(currTask.getDate());
        }
        return UIMessage;
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
        Map<String, DayPlan> sortedMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String firstDate, String secondDate) {
                String[] firstDateArr = firstDate.split("-");
                String[] secondDateArr = secondDate.split("-");
                int firstIntDate = Integer.parseInt(firstDateArr[0] + firstDateArr[1] + firstDateArr[2]);
                int secondIntDate = Integer.parseInt(secondDateArr[0] + secondDateArr[1] + secondDateArr[2]);
                return (firstIntDate > secondIntDate) ? 1 : -1;
            }
        });
        sortedMap.putAll(this.timetable);
        String allScheduledTasks = "";
        Iterator<String> activeDates = sortedMap.keySet().iterator();

        while (activeDates.hasNext()) {
            String currDate = activeDates.next();
            allScheduledTasks += currDate + "\n" + this.timetable.get(currDate).viewDayPlan();
        }
        return allScheduledTasks;
    }

    public boolean isClash(ScheduledTask currTask) {
        try {
            return this.timetable.get(currTask.getDate()).isClash(currTask);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public void markDone(ScheduledTask currTask) {
        this.timetable.get(currTask.getDate()).markeDone(currTask);
    }
}
