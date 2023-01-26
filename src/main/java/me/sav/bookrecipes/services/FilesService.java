package me.sav.bookrecipes.services;

public interface FilesService {
    boolean saveToFileRc(String json);

    String readFromFileRc();

    boolean saveToFileIg(String json);

    String readFromFileIg();
}
