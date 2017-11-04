package com.nononsenseapps.filepicker.ftp;

/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nononsenseapps.filepicker.AbstractFilePickerActivity;
import com.nononsenseapps.filepicker.AbstractFilePickerFragment;
import com.nononsenseapps.filepicker.Utils;

import org.apache.commons.net.ftp.FTP;

/**
 * An example implementation of an FTP file-picker
 */
public class FtpPickerActivity extends AbstractFilePickerActivity<FtpFile> {

    public static final String EXTRA_FTP_SERVER_IP = "extra_ftp_server_ip";
    public static final String EXTRA_FTP_SERVER_PORT = "extra_ftp_server_port";
    public static final String EXTRA_FTP_SERVER_USERNAME = "extra_ftp_server_username";
    public static final String EXTRA_FTP_SERVER_PASSWORD = "extra_ftp_server_password";
    public static final String EXTRA_FTP_SERVER_ROOT_DIR = "extra_ftp_server_root_dir";

    private String serverIp;
    private int serverPort;
    private String username;
    private String password;
    private String serverRootDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        serverIp = getIntent().getStringExtra(EXTRA_FTP_SERVER_IP);
        serverPort = getIntent().getIntExtra(EXTRA_FTP_SERVER_PORT, FTP.DEFAULT_PORT);
        username = getIntent().getStringExtra(EXTRA_FTP_SERVER_USERNAME);
        password = getIntent().getStringExtra(EXTRA_FTP_SERVER_PASSWORD);
        serverRootDir = getIntent().hasExtra(EXTRA_FTP_SERVER_ROOT_DIR) ? getIntent().getStringExtra(EXTRA_FTP_SERVER_ROOT_DIR) : "/";
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

        return FtpPickerFragment.newInstance(
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
    }

    @Override
    public boolean fileVisible(FtpFile item) {
        return visibleFileExtensions.isEmpty() || visibleFileExtensions.contains(Utils.getExtension(item.getName()).toLowerCase());
    }
}