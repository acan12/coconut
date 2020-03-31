package app.beelabs.com.codebase.component.spinkit;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.SpriteFactory;
import com.github.ybq.android.spinkit.Style;
import com.github.ybq.android.spinkit.sprite.Sprite;

import app.beelabs.com.codebase.R;

public class CoconutSpinKitView extends ProgressBar {

    private Style mStyle;
    private int mColor;
    private Sprite mSprite;

    public CoconutSpinKitView(Context context) {
        this(context, null);
    }

    public CoconutSpinKitView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.SpinKitViewStyle);
    }

    public CoconutSpinKitView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.SpinKitView);
    }


    public void setIconColor(int iconColor) {
        this.mColor = iconColor;
        mSprite.setColor(0);
        mSprite.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CoconutSpinKitView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SpinKitView, defStyleAttr,
                defStyleRes);
        mStyle = Style.values()[a.getInt(R.styleable.SpinKitView_SpinKit_Style, 0)];
        mColor = a.getColor(R.styleable.SpinKitView_SpinKit_Color, Color.GRAY);
        a.recycle();
        init();
        setIndeterminate(true);
    }

    private void init() {
        Sprite sprite = SpriteFactory.create(mStyle);
        sprite.setColor(mColor);
        setIndeterminateDrawable(sprite);
    }

    @Override
    public void setIndeterminateDrawable(Drawable d) {
        if (!(d instanceof Sprite)) {
            throw new IllegalArgumentException("this d must be instanceof Sprite");
        }
        setIndeterminateDrawable((Sprite) d);
    }

    public void setIndeterminateDrawable(Sprite d) {
        super.setIndeterminateDrawable(d);
        mSprite = d;
        if (mSprite.getColor() == 0) {

            mSprite.setColor(mColor);
        }
        onSizeChanged(getWidth(), getHeight(), getWidth(), getHeight());
        if (getVisibility() == VISIBLE) {

            mSprite.start();
        }
    }

    @Override
    public Sprite getIndeterminateDrawable() {
        return mSprite;
    }

    public void setColor(int color) {
        this.mColor = color;
        if (mSprite != null) {

            mSprite.setColor(color);
        }
        invalidate();
    }

    @Override
    public void unscheduleDrawable(Drawable who) {
        super.unscheduleDrawable(who);
        if (who instanceof Sprite) {

            ((Sprite) who).stop();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            if (mSprite != null && getVisibility() == VISIBLE) {

                mSprite.start();
            }
        }
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
        if (screenState == View.SCREEN_STATE_OFF) {

            if (mSprite != null) {

                mSprite.stop();
            }
        }
    }
}
