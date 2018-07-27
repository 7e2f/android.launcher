package org.ch.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class HomeScreen extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom);
    }

    @Override
    public void onClick(View v) {
        String pkg = v.getTag().toString();
        Intent intent = getPackageManager().getLaunchIntentForPackage(pkg);
        startActivity(intent);

    }
}
