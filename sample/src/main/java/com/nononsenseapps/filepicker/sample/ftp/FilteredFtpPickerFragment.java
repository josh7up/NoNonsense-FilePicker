package com.nononsenseapps.filepicker.sample.ftp;

import android.os.Bundle;

import com.nononsenseapps.filepicker.Utils;
import com.nononsenseapps.filepicker.ftp.FtpFile;
import com.nononsenseapps.filepicker.ftp.FtpPickerFragment;

import java.util.ArrayList;
import java.util.List;

import static com.nononsenseapps.filepicker.sample.file.FilteredFilePickerActivity.EXTRA_VISIBLE_FILE_EXTENSIONS;

public class FilteredFtpPickerFragment extends FtpPickerFragment {

    private List<String> visibleFileExtensions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        visibleFileExtensions = getArguments().getStringArrayList(EXTRA_VISIBLE_FILE_EXTENSIONS) != null ?
                getArguments().getStringArrayList(EXTRA_VISIBLE_FILE_EXTENSIONS) :
                new ArrayList<String>();
    }

    @Override
    protected boolean isItemVisible(FtpFile file) {
        return (isDir(file) ||
                (mode == MODE_FILE || mode == MODE_FILE_AND_DIR ||
                (mode == MODE_NEW_FILE && allowExistingFile)) &&
                visibleFileExtensions.isEmpty() || visibleFileExtensions.contains(Utils.getExtension(file.getName()).toLowerCase()));
    }
}
