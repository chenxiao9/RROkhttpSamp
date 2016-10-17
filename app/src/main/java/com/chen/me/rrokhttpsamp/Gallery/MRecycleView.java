package com.chen.me.rrokhttpsamp.Gallery;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/10/17 0017.
 */

public class MRecycleView extends RecyclerView {
    /**
     * 记录当前第一个View
     */
    private View mCurrentView;

    public MRecycleView(Context context) {
        super(context);
        this.setOnScrollListener(new MScrollChange());
    }

    public MRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setOnScrollListener(new MScrollChange());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mCurrentView=getChildAt(0);
        if (mItemScrollChangeListener != null)
        {
            mItemScrollChangeListener.onChange(mCurrentView,
                    getChildLayoutPosition(mCurrentView));
        }
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent e) {
//
//        if (e.getAction()==MotionEvent.ACTION_MOVE){
//            mCurrentView=getChildAt(0);
//            if (mItemScrollChangeListener!=null){
//                mItemScrollChangeListener.onChange(mCurrentView,
//                        getChildLayoutPosition(mCurrentView));
//            }
//        }
//
//        return super.onTouchEvent(e);
//    }

    public class MScrollChange extends RecyclerView.OnScrollListener{
        /**
         * 判断第一个view是否发生变化，发生才回调
         * @param recyclerView
         * @param dx
         * @param dy
         */
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            View newView=recyclerView.getChildAt(0);
            if (mItemScrollChangeListener!=null){
                if (newView!=null&&newView!=mCurrentView){
                    mCurrentView=newView;
                    mItemScrollChangeListener.onChange(mCurrentView,getChildLayoutPosition(mCurrentView));
                }
            }
        }

        public MScrollChange() {
            super();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }
    }


    /**
     * 滚动时回调接口
     */
    public interface OnItemScrollChangeListener{
        void onChange(View view,int position);
    }

    private OnItemScrollChangeListener mItemScrollChangeListener;

    public void setmItemScrollChangeListener(OnItemScrollChangeListener mItemScrollChangeListener) {
        this.mItemScrollChangeListener = mItemScrollChangeListener;
    }
}
