package org.delonce.cache.menu;

import org.delonce.cache.SoftCache;
import org.delonce.cache.WeakCache;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class Emulator {
    public static void example1() throws IOException, InterruptedException {
        WeakCache weakCache = new WeakCache("cacheProject/files");
        System.out.println("Weak before GC:");
        Supplier<String> cacheValue = weakCache.getFromCache("notes.txt");
        System.out.println(cacheValue.get());

        cacheValue = null;
        System.gc();

        TimeUnit.SECONDS.sleep(3);
        System.out.println();
        System.out.println("Weak after GC:");
        cacheValue = weakCache.getFromCache("notes.txt");
        System.out.println(cacheValue.get());

        weakCache.loadInCache("notes.txt");
        System.out.println();
        System.out.println("Weak after manually load again:");
        cacheValue = weakCache.getFromCache("notes.txt");
        System.out.println(cacheValue.get());
    }

    public static void example2() throws IOException, InterruptedException {
        SoftCache softCache = new SoftCache("cacheProject/files");
        System.out.println("Soft before GC:");

        Supplier<String> cacheValue = softCache.getFromCache("workers.txt");
        System.out.println(cacheValue.get());

        cacheValue = null;
        System.gc();

        TimeUnit.SECONDS.sleep(3);
        System.out.println();
        System.out.println("Soft after GC:");

        cacheValue = softCache.getFromCache("workers.txt");
        System.out.println(cacheValue.get());
    }
}
