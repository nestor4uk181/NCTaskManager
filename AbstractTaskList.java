package ua.edu.sumdu.j2se.makarovskyi.tasks;

/**
 * Abstract class which describes operations used with task list
 */

public abstract class AbstractTaskList {


    /**
     * Method that adds specified task to the list
     */
    public abstract void add(Task task);


    /**
     * Method that removes a specified task from the list (returns true if such task was on the list)
     */
    public abstract boolean remove(Task task);

    /**
     * Method that returns the size of the list with tasks
     */
    public abstract int size();

    /**
     * Method which allows to get specified task from the list by index
     */
    public abstract Task getTask(int index);


    /**
     * Method which returns the tasks which are about to happen within the specified period of time
     */

    public AbstractTaskList incoming(int from, int to, ListTypes.types type) {
        AbstractTaskList result = TaskListFactory.createTaskList(type);
        for (int i = 0; i < size(); i++) {
            if (getTask(i).nextTimeAfter(from) > from && getTask(i).nextTimeAfter(to) < to) {
                result.add(getTask(i));
            }
        }
        return result;
    }

}
