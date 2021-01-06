package com.petrosp.threaded.scheduler;

import java.util.Optional;
import java.util.concurrent.*;

/**
 * Creates a new thread that executes a callback method that is provided after a random or fixed delay
 * Kill this thread after you are done by calling terminateAfter() or terminateNow()
 */
public final class PFixedTaskScheduler
{
    private final ScheduledExecutorService executor;
    private final PCallable callback;
    private final int lowerLimitMillis, upperLimitMillis;

    private boolean isRepeating;
    private Optional<Future<?>> repeatingTaskOpt;
    private Optional<Future<?>> oneTimeTaskOpt;

    public PFixedTaskScheduler(PCallable callback, int lowerLimitMillis, int upperLimitMillis)
    {
        this.callback = callback;
        this.lowerLimitMillis = lowerLimitMillis;
        this.upperLimitMillis = upperLimitMillis;
        this.executor = Executors.newSingleThreadScheduledExecutor();
        this.repeatingTaskOpt = Optional.empty();
        this.oneTimeTaskOpt = Optional.empty();
    }


    /**
     * Schedules a single task to execute the object's callback, using the existing delay bounds.
     * Can be called even if <code>startRepeating()</code> has been called on the object.
     */
    public void startOnce()
    {
        Runnable task = callback::call;
        long delay = ThreadLocalRandom.current().nextInt(lowerLimitMillis, upperLimitMillis + 1);
        oneTimeTaskOpt = Optional.of(executor.schedule(task, delay, TimeUnit.MILLISECONDS));
    }


    /**
     * Schedules a single task to execute the object's callback, using the provided delay bounds.
     * Can be called even if <code>startRepeating()</code> has been called on the object.
     * @param lowerLimitMillis The new lower delay bound (in milliseconds).
     * @param upperLimitMillis The new upper delay bound (in milliseconds).
     */
    public void startOnce(int lowerLimitMillis, int upperLimitMillis)
    {
        Runnable task = callback::call;
        long delay = ThreadLocalRandom.current().nextInt(lowerLimitMillis, upperLimitMillis + 1);
        oneTimeTaskOpt = Optional.of(executor.schedule(task, delay, TimeUnit.MILLISECONDS));
    }


    /**
     * Repeats scheduling the same task when the previous one has finished,
     * using the existing object's callback delay bounds,
     * Can be called only once, until it has been stopped.
     */
    public void startRepeating()
    {
        if(isRepeating) return;
        isRepeating = true;

        startRepeating_();
    }

    private void startRepeating_()
    {
        Runnable task = () -> {
            callback.call();
            startRepeating_();
        };

        long delay = ThreadLocalRandom.current().nextInt(lowerLimitMillis, upperLimitMillis + 1);
        repeatingTaskOpt = Optional.of(executor.schedule(task, delay, TimeUnit.MILLISECONDS));
    }


    /**
     * Cancels all scheduled tasks. Object can be restarted later.
     */
    public void interruptNow()
    {
        repeatingTaskOpt.ifPresent(v -> v.cancel(true));
        oneTimeTaskOpt.ifPresent(v -> v.cancel(true));
        isRepeating = false;
    }


    /**
     * Call this function before terminating your program to kill the active thread.
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
     * @return true if the method <code>interruptNow()</code> hasn't been called.
     */
    public boolean isInterrupted()
    {
        if (!repeatingTaskOpt.isPresent() && !oneTimeTaskOpt.isPresent()){
            return false;
        }

        return repeatingTaskOpt.map(Future::isCancelled).orElseGet(() -> oneTimeTaskOpt.get().isCancelled());
    }


    /**
     * @return true if <code>terminateNow()</code> or <code>terminateAfter()</code> has been called.
     */
    public boolean isTerminated()
    {
        return executor.isShutdown();
    }
}
