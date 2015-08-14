package akiyama.swipeview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import akiyama.library.adapter.RecyclerViewAdapter;
import akiyama.library.swipe.SwipeItemLayout;
import akiyama.library.swipe.SwipeMenuItem;

/**
 * FIXME
 *
 * @author zhiwu_yan
 * @version 1.0
 * @since 2015-08-14  12:35
 */
public class SearchsAdapetr extends RecyclerViewAdapter<SearchsAdapetr.SearchsViewHolder>{

    private List<SearchVo> mSearchVoList;
    private Context mContext;
    public SearchsAdapetr(Context context, List<SearchVo> searchVoList){
        this.mContext=context;
        this.mSearchVoList=searchVoList;
    }

    @Override
    public SearchsViewHolder onCreatViewHodler(View v, List<SwipeMenuItem> menuItems, SwipeItemLayout swipeItemLayout, int layoutId) {
        return new SearchsViewHolder(v,menuItems,swipeItemLayout,layoutId);
    }

    @Override
    public View onCreateItemLayoutId(ViewGroup parent, int viewType) {
        return LayoutInflater.from(mContext).inflate(R.layout.item_recycker_layout,parent,false);
    }

    @Override
    public List<SwipeMenuItem> creatMenuView() {
        List<SwipeMenuItem> menuItems = new ArrayList<>();
        menuItems =new ArrayList<>();

        SwipeMenuItem a=new SwipeMenuItem();
        a.setBackgroundResId(R.drawable.save_btn_bg);
        a.setWidth((int) dipToPx(60));
        a.setIcon(null);
        a.setText("删除");
        a.setTitleColor(Color.parseColor("#ffffff"));
        a.setTitleSize(14);
        menuItems.add(a);

        SwipeMenuItem b=new SwipeMenuItem();
        b.setBackgroundResId(R.drawable.save_btn_bg);
        b.setWidth((int) dipToPx(80));
        b.setIcon(null);
        b.setText("取消");
        b.setTitleColor(Color.parseColor("#ffffff"));
        b.setTitleSize(14);
        menuItems.add(b);
        return menuItems;
    }

    @Override
    public int onCreatLayout() {
        return R.layout.item_search_resultl;
    }

    @Override
    public SwipeItemLayout onCreatSwipeLayout(View v) {
        return (SwipeItemLayout)v.findViewById(R.id.test) ;
    }

    @Override
    public void onBindViewHolder(SearchsAdapetr.SearchsViewHolder holder, final int position) {
        if(mSearchVoList!=null){
            holder.mTitleTv.setText(mSearchVoList.get(position).getName());
            holder.mSelectLabelCb.setChecked(mSearchVoList.get(position).getIsCheck());
        }

        /**
         * 设置单击事件，需要用自定义的单击事件来实现，否则由于滑动冲突会导致单击失效
         */
        holder.getSwipeItemLayout().setSwipeClick(new SwipeItemLayout.SwipeClick() {
            @Override
            public void swipeLayoutClickEvent() {
                Toast.makeText(mContext,"您单击了"+position+"行",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSearchVoList.size();
    }

    public static class SearchsViewHolder extends RecyclerViewAdapter.ViewHolder{
        public TextView mTitleTv;
        public CheckBox mSelectLabelCb;
        public SearchsViewHolder(View v, List<SwipeMenuItem> menuItems, SwipeItemLayout swipeItemLayout, int layoutId) {
            super(v, menuItems, swipeItemLayout, layoutId);
            mTitleTv = (TextView) findSwipeViewById(R.id.label_name_tv);
            mSelectLabelCb = (CheckBox) findSwipeViewById(R.id.select_label_cb);
        }
    }

    public static float dipToPx(float dip){
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return  (dip * scale + 0.5f);
    }
}
