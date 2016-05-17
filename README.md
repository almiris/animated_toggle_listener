# Animated Toggle Listener

A simple android way to create an animated toggle button. The trick is to use two overlapping buttons (the "on" and the "off") and to add a common click (or long click) listener that will animate the transition between the two buttons and trigger the on and the off events.

The AnimatedToggleListener is this helper class. It works with any view (FloatingActionButton, Button...). The animation can be customized very easily.

[![Demo AnimatedToggleListener](https://j.gifs.com/5yr5PA.gif)](https://www.youtube.com/watch?v=50n1soyHhmA)

Define a layout with two buttons (one for each state) :

```xml
<FrameLayout
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="20dp">
    <android.support.design.widget.FloatingActionButton
        android:id="@+id/toggleOn1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:useCompatPadding="true"
        android:theme="@style/toggleOn1"/>
    <android.support.design.widget.FloatingActionButton
        android:id="@+id/toggleOff1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:useCompatPadding="true"
        android:theme="@style/toggleOff1"/>
</FrameLayout>
```

Define the style of the two buttons :

```xml
<style name="toggleOn1">
    <item name="android:src">@android:drawable/ic_lock_silent_mode</item>
    <item name="android:tint">#fff</item>
    <item name="colorAccent">@color/colorPrimary</item>
</style>
<style name="toggleOff1">
    <item name="android:src">@android:drawable/ic_lock_silent_mode_off</item>
</style>
```

Associate the listener class to the two buttons :

```java
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
```

Et voilà !

A more complex example could involve customizing the animation or changing the duration of the animation :

```java
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
```
