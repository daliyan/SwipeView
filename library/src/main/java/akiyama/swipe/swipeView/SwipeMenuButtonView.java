package akiyama.swipe.swipeView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import akiyama.swipe.swipe.SwipeMenuItem;

/**
 * 具体菜单项目按钮,现在不使用该方法，因为不能灵活的定义菜单，<br/><b/><i/>该类直接放弃使用,直接使用View来替代。
 * @see akiyama.swipe.swipe.SwipeItemLayout
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

            setTextColor(swipeItem.getTitleColor());
            setBackgroundResource(swipeItem.getBackgroundResId());
            setTextSize(swipeItem.getTitleSize());
            setCompoundDrawables(swipeItem.getIcon(),null,null,null);
            setWidth(swipeItem.getWidth());

            if(swipeItem.getText()!=null){
                setText(swipeItem.getText());
            }

        }

    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }
}
