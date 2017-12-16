package app.beelabs.com.codebase.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import app.beelabs.com.codebase.R;

/**
 * Created by arysuryawan on 12/16/17.
 */

public class BaseDialog extends Dialog {

    private int widthLayout = WindowManager.LayoutParams.MATCH_PARENT;
    private int heightLayout = WindowManager.LayoutParams.MATCH_PARENT;

    public BaseDialog(@NonNull Context context, int style) {
        super(context, style);
    }


    public int getWidthLayout() {
        return widthLayout;
    }

    public void setWidthLayout(int widthLayout) {
        this.widthLayout = widthLayout;
    }

    public int getHeightLayout() {
        return heightLayout;
    }

    public void setHeightLayout(int heightLayout) {
        this.heightLayout = heightLayout;
    }

    protected void setWindowContentDialogLayout(int layoutId) {
        Window w = getWindow();
        w.setBackgroundDrawable(new ColorDrawable(getContext().getResources().getColor(R.color.color_black_transparent80)));
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        w.setLayout(widthLayout, heightLayout);
        WindowManager.LayoutParams wlp = w.getAttributes();
        wlp.gravity = Gravity.CENTER;
        w.setAttributes(wlp);

        setContentView(layoutId);

    }
}