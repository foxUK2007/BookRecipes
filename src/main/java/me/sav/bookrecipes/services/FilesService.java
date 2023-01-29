package me.sav.bookrecipes.services;

import java.io.File;
import java.nio.file.Path;

public interface FilesService {
    boolean saveToFileRc(String json);

    String readFromFileRc();

    boolean saveToFileIg(String json);

    String readFromFileIg();

    boolean cleanDataFileRc();

    boolean cleanDataFileIg();

    File getDataFileRc();

    File getDataFileIg();

    Path createTempFileRc(String suffix);

    Path createTempFileIg(String suffix);
}
