package com.nononsenseapps.filepicker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class AbstractBreadcrumbPathView<T> extends LinearLayout {

    private LinearLayout breadcrumbContainer;
    private LayoutInflater layoutInflater;
    private BreadcrumbListener breadcrumbListener;

    public AbstractBreadcrumbPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.breadcrumb_path, this);
        layoutInflater = LayoutInflater.from(context);
        breadcrumbContainer = findViewById(R.id.breadcrumbContainer);
        TextView rootPathTextView = findViewById(R.id.rootPathTextView);
        rootPathTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (breadcrumbListener != null) {
                    breadcrumbListener.onBreadcrumbClicked(getPath("/"));
                }
            }
        });
    }

    protected abstract T getPath(String path);

    public void setBreadcrumbListener(BreadcrumbListener breadcrumbListener) {
        this.breadcrumbListener = breadcrumbListener;
    }

    public void setPath(String fullPath) {
        if (fullPath != null) {
            breadcrumbContainer.removeAllViews();
            String [] pathParts = fullPath.split("/");
            String pathToCurrentBreadcrumbPart = "/";
            for (int i = 1; i < pathParts.length; i++) {
                final View breadcrumbPathPartView = layoutInflater.inflate(R.layout.breadcrumb_path_part, null);
                pathToCurrentBreadcrumbPart += pathParts[i] + "/";
                breadcrumbPathPartView.setTag(pathToCurrentBreadcrumbPart);

                TextView pathPartValueTextView = breadcrumbPathPartView.findViewById(R.id.pathPartValue);
                pathPartValueTextView.setText(pathParts[i]);

                breadcrumbPathPartView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (breadcrumbListener != null) {
                            breadcrumbListener.onBreadcrumbClicked(getPath(breadcrumbPathPartView.getTag().toString()));
                        }
                    }
                });

                breadcrumbContainer.addView(breadcrumbPathPartView);
            }
        }
    }
}
