package duke.util;

import java.time.LocalDateTime;

import duke.tasks.Task;
import duke.tasks.TaskStatuses;
import duke.tasks.TaskTypes;

/**
 * The {@code Query} class represents a user query when using the {@code Find} command.
 */
public class Query {

    private final String queryType;
    private String queryString;
    private TaskStatuses queryStatus;
    private TaskTypes queryTaskType;
    private LocalDateTime queryDateTime;

    /**
     * Initializes a {@code Query} with a specified {@code queryType} and {@code queryValue}.
     *
     * @param queryType {@code String} representing type of {@code Query}
     * @param queryValue Value of Query. One of String, TaskStatuses, TaskTypes, or LocalDateTime
     */
    public Query (String queryType, Object queryValue) {
        this.queryType = queryType;

        if (queryValue instanceof String) {
            this.queryString = (String) queryValue;
        } else if (queryValue instanceof TaskStatuses) {
            this.queryStatus = (TaskStatuses) queryValue;
        } else if (queryValue instanceof TaskTypes) {
            this.queryTaskType = (TaskTypes) queryValue;
        } else if (queryValue instanceof LocalDateTime) {
            this.queryDateTime = (LocalDateTime) queryValue;
        }
    }

    /**
     * Checks if given {@code Task} matches with the {@code Query}.
     *
     * @param task {@code Task} to be compared against the {@code Query}
     * @return {@code boolean} value representing a match
     */
    public boolean isMatch (Task task) {
        switch (queryType) {
        case "string":
            return task.isMatch(queryString);
        case "status":
            return task.isMatch(queryStatus);
        case "type":
            return task.isMatch(queryTaskType);
        case "datetime":
            return task.isMatch(queryDateTime);
        default:
            return false;
        }
    }
}
