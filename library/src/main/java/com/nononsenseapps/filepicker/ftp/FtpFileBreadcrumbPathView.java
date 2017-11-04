package com.nononsenseapps.filepicker.ftp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.nononsenseapps.filepicker.AbstractBreadcrumbPathView;

public class FtpFileBreadcrumbPathView extends AbstractBreadcrumbPathView<FtpFile> {

    public FtpFileBreadcrumbPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected FtpFile getPath(String path) {
        return new FtpDir(path);
    }
}
