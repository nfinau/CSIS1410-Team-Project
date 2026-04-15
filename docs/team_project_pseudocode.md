# Team Project Pseudocode

## Add Task
1. Ask the user for the task title
2. Ask the user for the task description
3. Ask the user for the task priority
4. Create a new task object
5. Add the task to the task list
6. Display confirmation message

## Delete Task
1. Ask the user for the task ID
2. Search for the task in the task list
3. If found, remove the task
4. Display success message
5. If not found, display error message

## Update Task
1. Ask the user for the task ID
2. Search for the task
3. If found, ask for updated title, description, and priority
4. Update the task fields
5. Display success message
6. If not found, display error message

## Mark Task Complete
1. Ask the user for the task ID
2. Search for the task
3. If found, set completion status to true
4. Display success message
5. If not found, display error message

## Filter Tasks
1. Ask the user for a filter value
2. Compare the filter value against each task
3. Add matching tasks to a filtered list
4. Display the filtered list

## Display All Tasks
1. Check if the task list is empty
2. If empty, display a message
3. Otherwise, loop through the task list
4. Print each task's details
