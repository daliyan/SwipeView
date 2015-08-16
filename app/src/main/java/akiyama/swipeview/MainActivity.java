package akiyama.swipeview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import akiyama.swipe.swipeView.SwipeRecyclerView;


public class MainActivity extends ActionBarActivity {

    private SwipeRecyclerView mSwipeRecyclerView;
    private SearchsAdapetr mAdapter=null;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<SearchVo> mSerachVos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwipeRecyclerView = (SwipeRecyclerView) findViewById(R.id.swipeView);
        mLayoutManager = new LinearLayoutManager(this);
        mSerachVos = new ArrayList<>();
        for(int i=0;i<10;i++){
            mSerachVos.add(new SearchVo("test"+i,false));
        }

        mAdapter = new SearchsAdapetr(this,mSerachVos);
        mSwipeRecyclerView.setHasFixedSize(true);
        mSwipeRecyclerView.setLayoutManager(mLayoutManager);
        mSwipeRecyclerView.setAdapter(mAdapter);
        mSwipeRecyclerView.setScrollContainer(false);

    }
}
