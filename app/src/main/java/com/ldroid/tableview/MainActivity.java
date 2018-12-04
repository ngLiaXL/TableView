package com.ldroid.tableview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ldroid.tableview.model.Cell;
import com.ldroid.tableview.model.ColumnHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TableView<Goods> tableView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableView = findViewById(R.id.tableView);
        tableView.setTableViewModel(new GoodsModel());


        tableView.setCellList(new ArrayList<Goods>() {
            {
                for (int i = 0; i < 20; i++) {
                    add(new Goods("测试商品" + (i + 1), "69" + new Random().nextInt(100000), "个", "12.56", "1", "中", "白色", "15", "无"));
                }
            }
        });
    }

    public class GoodsModel extends TableViewModel<Goods> {

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

        public int getRowSize() {
            return mListData != null ? mListData.size() : 0;
        }


    }

    static class Goods {
        public String name;
        public String sku;
        public String unit;
        public String price;
        public String count;
        public String model;
        public String color;
        public String size;
        public String taste;

        public Goods(String name, String sku, String unit, String price, String count, String model, String color, String size, String taste) {
            this.name = name;
            this.sku = sku;
            this.unit = unit;
            this.price = price;
            this.count = count;
            this.model = model;
            this.color = color;
            this.size = size;
            this.taste = taste;
        }
    }

}
