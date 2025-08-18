package org.delonce.cache;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;

public class SoftCache extends AbstractCache<String> {
    public SoftCache(String dir) {
        super(dir);
    }

    @Override
    public Reference<String> createReference(Path filePath) throws IOException {
        return new SoftReference<>(Files.readString(filePath));
    }
}
