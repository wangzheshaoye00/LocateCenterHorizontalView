package com.bocweb.fly.locatecenterhorizontalview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bocweb.fly.locatecenterhorizontalview.view.LocateCenterHorizontalView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fly on 2018/4/4.
 */

public class MainActivity extends AppCompatActivity {
    LocateCenterHorizontalView zhouText;
    List<ContinentModel> list = new ArrayList<>();;
    IndexZhouTextAdapter zhouTextAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        zhouText = findViewById(R.id.zhouText);
        initZhouText();
        initData();
    }

    private void initZhouText() {
        zhouText.setHasFixedSize(true);
        zhouText.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        zhouTextAdapter = new IndexZhouTextAdapter(this, list);
        zhouText.setAdapter(zhouTextAdapter);
        zhouText.setOnSelectedPositionChangedListener(new LocateCenterHorizontalView.OnSelectedPositionChangedListener() {
            @Override
            public void selectedPositionChanged(int pos) {
                if (zhouTextAdapter.getData().size() > 0) {
                    int i = pos % zhouTextAdapter.getData().size();
                }
            }
        });
        zhouTextAdapter.setOnItemClickListner(new IndexZhouTextAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, ContinentModel data, int position) {
                zhouText.moveToPosition(position);
            }
        });

    }


    private void initData() {
        list.add(new ContinentModel(0, "第一天", "1"));
        list.add(new ContinentModel(1, "第二天", "2"));
        list.add(new ContinentModel(2, "第三天", "3"));
        list.add(new ContinentModel(3, "第四天", "4"));
        list.add(new ContinentModel(4, "第五天", "5"));
        list.add(new ContinentModel(5, "第六天", "6"));
        list.add(new ContinentModel(6, "第七天", "7"));
        list.add(new ContinentModel(7, "第八天", "8"));
        list.add(new ContinentModel(8, "第九天", "9"));
        list.add(new ContinentModel(9, "第十天", "10"));
        zhouTextAdapter.notifyDataSetChanged();
    }
}
