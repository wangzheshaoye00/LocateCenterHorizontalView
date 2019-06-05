package com.bocweb.fly.locatecenterhorizontalview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bocweb.fly.locatecenterhorizontalview.view.LocateCenterHorizontalView;


import java.util.List;

/**
 * Created by fly on 2018/4/4.
 */

public class IndexZhouTextAdapter extends RecyclerView.Adapter<IndexZhouTextAdapter.RankHistoryHolder>
        implements LocateCenterHorizontalView.IAutoLocateHorizontalView {
    private Context mContext;
    private List<RankingHistoryEntity> mDatas;
    private final int MAX_STEP = 18000;
    private int mRootViewHeight;

    public IndexZhouTextAdapter(Context context, List<RankingHistoryEntity> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    public IndexZhouTextAdapter setRecyclerViewHeight(int rootViewHeight) {
        this.mRootViewHeight = rootViewHeight;
        return this;
    }

    @Override
    public RankHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_text_zhou_index, parent, false);
        return new RankHistoryHolder(mView);
    }

    @Override
    public void onBindViewHolder(RankHistoryHolder holder, final int position) {
        RankingHistoryEntity item = getData().get(position);
        holder.tvDate.setText(TextUtils.isEmpty(item.dateStr) ? "暂无" : item.dateStr);
        ViewGroup.LayoutParams layoutParams = holder.ivStep.getLayoutParams();
        int maxIvHeight = mRootViewHeight - Utils.dp2px(holder.itemView.getContext(), 40);
        if (item.stepCount == null) {
            if (layoutParams != null) {
                layoutParams.height = 0;
                holder.ivStep.setLayoutParams(layoutParams);
            }
        } else {
            if (layoutParams != null) {
                if (item.stepCount <= 0) {
                    layoutParams.height = 0;
                } else if (item.stepCount < MAX_STEP) {
                    layoutParams.height = (int) (maxIvHeight * item.stepCount * 1.0 / MAX_STEP);
                } else {
                    layoutParams.height = maxIvHeight;
                }
                holder.ivStep.setLayoutParams(layoutParams);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onItemClickListner) {
                    if (null != mDatas) {
                        //解决列表为list  底部为自定义bottom条目添加问题呢
                        if (position != mDatas.size()) {
                            onItemClickListner.onItemClickListner(v, mDatas.get(position), position);
                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mDatas == null) {
            return 0;
        }
        return mDatas.size();
    }

    public List<RankingHistoryEntity> getData() {
        return mDatas;
    }

    @Override
    public void onViewSelected(boolean isSelected, int pos, RecyclerView.ViewHolder holder, int itemWidth) {
        if (isSelected) {
            ((RankHistoryHolder) holder).setSelectedItem(isSelected);
        } else {
            ((RankHistoryHolder) holder).setSelectedItem(isSelected);
        }
    }

    class RankHistoryHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        ImageView ivStep;

        RankHistoryHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            ivStep = itemView.findViewById(R.id.iv_small_arrow);
        }

        public void setSelectedItem(boolean isSelected) {
            ivStep.setSelected(isSelected);
        }

    }

    private OnItemClickListner onItemClickListner;//单击事件

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public interface OnItemClickListner {
        void onItemClickListner(View v, RankingHistoryEntity data, int position);
    }

}
