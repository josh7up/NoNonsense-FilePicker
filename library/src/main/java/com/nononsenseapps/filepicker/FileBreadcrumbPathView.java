package com.nononsenseapps.filepicker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.io.File;

public class FileBreadcrumbPathView extends AbstractBreadcrumbPathView<File> {

    public FileBreadcrumbPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected File getPath(String path) {
        return new File(path);
    }
}
