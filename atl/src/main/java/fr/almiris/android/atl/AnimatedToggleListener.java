package fr.almiris.android.atl;

import android.animation.Animator;
import android.view.View;
import android.view.ViewPropertyAnimator;

/**
 * Copyright (c) 2016 Almiris (http://www.almiris.fr)
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files
 * (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished
 * to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF
 * OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
public abstract class AnimatedToggleListener implements View.OnClickListener, View.OnLongClickListener {
    public final static String TAG = AnimatedToggleListener.class.getSimpleName();

    View on;
    View off;
    long duration = 500;

    public AnimatedToggleListener() {
    }

    public abstract void onOn();

    public abstract void onOff();

    public AnimatedToggleListener attachTo(View on, View off) {
        this.on = on;
        this.off = off;
        this.on.setOnClickListener(this);
        this.off.setOnClickListener(this);
        return this;
    }

    public void setToOn() {
        this.on.setVisibility(View.VISIBLE);
        this.on.setEnabled(true);
        this.off.setVisibility(View.INVISIBLE);
        this.off.setEnabled(false);
    }

    public void setToOff() {
        this.on.setVisibility(View.INVISIBLE);
        this.on.setEnabled(false);
        this.off.setVisibility(View.VISIBLE);
        this.off.setEnabled(true);
    }

    @Override
    public void onClick(View v) {
        v.setEnabled(false);
        if (v == on) {
            animateOnToOff();
        }
        else if (v == off) {
            animateOffToOn();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        v.setEnabled(false);
        if (v == on) {
            animateOnToOff();
            return true;
        }
        else if (v == off) {
            animateOffToOn();
            return true;
        }
        return false;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void animateOnToOff() {
        off.setEnabled(false);
        off.setAlpha(0.0f);
        off.setVisibility(View.VISIBLE);
        exit(on.animate()).setDuration(this.duration);
        enter(off.animate()).setDuration(this.duration).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                on.setVisibility(View.INVISIBLE);
                off.setEnabled(true);
                off.animate().setListener(null);
                onOff();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    public void animateOffToOn() {
        on.setEnabled(false);
        on.setAlpha(0.0f);
        on.setVisibility(View.VISIBLE);
        exit(off.animate()).setDuration(this.duration);
        enter(on.animate()).setDuration(this.duration).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                off.setVisibility(View.INVISIBLE);
                on.setEnabled(true);
                on.animate().setListener(null);
                onOn();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    protected ViewPropertyAnimator enter(ViewPropertyAnimator animator) {
        return animator.alpha(1.0f).rotationBy(360.0f);
    }

    protected ViewPropertyAnimator exit(ViewPropertyAnimator animator) {
        return animator.alpha(0.0f).rotationBy(360.0f);
    }
}
