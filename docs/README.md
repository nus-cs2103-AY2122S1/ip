# User Guide

## Features 

### Adding a To-Do task `<t>`

Adds a to-do task to the list.

Format: '<t NAME>'

### Adding a Deadline task `<d>`

Adds a deadline task to the list.

Format: '<d NAME /by DATE>'

* Date should be entered in YYYY-MM-DD format.

### Adding an Event task `<e>`

Adds an event task to the list.

Format: '<e NAME /at DATE>'

* Date should be entered in YYYY-MM-DD format.

### Viewing the task list `<l>`

Displays the list of tasks.

### Deleting a task `<del>` or `<rem>`

Deletes the task with the specified index from the list.

Format: '<del INDEX>' or '<rem INDEX>'

* You can use `<l>` to refer to the list to find the index of the task you want to delete.

### Marking a task as done `<do>`

Marks the task with the specified index as done.

Format: '<do INDEX>'

* You can use `<l>` to refer to the list to find the index of the task you want to delete.

### Searching the list `<f>` or `<s>`

Searches the list for tasks containing the search query and lists the results.

Format: '<f SEARCH_QUERY>' or '<s SEARCH_QUERY>'

* The search results will be listed with the same indexes they have in the list.

### Wiping the task list `<WIPE>`

Deletes all tasks inside the list.