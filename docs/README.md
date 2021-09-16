# User Guide

MomoBot is a **chatbot for managing schedules, optimised for use via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, MomoBot can organise your schedules faster than traditional GUI apps.

* Quick start
* Features
    * Add a Task to do : todo
    * Add a Task with deadline : deadline
    * Add an Event Task : event
    * Delete a Task : delete
    * Mark a Task as done: done
    * List current Tasks : list
    * Find Task from list : find
    * Exit Momobot: exit

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest MomoBot.jar from here.
3. Copy the file to the folder you want to use as the home folder for your Momobot.
4. Double-click the file to start the app. The GUI should appear in a few seconds.

Type the command in the command box and press Enter to execute it. e.g. typing list and pressing Enter will list all current tasks.
Some example commands you can try:

list : Lists all current Tasks.

deadline submission /by 2021-01-01T23:59:01 : Adds a deadline for 'Submission' at 1 Jan 2021, 23:59:01

delete 3 : Deletes the 3rd Task shown in the list.

done 3 : Mark the 3rd Task shown in the list as done.

exit : Exits the app.

## Features

### Add a Task to do : todo

Adds a Task to do to the list.

Format: todo DESCRIPTION

Examples: 
* todo Read Book

### Add a Task with deadline : deadline

Adds a Task with deadline to the list.

Format: deadline DESCRIPTION /by yyyy-mm-ddTHH:mm:ss

Examples:
* deadline Submission /by 1980-01-01T01:02:03

### Add an Event Task : event

Adds an event to the list.

Format: event DESCRIPTION /at yyyy-mm-ddTHH:mm:ss

Examples:
* event Meeting /at 1980-01-01T01:02:03

### Delete a Task : delete

Deletes the specified Task from the list.

Format: delete INDEX
* Deletes the Task at the specified INDEX.
* The index refers to the index number shown in the displayed list.
* The index must be a positive integer 1, 2, 3, …

Examples:
* list followed by delete 2 deletes the 2nd task in the list

### Mark a Task as done: done

Mark an existing Task as done.

Format: done INDEX
* Mark Task as done at the specified INDEX.
* The index refers to the index number shown in the displayed list.
* The index must be a positive integer 1, 2, 3, …

Examples:
* list followed by done 2 marks the 2nd task in the list as done.

### List current Tasks : list

Shows a list of all current tasks in the list.

Format: list

### Find Task from list : find

Finds Tasks whose names contain the given keywords.

Format: find KEYWORD

* The search is case-sensitive.
* The order of the keywords does not matter. e.g. Submit Assignment will not match Assignment Submit
* Only the description is searched.
* Only full words will be matched e.g. Tas will not match Task
* Tasks matching at least one keyword will be returned (i.e. OR search). e.g. Submit will return Submit Assignment and Project Submit.

Examples:

* find hi returns all Tasks with 'hi' in its description

### Exit Momobot: exit

Exits the program.

Format: exit
