# SwipeView
具有侧滑菜单的列表类型View,目前匹配了RecyclerView,后续将陆续适配ListView、GridView等控件。

###使用方法：
- 自定义继承RecyclerViewAdapter对应控件适配器 
- 实现对应抽象方法：

  

```
  public abstract VH onCreatViewHodler(View v,List<SwipeMenuItem> menuItems,SwipeItemLayout swipeItemLayout,int layoutId);
    public abstract View onCreateItemLayoutId(ViewGroup parent, int viewType);
    public abstract List<SwipeMenuItem> creatMenuView(); 
    public abstract int onCreatLayout();
    public abstract SwipeItemLayout onCreatSwipeLayout(View v);
```
 -  在Activity中调用：

```
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
```