package com.bocweb.fly.locatecenterhorizontalview;

import java.io.Serializable;

/**
 * @author wangshaobo
 * @date 2019.05.29
 */
public class RankingHistoryEntity implements Serializable {
    public Integer stepCount;
    public String dateStr;

    public RankingHistoryEntity(Integer stepCount, String dateStr) {
        this.stepCount = stepCount;
        this.dateStr = dateStr;
    }

}
