package com.ajoshow.mock.domain.utils;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * @author Andy on 2017/4/26.
 */
public class FutureUtils {


    /**
     * Returns a new CompletableFuture that is asynchronously completed by runnable timer in the
     * ForkJoinPool.commonPool() with the value obtained by calling the given Supplier.
     *
     * @param millisecs
     * @param supplier
     * @param <T>
     * @return
     */
    public static <T> CompletableFuture<T> delayAndGetAsync(long millisecs, Supplier<T> supplier){
        return CompletableFuture.supplyAsync(()-> {
            try {
                Thread.sleep(millisecs);
            } catch (InterruptedException e) {
                // ignore
            }
            return supplier.get();
        });
    }
}
