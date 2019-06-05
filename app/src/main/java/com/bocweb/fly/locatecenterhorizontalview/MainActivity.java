package com.bocweb.fly.locatecenterhorizontalview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.bocweb.fly.locatecenterhorizontalview.view.LocateCenterHorizontalView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fly on 2018/4/4.
 */

public class MainActivity extends AppCompatActivity {
    LocateCenterHorizontalView zhouText;
    List<RankingHistoryEntity> list = new ArrayList<>();

    IndexZhouTextAdapter zhouTextAdapter;
    private int mScreenWidth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        zhouText = findViewById(R.id.zhouText);
        initZhouText();
        addData();
    }

    private void initZhouText() {
        mScreenWidth = Utils.getWindowWidth(this);
        zhouText.setHasFixedSize(true);
//        int recyclerViewHeight = Utils.dp2px(this, 300);
        int recyclerViewHeight = mScreenWidth * 638 / 1080;
        ViewGroup.LayoutParams layoutParams = zhouText.getLayoutParams();
        layoutParams.height = recyclerViewHeight;
        zhouText.setLayoutParams(layoutParams);

        zhouText.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));

        zhouTextAdapter = new IndexZhouTextAdapter(this, list).setRecyclerViewHeight(recyclerViewHeight);

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
            public void onItemClickListner(View v, RankingHistoryEntity data, int position) {
                zhouText.moveToPosition(position);
            }
        });

    }


    private void addData() {
        int size = list.size();
        for (int i = size; i < size + 10; i++) {
            int stepCount = (i + 1) * 1000;
            if (stepCount > 18000) {
                stepCount = (int) (18000 * Math.random());
            }
            list.add(new RankingHistoryEntity(stepCount, "第" + (i + 1) + "天"));
        }
        zhouTextAdapter.notifyDataSetChanged();
    }

    public void btClick(View view) {
        addData();
    }
}
