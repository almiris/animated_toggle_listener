package fr.almiris.android.atl.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.TextView;

import fr.almiris.android.atl.AnimatedToggleListener;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        setupDemo1();
        setupDemo2();
    }

    public void setupDemo1() {
        View buttonOff1 = findViewById(R.id.toggleOff1);
        View buttonOn1 = findViewById(R.id.toggleOn1);

        final TextView toggleMessage1 = (TextView)findViewById(R.id.toggleMessage1);
        toggleMessage1.setText(getString(R.string.toggle_message_off));

        new AnimatedToggleListener() {
            @Override
            public void onOn() {
                toggleMessage1.setText(getString(R.string.toggle_message_on));
            }

            @Override
            public void onOff() {
                toggleMessage1.setText(getString(R.string.toggle_message_off));
            }
        }.attachTo(buttonOn1, buttonOff1).setToOff();
    }

    public void setupDemo2() {
        View buttonOff2 = findViewById(R.id.toggleOff2);
        View buttonOn2 = findViewById(R.id.toggleOn2);

        final TextView toggleMessage2 = (TextView)findViewById(R.id.toggleMessage2);
        toggleMessage2.setText(getString(R.string.toggle_message_off));

        new AnimatedToggleListener() {
            @Override
            public void onOn() {
                toggleMessage2.setText(getString(R.string.toggle_message_on));
            }

            @Override
            public void onOff() {
                toggleMessage2.setText(getString(R.string.toggle_message_off));
            }

            @Override
            protected ViewPropertyAnimator enter(ViewPropertyAnimator animator) {
                return animator.rotationXBy(360.0f);
            }

            @Override
            protected ViewPropertyAnimator exit(ViewPropertyAnimator animator) {
                return animator.rotationXBy(360.0f);
            }
        }.attachTo(buttonOn2, buttonOff2).setToOff().setDuration(1000);
    }
}
