package com.chen.propertyani;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv_hello)
    TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_hello,R.id.btn_obj_ani,R.id.btn_point_ani})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_hello:
                ValueAnimator ani = ValueAnimator.ofFloat(0f, 1f);
                ani.setDuration(1000);
                ani.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float currentValue = (float) animation.getAnimatedValue();
                        Log.d("TAG", "current value is " + currentValue);
                    }
                });
                ani.start();
                break;
            case R.id.btn_obj_ani:
//                ObjectAnimator moveIn = ObjectAnimator.ofFloat(tvHello, "translationX", -500f, 0f);
//                ObjectAnimator rotate = ObjectAnimator.ofFloat(tvHello, "rotation", 0f, 360f);
//                ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(tvHello, "alpha", 1f, 0f, 1f);
//                AnimatorSet animSet = new AnimatorSet();
//                animSet.play(rotate).with(fadeInOut).after(moveIn);
//                animSet.setDuration(5000);
//                animSet.start();

                Animator animator= AnimatorInflater.loadAnimator(MainActivity.this,R.animator.animator);
                animator.setTarget(tvHello);
                animator.start();
                break;
            case R.id.btn_point_ani:
                tvHello.animate().alpha(0.5f).x(500).y(500).setDuration(5000)
                    .setInterpolator(new BounceInterpolator());
                break;
        }

    }

}
