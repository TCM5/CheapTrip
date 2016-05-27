package com.fmt.cheaptrip.customviews;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 *
 */
public class GreenProgressDialog extends ProgressDialog {


    public GreenProgressDialog(Context context) {
        super(context);
    }

    @Override
    public void setProgressStyle(int style) {
        super.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    @Override
    public void setIcon(Drawable icon) {
        super.setIcon(android.R.drawable.ic_dialog_info);
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(false);
    }

    @Override
    public void setIndeterminate(boolean indeterminate) {
        super.setIndeterminate(true);
    }

    @Override
    public void setMessage(CharSequence message) {
        super.setMessage("TESTE");
    }
}
