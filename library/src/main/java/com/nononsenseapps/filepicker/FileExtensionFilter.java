package com.nononsenseapps.filepicker;

public interface FileExtensionFilter<T> {

    boolean fileVisible(T item);
}
