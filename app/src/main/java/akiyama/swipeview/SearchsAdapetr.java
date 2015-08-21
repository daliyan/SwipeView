package akiyama.swipeview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import akiyama.swipe.adapter.RecyclerViewAdapter;
import akiyama.swipe.swipe.SwipeItemLayout;
import akiyama.swipe.swipe.SwipeMenuItem;

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
    public SearchsViewHolder onCreatViewHodler(View v, List<? extends View> menuViews, SwipeItemLayout swipeItemLayout, int layoutId) {
        return new SearchsViewHolder(v,menuViews,swipeItemLayout,layoutId);
    }

    @Override
    public View onCreateItemLayoutId(ViewGroup parent, int viewType) {
        return LayoutInflater.from(mContext).inflate(R.layout.item_recycker_layout,parent,false);
    }

    @Override
    public List<? extends View> creatMenuView() {
        List<Button> menuViews = new ArrayList<>();

        Button menu1=new Button(mContext);
        menu1.setBackgroundResource(R.drawable.save_btn_bg);
        menu1.setLayoutParams(new ViewGroup.LayoutParams((int) dipToPx(60), ViewGroup.LayoutParams.MATCH_PARENT));
        menu1.setCompoundDrawables(mContext.getResources().getDrawable(R.drawable.ic_camera_alt_white_24dp),null,null,null);
        menu1.setText("删除");
        menu1.setTextColor(Color.parseColor("#ffffff"));
        menu1.setTextSize(14);
        menuViews.add(menu1);

        Button menu2=new Button(mContext);
        menu2.setBackgroundResource(R.drawable.save_btn_bg);
        menu2.setLayoutParams(new ViewGroup.LayoutParams((int) dipToPx(60), ViewGroup.LayoutParams.MATCH_PARENT));
        menu2.setCompoundDrawables(mContext.getResources().getDrawable(R.drawable.ic_camera_alt_white_24dp),null,null,null);
        menu2.setText("取消");
        menu2.setTextColor(Color.parseColor("#ffffff"));
        menu2.setTextSize(14);
        menuViews.add(menu2);
        return menuViews;
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

        holder.getSwipeItemLayout().setmSwipeMenuClick(new SwipeItemLayout.SwipeMenuClick() {
            @Override
            public void swipeMenuClickEvent(int position) {
                switch (position){
                    case 0:
                        Toast.makeText(mContext,"删除事件执行",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(mContext,position+"取消事件执行",Toast.LENGTH_SHORT).show();
                        break;
                }
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
        public SearchsViewHolder(View v, List<? extends View> menuItems, SwipeItemLayout swipeItemLayout, int layoutId) {
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
