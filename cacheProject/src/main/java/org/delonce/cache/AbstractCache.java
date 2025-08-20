package org.delonce.cache;

import java.io.IOException;
import java.lang.ref.Reference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<V> {
    private final Map<String, Reference<V>> cache = new HashMap<>();
    private final Path dir;

    public AbstractCache(String dir) {
        Path path = Paths.get(dir);

        if (!Files.exists(path) || !Files.isDirectory(path)) {
            throw new IllegalArgumentException("Directory does not exist: " + path.toAbsolutePath());
        }

        this.dir = path;
    }

    public void loadInCache(String key) throws IOException {
        Path filePath = dir.resolve(Paths.get(key));

        if (!Files.exists(filePath) || Files.isDirectory(filePath)) {
            throw new IllegalArgumentException("File does not exist: " + filePath.toAbsolutePath());
        }

        cache.put(key, createReference(filePath));
    }

    public V getFromCache(String key) throws IOException {
        if (!cache.containsKey(key)) {
            loadInCache(key);
        }

        Reference<V> ref = cache.get(key);

        return ref != null ? ref.get() : null;
    }

    public abstract Reference<V> createReference(Path filePath) throws IOException;
}
