# Duke chat bot :robot:

>Creativity is intelligence having FUN - Albert Einstein

This is the chat bot that lets you manage your tasks easily!([download it from here](https://github.com/LuoZhijie-tom/ip.git))

### Commands:
* __todo__ *description* : Add a ToDo task.
* __deadline__ *description* __/by__ *deadline* : Add a Deadline task.
* __event__ *description* __/at__ *event time* : Add an Event.
* __done__ *taskIndex*: Marks the task at *taskIndex* as done.
* __list__ : See all existing tasks.
* __find__ *keyword* : Find all tasks with *keyword*.
* __delete__ *taskIndex* : Delete task at *taskIndex*.

### Current features:
1. add delete and mark three types of tasks which are ToDo, Deadline, and Event.
2. mark the task as done.
3. list all existing tasks.
4. search for tasks based on keyword.

### Features:
- [X] Managing tasks(add, delete, mark as done, list, find)
- [ ] GUI

### Sample codes(`run()` method):
```java
public void run() {
        try {
            tasks = storage.convertFileToTaskList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        ui.greet();
        boolean isExit = false;
        Parser parser = new Parser(" ");
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
```
