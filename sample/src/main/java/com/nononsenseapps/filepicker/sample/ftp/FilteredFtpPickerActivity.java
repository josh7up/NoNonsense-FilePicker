/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.nononsenseapps.filepicker.sample.ftp;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nononsenseapps.filepicker.AbstractFilePickerFragment;
import com.nononsenseapps.filepicker.ftp.FtpFile;
import com.nononsenseapps.filepicker.ftp.FtpPickerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * An example implementation of an FTP file-picker
 */
public class FilteredFtpPickerActivity extends FtpPickerActivity {

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
    protected AbstractFilePickerFragment<FtpFile> getFragment(
            @Nullable String startPath,
            int mode,
            boolean allowMultiple,
            boolean allowCreateDir,
            boolean allowExistingFile,
            boolean singleClick) {

        FilteredFtpPickerFragment fragment = new FilteredFtpPickerFragment();
        fragment.setArgs(
                startPath,
                mode,
                allowMultiple,
                allowCreateDir,
                allowExistingFile,
                singleClick,
                serverIp,
                serverPort,
                username,
                password,
                serverRootDir);
        fragment.getArguments().putStringArrayList(EXTRA_VISIBLE_FILE_EXTENSIONS, new ArrayList<>(visibleFileExtensions));
        return fragment;
    }
}
