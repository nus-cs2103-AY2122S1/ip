# SaDOS

## Features 

### Tasklist

Add and delete tasks that you want to be listed.

### Filter tasks

Looking for a specific task? Filter tasks based on names or dates.

## Usage

### `Adding Tasks` - Add a new task to the list: Todo/Event/Deadline

Enter your task name and date (optional for Todo events), and pick the desired task type from the 
dropdown list.

Example of usage: 

_input_: newEventName, _input_: (YYYY-MM-DD), _select_:  Event

Expected outcome:

New task is entered into the list and automatically displayed.

### `Toggling Tasks' Doneness` - Toggles a task between done/not done

Select your task by clicking on it, and click the done button in the top right corner.

Expected outcome:

Task's doneness is switched from done to undone or vice versa.

### `Deleting Tasks` - Removes a task

Select your task by clicking on it, and click the delete button in the top right corner.

Expected outcome:

Task is deleted.

### `Filtering Tasks` - Filtering tasks based on keywords and/or by date

Input your keywords and/or date and select the filter option from the dropdown list.

Expected outcome:

A list of the tasks that match the details inputted would appear.

### `Saving/Loading Lists` - Basic saving and loading functions for the tasklist.

Click the save/load button in the top left corner to save the current list or load the most recently
saved list. The save file is located at "./save.txt"
