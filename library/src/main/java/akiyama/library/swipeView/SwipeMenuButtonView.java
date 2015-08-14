package akiyama.library.swipeView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import akiyama.library.swipe.SwipeMenuItem;

/**
 * 具体菜单项目按钮
 * @author zhiwu_yan
 * @since 2015/8/13
 */
public class SwipeMenuButtonView extends Button{

    public SwipeMenuButtonView(Context context,SwipeMenuItem swipeItem){
        super(context);
        initView(swipeItem);
    }

    public SwipeMenuButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(null);
    }

    private void initView(SwipeMenuItem swipeItem){
        if(swipeItem!=null){
            setText(swipeItem.getText());
            setTextColor(swipeItem.getTitleColor());
            setBackgroundResource(swipeItem.getBackgroundResId());
            setTextSize(swipeItem.getTitleSize());
            setCompoundDrawables(swipeItem.getIcon(),null,null,null);
            setWidth(swipeItem.getWidth());
        }

    }



}
