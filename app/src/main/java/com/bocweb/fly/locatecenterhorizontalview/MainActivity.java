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
    List<ContinentModel> list = new ArrayList<>();
    ;
    IndexZhouTextAdapter zhouTextAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        zhouText = findViewById(R.id.zhouText);
        initZhouText();
        addData();
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


    private void addData() {
        int size = list.size();
        for (int i = size; i < size + 10; i++) {
            list.add(new ContinentModel(size, "第" + (i + 1) + "天", i + 1 + ""));
        }
        zhouTextAdapter.notifyDataSetChanged();
    }

    public void btClick(View view) {
        addData();
    }
}
