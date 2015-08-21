package akiyama.swipe.swipe;

import android.graphics.drawable.Drawable;

/**
 * 该类直接放弃使用,直接使用View来替代。
 * @see akiyama.swipe.swipeView.SwipeMenuButtonView
 * @author zhiwu_yan
 * @since 2015/8/13
 */
 public class SwipeMenuItem {

    private int id;
    private String text;
    private Drawable icon;
    private int backgroundResId;
    private int titleColor;
    private int titleSize;
    private int width;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public int getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(int titleSize) {
        this.titleSize = titleSize;
    }

    public int getBackgroundResId() {
        return backgroundResId;
    }

    public void setBackgroundResId(int backgroundResId) {
        this.backgroundResId = backgroundResId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
