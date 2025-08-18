package org.delonce;

import org.delonce.cache.SoftCache;
import org.delonce.cache.WeakCache;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        example1();
        System.out.println();
        example2();
    }

    public static void example1() throws IOException, InterruptedException {
        WeakCache weakCache = new WeakCache("cacheProject/files");
        System.out.println("Weak before GC:");
        System.out.println(weakCache.getFromCache("notes.txt"));

        System.gc();

        TimeUnit.SECONDS.sleep(3);
        System.out.println();
        System.out.println("Weak after GC:");
        System.out.println(weakCache.getFromCache("notes.txt"));

        weakCache.loadInCache("notes.txt");
        System.out.println();
        System.out.println("Weak after manually load again:");
        System.out.println(weakCache.getFromCache("notes.txt"));
    }

    public static void example2() throws IOException, InterruptedException {
        SoftCache softCache = new SoftCache("cacheProject/files");
        System.out.println("Soft before GC:");
        System.out.println(softCache.getFromCache("workers.txt"));

        System.gc();

        TimeUnit.SECONDS.sleep(3);
        System.out.println();
        System.out.println("Soft after GC:");
        System.out.println(softCache.getFromCache("workers.txt"));
    }
}