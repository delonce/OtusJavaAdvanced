package org.delonce;

import org.delonce.cache.menu.Emulator;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Emulator.example1();
        System.out.println();
        Emulator.example2();
    }
}