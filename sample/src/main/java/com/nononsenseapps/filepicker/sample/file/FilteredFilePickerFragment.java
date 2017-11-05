package com.nononsenseapps.filepicker.sample.file;

import android.os.Bundle;

import com.nononsenseapps.filepicker.FilePickerFragment;
import com.nononsenseapps.filepicker.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.nononsenseapps.filepicker.sample.file.FilteredFilePickerActivity.EXTRA_VISIBLE_FILE_EXTENSIONS;

public class FilteredFilePickerFragment extends FilePickerFragment {

    private List<String> visibleFileExtensions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        visibleFileExtensions = getArguments().getStringArrayList(EXTRA_VISIBLE_FILE_EXTENSIONS) != null ?
                                getArguments().getStringArrayList(EXTRA_VISIBLE_FILE_EXTENSIONS) :
                                new ArrayList<String>();
    }

    @Override
    protected boolean isItemVisible(File file) {
        if (!showHiddenItems && file.isHidden()) {
            return false;
        }
        return (isDir(file) ||
                (mode == MODE_FILE || mode == MODE_FILE_AND_DIR ||
                (mode == MODE_NEW_FILE && allowExistingFile)) &&
                 visibleFileExtensions.isEmpty() || visibleFileExtensions.contains(Utils.getExtension(file.getName()).toLowerCase()));
    }
}
