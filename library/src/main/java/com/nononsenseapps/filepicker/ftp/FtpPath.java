package com.nononsenseapps.filepicker.ftp;

/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */


import android.support.annotation.NonNull;

/**
 * Adds path information to FtpFile objects
 */
public class FtpPath {
    public final String path;
    public final FtpFile file;

    public FtpPath(@NonNull String path, @NonNull FtpFile file) {
        this.path = path;
        this.file = file;
    }

    public FtpPath(@NonNull FtpPath mCurrentPath, @NonNull FtpFile file) {
        this.file = file;
        if (mCurrentPath.path.endsWith("/")) {
            this.path = mCurrentPath + file.getName();
        } else {
            this.path = mCurrentPath.path + "/" + file.getName();
        }
    }

    public boolean isDirectory() {
        return file.isDirectory();
    }

    public String getName() {
        return file.getName();
    }

    public String appendToDir(@NonNull String name) {
        if (this.path.endsWith("/")) {
            return path + name;
        } else {
            return path + "/" + name;
        }
    }
}