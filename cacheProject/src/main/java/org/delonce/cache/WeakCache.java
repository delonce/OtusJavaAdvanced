package org.delonce.cache;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

public class WeakCache extends AbstractCache<Supplier<String>> {
    public WeakCache(String dir) {
        super(dir);
    }

    @Override
    public Reference<Supplier<String>> createReference(Path filePath) {
        //System.out.println("File will be really readed");
        return new WeakReference<>(() -> {
            try {
                return Files.readString(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Supplier<String> emptyValue() {
        return () -> null;
    }
}
