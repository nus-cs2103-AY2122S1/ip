<h1>Duke</h1>

<h2>User guide</h2>
   
Duke is a desktop application for managing tasks, optimized for use via a Command Line Interface (CLI), while still having the benefits of a Graphical User Interface (GUI).

<h3>Quick start</h3>

1. Ensure you have Java 11 or above installed in your computer
1. Download the latest duke.jar from here (when it is ready).
1. Copy the file to the folder you want to use as the home folder for Duke.
1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds
1. Refer to the features below to see what Duke can do! :>


<h3>Features</h3>

<b>Notes about the command format</b>

* Words encased in <angle brackets> are the parameters to be supplied by the user
* Parameters must be in the correct order
* The slashes are to be input together with the parameters, with the parameter after the slash coming immediately after the slash with no space.

Adding a todo task: todo

Adds a todo task to Duke  
Format: `todo <task>`
   
Example: `todo read book`

   
Adding an event: event

Adds an event to Duke  
Format: `event <event> /at<date in yyyy-mm-dd format>`
   
Example: `event CS2103 tutorial /at 2021-09-17`

   
Adding a deadline: deadline

Adds a deadline to Duke  
Format: `deadline <task> /by<date in yyyy-mm-dd format>`
   
Example: `deadline CS2103 iP /by 2021-09-17`
   

Listing all tasks: list

Shows a list of all the tasks Duke currently has  
Format: `list`
   
   
Marking a task as done: done
Marks a task as done  
Format: `task <index of task>`
   
Example: `done 1`
   
   
Delete a task from Duke: delete
   
Deletes a task from Duke  
Format: `delete <index of task>`
   
Example: `delete 1`
   

Find tasks that Duke has: find

Shows which tasks Duke has that match a certain keyword or phrase  
Format: `find <keyword>`
   
Example: `find CS2103`

 
Statistics about different types of tasks: stattask

Shows how many of each type of task Duke has  
Format: `stattask`
   
Statistics about how many tasks are done: statdone
   
Shows how many tasks are marked as done  
Format: `statdone`
   
Statistics about how many tasks are not marked as done: statnotdone
   
Shows how many tasks are not marked as done  
Format: `statnotdone`
