# User Guide for Duke

> Duke helps you manage your tasks and live a more efficient life :).

Duke has the following functions:
* Record tasks
* Mark a task as done
* Remove tasks from the list
* Find tasks with a keyword
* list all tasks

Duke supports finve versions of tasks:
* **Todo** is a task without any time.
* **Event** is a task that starts at a time and end at a time.
* **Deadline** is a task to be done before a certain time.
* **Doafter** is a task to be done after a certain time.

To use Duke, you simply need to type commands in the following command list.
If you want to exit duke, you may click on the cross button on the right corner. 

## Commad List
You may tell duke what to do for you using the following commands:

**Formats Supported**
* `start_date` and `end_date` are in `yyyy-mm-dd` format
* `start_time` and `end_time` are in `hhmm` 24h format
* `time_string` supports any input
* `index` should be integer

**For General Bot Execution**

* `list`
  list current tasks in the list
* `delete index`
  remove the task of the corresponding `index` from the list
* `find keyword`
  find tasks with `keyword` in its name
* `done index`
  mark the task of corresponding index as done

**For Adding Todo**

* `todo task_name`

**For Adding Event**

* `event task_name /at start_date`
* `event task_name /at start_date start_time`
* `event task_name /at start_date /to end_date`
* `event task_name /at start_date start_time /to end_date end_time`
* `event task_name /at time_string`

**For Adding Deadline**

* `deadline task_name /by end_date`
* `deadline taek_name /by end_date end_time`
* `deadline task_name /by time_string`

**For Adding Doafter**

* `doafter task_name /after start_date`
* `doafter task_name /after start_date start_time`
* `doafter task_name /after time_string`

## Reminders
Please follow the above command format strictly, including the spaces, for duke to understand you.
