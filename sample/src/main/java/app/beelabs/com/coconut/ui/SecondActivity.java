package app.beelabs.com.coconut.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import app.beelabs.com.coconut.R;
import app.beelabs.com.codebase.base.BaseActivity;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}
