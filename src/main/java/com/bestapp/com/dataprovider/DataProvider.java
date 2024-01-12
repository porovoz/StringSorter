package com.bestapp.com.dataprovider;

import java.io.IOException;
import java.util.List;

public interface DataProvider {
    boolean setSource(String file);

    boolean setTarget(String pathString);

    List<String> getData() throws IOException;

    boolean saveData(List<String> list) throws IOException;
}