package com.nononsenseapps.filepicker.sample.file;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;

import com.nononsenseapps.filepicker.AbstractFilePickerActivity;
import com.nononsenseapps.filepicker.AbstractFilePickerFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilteredFilePickerActivity extends AbstractFilePickerActivity<File> {

    public static final String EXTRA_VISIBLE_FILE_EXTENSIONS = "EXTRA_VISIBLE_FILE_EXTENSIONS";
    private List<String> visibleFileExtensions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        visibleFileExtensions = getIntent().hasExtra(EXTRA_VISIBLE_FILE_EXTENSIONS) ?
                                getIntent().getStringArrayListExtra(EXTRA_VISIBLE_FILE_EXTENSIONS) :
                                new ArrayList<String>();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected AbstractFilePickerFragment<File> getFragment(
            @Nullable String startPath,
            int mode,
            boolean allowMultiple,
            boolean allowCreateDir,
            boolean allowExistingFile,
            boolean singleClick) {

        FilteredFilePickerFragment fragment = new FilteredFilePickerFragment();
        fragment.setArgs(startPath != null ? startPath : Environment.getExternalStorageDirectory().getPath(), mode, allowMultiple, allowCreateDir, allowExistingFile, singleClick);
        fragment.getArguments().putStringArrayList(EXTRA_VISIBLE_FILE_EXTENSIONS, new ArrayList<>(visibleFileExtensions));
        return fragment;
    }
}
