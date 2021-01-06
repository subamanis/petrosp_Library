package com.petrosp.assist.threaded.scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Creates a new thread that is responsible of executing any scheduled tasks
 * Multiple parallel scheduled tasks are allowed to execute.
 * Kill this thread after you are done by calling terminateAfter() or terminateNow()
 */
public final class PTaskScheduler
{
    private final ScheduledExecutorService executor;
    private final Map<Integer, Future<?>> taskMap = new HashMap<>();

    public PTaskScheduler()
    {
        executor = Executors.newSingleThreadScheduledExecutor();
    }


    /**
     * Schedules a single task to execute the provided callback, using the provided delay bounds.
     * If the provided id for the task already exists, it cancels the existing task and replaces it.
     * @param id An identifier for the task.
     * @param callback A method to be called by the task.
     * @param lowerLimitMillis The lower delay bound (in milliseconds).
     * @param upperLimitMillis The upper delay bound (in milliseconds).
     */
    public void startOnce(int id, PCallable callback, int lowerLimitMillis, int upperLimitMillis)
    {
        stopExistingTaskAt(id);
        Runnable task = callback::call;
        long delay = ThreadLocalRandom.current().nextInt(lowerLimitMillis, upperLimitMillis + 1);
        taskMap.put(id, executor.schedule(task, delay, TimeUnit.MILLISECONDS));
    }


    /**
     * Repeats scheduling the same task when the previous one has finished,
     * to execute the provided callback, using the provided delay bounds.
     * If the provided id for the task already exists, it cancels the existing task and replaces it.
     */
    public void startRepeating(int id, PCallable callback, int lowerLimitMillis, int upperLimitMillis)
    {
        stopExistingTaskAt(id);
        startRepeating_(id, callback, lowerLimitMillis, upperLimitMillis);
    }

    private void startRepeating_(int id, PCallable callback, int lowerLimitMillis, int upperLimitMillis)
    {
        Runnable task = () -> {
            callback.call();
            startRepeating_(id, callback, lowerLimitMillis, upperLimitMillis);
        };

        long delay = ThreadLocalRandom.current().nextInt(lowerLimitMillis, upperLimitMillis + 1);
        taskMap.put(id, executor.schedule(task, delay, TimeUnit.MILLISECONDS));
    }


    /**
     * Cancels all scheduled tasks before they have a chance to execute
     */
    public void interruptAllNow()
    {
        for (Future<?> f : taskMap.values()){
            f.cancel(true);
        }
    }


    /**
     * Cancels a particular task before it has a chance to execute.
     * @param id The task's identifier as provided when scheduled.
     */
    public void interruptNow(int id)
    {
        stopExistingTaskAt(id);
    }


    /**
     * Call this function before terminating your program!
     * Executes the remaining scheduled tasks but ignores any more incoming scheduling requests.
     * Object cannot be restarted later.
     */
    public void terminateAfter()
    {
        executor.shutdown();
    }


    /**
     * Call this function before terminating your program to kill the active thread.
     * Cancels all scheduled tasks. Object cannot be restarted later.
     */
    public void terminateNow()
    {
        executor.shutdownNow();
    }


    /**
     * @return true if <code>terminateNow()</code> or <code>terminateAfter()</code> has been called.
     */
    public boolean isTerminated()
    {
        return executor.isShutdown();
    }



    private void stopExistingTaskAt(int id)
    {
        if (taskMap.containsKey(id)) {
            taskMap.get(id).cancel(true);
        }
    }}
