package akiyama.library.swipe;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import akiyama.library.R;
import akiyama.library.swipeView.SwipeMenuButtonView;

/**
 * 滑动item，可以作为View的布局文件
 * @author zhiwu_yan
 * @since 2015/8/13
 */
public class SwipeItemLayout extends LinearLayout {

    /**
     * 按下和抬起 200毫秒以内就认为是单击事件
     */
    private static final long CLICK_MAX_TIME = 200;

    /**
     * 当前SwipeItemLayout的布局View
     */
    private View mView;
    /**
     * SwipeItemLayout布局Item的主要内容
     */
    private LinearLayout mContentLl;
    /**
     * 侧滑菜单布局
     */
    private LinearLayout mMenuLl;
    /**
     * 需要附加的到这个SwipeItenView的View
     */
    private View mSwipItemLayout;
    /**
     * 滑动对象
     */
    private Scroller mScroller;
    /**
     * 最后触摸事件的X坐标
     */
    private int mLastX = 0;
    /**
     * 最后触摸事件的Y坐标
     */
    private int mLastY = 0;
    /**
     * 菜单布局文件的宽度
     */
    private int mHolderWidth;
    /**
     * 需要附加布局的layoutId,一般用来显示我们列表中的内容
     */
    private int mLayoutId;
    private Context mContext;
    /**
     * 菜单项目集合
     */
    private List<SwipeMenuItem> mSwipeItems;
    /**
     * 包含菜单的长度
     */
    private int mMenuCount;
    /**
     * 单击事件
     */
    private SwipeClick mSwipeClick;
    /**
     * 按下屏幕的开始时间
     */
    private long mStartTime = 0;
    /**
     * 是否发生滑动偏移
     */
    private boolean mIsSlide = false;

    public SwipeItemLayout(Context context) {
        super(context);
        init(context);
    }

    public SwipeItemLayout(Context context, AttributeSet attrs) {
        super(context,attrs);
        init(context);
    }

    /**
     * 初始化数据
     * @param context
     */
    private void init(Context context) {
        this.mContext = context;
        mSwipeItems = new ArrayList<>();
        mScroller = new Scroller(context);
        setOrientation(LinearLayout.HORIZONTAL);
        mView = View.inflate(context, R.layout.layout_custem_swipe, this);//附加merge布局
        mContentLl = (LinearLayout) mView.findViewById(R.id.item_content_ll);//具体内容
        mMenuLl = (LinearLayout) mView.findViewById(R.id.item_menu_ll);//需要添加的菜单项目
        setBackgroundResource(R.drawable.select_bg);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int scrollX = getScrollX();
        if(scrollX == 0){
            mIsSlide = false;
        }else {
            mIsSlide = true;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mStartTime = Calendar.getInstance().getTimeInMillis();
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                setPressed(!mIsSlide);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                //判断是否横向滑动
                if (Math.abs(deltaX) < Math.abs(deltaY) *2) {
                    break;
                }
                // 计算滑动终点是否合法，防止滑动越界
                int newScrollX = scrollX - deltaX;
                if (deltaX != 0) {
                    if (newScrollX < 0) {
                        newScrollX = 0;
                    } else if (newScrollX > mHolderWidth) {
                        newScrollX = mHolderWidth;
                    }
                    scrollTo(newScrollX, 0);
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                long endTime = Calendar.getInstance().getTimeInMillis();
                //当点击时间 < CLICK_MAX_TIME,并且没有左滑的时候响应点击事件
                if(mStartTime !=0 && endTime - mStartTime < CLICK_MAX_TIME && !mIsSlide){
                    if(mSwipeClick!=null ){
                        mSwipeClick.swipeLayoutClickEvent();
                    }
                }
                int newScrollX = 0;
                // 这里做了下判断，当松开手的时候，会自动向两边滑动，具体向哪边滑，要看当前所处的位置
                if (scrollX - mHolderWidth * 0.5 > 0) {
                    newScrollX = mHolderWidth;
                }
                // 慢慢滑向终点
                smoothScrollTo(newScrollX, 0);
                setPressed(false);//移除点击效果
                break;
            }
            default:
                setPressed(false);
                break;
        }

        mLastX = x;
        mLastY = y;
        return true;//自己来处理这个事件
    }

    /**
     * 附加布局
     * @param mLayoutId 附加布局的ID
     */
    private void setLayoutId(int mLayoutId) {
        this.mLayoutId = mLayoutId;
        setSwipeItemView(mContext);
    }

    /**
     * 添加附加布局和菜单项
     * @param mLayoutId
     * @param mSwipeItems
     */
    public void setLayoutAndMenu(int mLayoutId,List<SwipeMenuItem> mSwipeItems){
        setLayoutId(mLayoutId);
        setSwipeMenuItems(mSwipeItems);
    }

    /**
     * 需要设置的菜单项
     * @param mSwipeItems
     */
    private void setSwipeMenuItems(List<SwipeMenuItem> mSwipeItems) {
        this.mSwipeItems = mSwipeItems;
        setMenuItem();
    }

    /**
     * 设置侧滑菜单项
     */
    public void setMenuItem(){
        if(mSwipeItems!=null && mSwipeItems.size()>0){
            mMenuCount = mSwipeItems.size();
            mHolderWidth =0;
            for(int i=0;i<mMenuCount;i++){
                SwipeMenuButtonView swipeMenuButtonView=new SwipeMenuButtonView(mContext,mSwipeItems.get(i));
                mHolderWidth += mSwipeItems.get(i).getWidth();//动态计算菜单所占的空间
                mMenuLl.addView(swipeMenuButtonView);
            }
            //需要重新设置菜单布局的大小，否则默认是0
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(mHolderWidth,  LinearLayout.LayoutParams.MATCH_PARENT);
            mMenuLl.setLayoutParams(params);
        }
    }

    /**
     * 设置附加的布局
     * @param context
     */
    public void setSwipeItemView(Context context){
        mSwipItemLayout = LayoutInflater.from(context).inflate(mLayoutId,null);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.MATCH_PARENT);
        mSwipItemLayout.setLayoutParams(params);
        if(mContentLl!=null){
            mContentLl.addView(mSwipItemLayout);
        }
    }

    public View getSwipItemLayout() {
        return mSwipItemLayout;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }


    /**
     * 滑动到某个位置
     * @param destX
     * @param destY
     */
    private void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        mScroller.startScroll(scrollX, 0, delta, 0, Math.abs(delta) * 2);
        invalidate();
    }

    public void setSwipeClick(SwipeClick mSwipeClick) {
        this.mSwipeClick = mSwipeClick;
    }

    /**
     * 单击事件接口
     */
    public interface SwipeClick{
        public void swipeLayoutClickEvent();
    }

}
