package utils;

import tasks.Task;

public class StorageParser {
    String taskIcon;
    Boolean isDone;
    String description;
    String time;

    public StorageParser(String storageLine) {
        String[] dataList = storageLine.split(Task.delimiter_regex);
        this.taskIcon = dataList[0];
        this.isDone = Integer.parseInt(dataList[1]) == 1;
        this.description = dataList[2];

        if (dataList.length > 3) {
            this.time = dataList[3];
        }
    }

    public String getTaskIcon() {
        return taskIcon;
    }

    public Boolean getDone() {
        return isDone;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }
}