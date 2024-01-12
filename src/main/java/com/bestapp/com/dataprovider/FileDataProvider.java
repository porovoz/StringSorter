package com.bestapp.com.dataprovider;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public final class FileDataProvider implements DataProvider {

    private Path dataSource;
    private Path dataTarget;

    @Override
    public boolean setSource(String file) {
        Path path;
        try {
            path = Paths.get(file);
        } catch (Exception ignored) {
            return false;
        }

        if (!checkSourcePathIsValid(path)) {
            return false;
        }
        this.dataSource = path;
        return true;
    }

    @Override
    public boolean setTarget(String pathString) {
        Path path = Paths.get(pathString);

        if (!checkTargetPathIsValid(path)) {
            return false;
        }
        this.dataTarget = path;
        return true;
    }

    @Override
    public List<String> getData() throws IOException {

        if (dataSource == null) {
            throw new IllegalStateException("Not all parameters have been set");
        }

        List<String> result;

        try (BufferedReader in = Files.newBufferedReader(dataSource)) {
            result = in.lines().collect(Collectors.toList());
        } catch (Exception e) {
            throw new IOException("Error occurred while writing to source file", e);
        }
        return result;
    }

    @Override
    public boolean saveData(List<String> list) throws IOException {
        try (BufferedWriter writer =
                     Files.newBufferedWriter(dataTarget, StandardCharsets.UTF_8)) {
            for (int i = 0; i < list.size(); i++) {
                writer.append(list.get(i), 0, list.get(i).length());
                if (i < list.size() - 1) {
                    writer.append(System.lineSeparator());
                }
            }
        } catch (Exception e) {
            throw new IOException("Error occurred while writing to target file", e);
        }
        return true;
    }

    private boolean checkTargetPathIsValid(Path path) {
        return  path != null
                && !Files.exists(path)
                && path.getParent() != null;
    }

    private boolean checkSourcePathIsValid(Path path) {
        return Files.exists(path)
                && Files.isRegularFile(path)
                && Files.isReadable(path);
    }
}