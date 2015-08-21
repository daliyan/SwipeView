package akiyama.swipe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import akiyama.swipe.swipe.SwipeItemLayout;
import akiyama.swipe.swipe.SwipeMenuItem;

/**
 * RecyclerView 适配器
 * @author zhiwu_yan
 * @version 1.0
 * @since 2015-07-29  16:21
 */
public abstract class RecyclerViewAdapter<VH extends RecyclerViewAdapter.ViewHolder> extends RecyclerView.Adapter<VH> {

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = onCreateItemLayoutId(parent,viewType);
        VH vh = onCreatViewHodler(v,creatMenuView(),onCreatSwipeLayout(v),onCreatLayout());
        return vh;

    }

    /**
     * 创建布局ViewHolder
     * @param v
     * @param menuViews
     * @param swipeItemLayout
     * @param layoutId
     * @return
     */
    public abstract VH onCreatViewHodler(View v,List<? extends View> menuViews,SwipeItemLayout swipeItemLayout,int layoutId);

    /**
     * 创建的ViewHolder对应的View,最底层的布局文件
     * @param parent
     * @param viewType
     * @return 需要创建的View
     */
    public abstract View onCreateItemLayoutId(ViewGroup parent, int viewType);

    /**
     * 创建侧滑菜单
     * @return 菜单项目的SwipeMenuItem列表
     */
    public abstract List<? extends View> creatMenuView();

    /**
     * 你附加的布局文件，最外层布局文件
     * @return 布局文件的ID
     */
    public abstract int onCreatLayout();

    /**
     * 创建侧滑布局，SwipeItemLayout布局文件，次一层布局文件
     * @return
     */
    public abstract SwipeItemLayout onCreatSwipeLayout(View v);


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private SwipeItemLayout mSwipeItemLayout;
        public ViewHolder(View v,List<? extends View> menuItems,SwipeItemLayout swipeItemLayout,int layoutId) {
            super(v);
            this.mView=v;
            mSwipeItemLayout = swipeItemLayout;
            mSwipeItemLayout.setLayoutAndMenu(layoutId,menuItems);//添加附加布局和菜单项
        }

        public View getView() {
            return mView;
        }

        public SwipeItemLayout getSwipeItemLayout(){
            return mSwipeItemLayout;
        }

        /**
         * 更具ID获取对应的数据
         * @param id
         * @return
         */
        public View findSwipeViewById(int id){
            return mSwipeItemLayout.findViewById(id);
        }
    }

}
