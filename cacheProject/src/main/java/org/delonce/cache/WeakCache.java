package org.delonce.cache;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.nio.file.Files;
import java.nio.file.Path;

public class WeakCache extends AbstractCache<String> {
    public WeakCache(String dir) {
        super(dir);
    }

    @Override
    public Reference<String> createReference(Path filePath) throws IOException {
        return new WeakReference<>(Files.readString(filePath));
    }
}
