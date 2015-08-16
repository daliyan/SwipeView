package akiyama.swipe.swipeView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 有侧滑菜单的RecyclerView，注意事件的分发
 * @author zhiwu_yan
 * @version 1.0
 * @since 2015-08-13  22:22
 */
public class SwipeRecyclerView extends RecyclerView {

    private int mLastX = 0;
    private int mLastY = 0;
    public SwipeRecyclerView(Context context){
        super(context);
    }

    public SwipeRecyclerView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public SwipeRecyclerView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action=e.getAction();
        int x = (int) e.getX();
        int y = (int) e.getY();
        switch (action){
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                //判断是否横向滑动
                if (Math.abs(deltaX) < Math.abs(deltaY) *2) {
                    super.onInterceptTouchEvent(e);
                    return true;//不是横向滑动就自己处理了该事件
                }
                return false;//是横向滑动交给item View去处理
        }
        mLastX = x;
        mLastY = y;
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return super.onTouchEvent(e);
    }


    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
    }
}
