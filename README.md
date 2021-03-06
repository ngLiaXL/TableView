# TableView
TableView for Android

横向纵向滚动的ListView使用非常简单

![image](https://github.com/ngLiaXL/TableView/blob/master/TableView.gif)   



## 1、布局文件增加TableView

~~~
<com.ldroid.tableview.TableView
        android:id="@+id/tableView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
~~~

        
        
        
        
   
## 2、自定义ViewModel继承TableViewModel重写如下两个方法


~~~
 @Override
        public List<ColumnHeader> getColumnHeaderList() {
            if (mColumnHeaderList == null) {
                mColumnHeaderList = new ArrayList<ColumnHeader>() {
                    {
                        add(new ColumnHeader("0", "行号", 50));
                        add(new ColumnHeader("1", "商品名称", 90));
                        add(new ColumnHeader("2", "商品条码", 90));
                        add(new ColumnHeader("3", "单位", 90));
                        add(new ColumnHeader("4", "价格", 90));
                        add(new ColumnHeader("5", "数量", 80));
                        add(new ColumnHeader("6", "型号", 80));
                        add(new ColumnHeader("7", "颜色", 90));
                        add(new ColumnHeader("8", "尺码", 90));
                        add(new ColumnHeader("9", "味道", 90));

                    }
                };
            }
            return mColumnHeaderList;
        }

        @Override
        public List<Cell> convert(final Goods goods) {
            resetIndex();
            mListData.add(goods);
            return new ArrayList<Cell>() {
                {
                    add(new Cell("0", String.valueOf(getRowSize()), getColumnWidth()));// row header
                    add(new Cell("1", goods.name, getColumnWidth()));
                    add(new Cell("2", goods.sku, getColumnWidth()));
                    add(new Cell("3", goods.unit, getColumnWidth()));
                    add(new Cell("4", goods.price, getColumnWidth()));
                    add(new Cell("5", goods.count, getColumnWidth()));
                    add(new Cell("6", goods.model, getColumnWidth()));
                    add(new Cell("7", goods.color, getColumnWidth()));
                    add(new Cell("8", goods.size, getColumnWidth()));
                    add(new Cell("9", goods.taste, getColumnWidth()));

                }
            };
        }

~~~


## 3、可自定义每一个cell宽度
~~~
new ColumnHeader("0", "行号", 50)
~~~

## 4、添加数据
~~~
        tableView = findViewById(R.id.tableView);
        tableView.setTableViewModel(new GoodsModel());


        tableView.setCellList(new ArrayList<Goods>() {
            {
                for (int i = 0; i < 20; i++) {
                    add(new Goods("测试商品" + (i + 1), "69" + new Random().nextInt(100000), "个", "12.56", "1", "中", "白色", "15", "无"));
                }
            }
        });
~~~
