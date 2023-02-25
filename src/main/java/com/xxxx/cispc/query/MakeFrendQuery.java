package com.xxxx.cispc.query;


import com.xxxx.cispc.base.BaseQuery;


public class MakeFrendQuery extends BaseQuery {
        private String createMan;
        private String activityName;
        private Integer maxNumbers;

        public String getCreateMan() {
                return createMan;
        }

        public void setCreateMan(String createMan) {
                this.createMan = createMan;
        }

        public String getActivityName() {
                return activityName;
        }

        public void setActivityName(String activityName) {
                this.activityName = activityName;
        }

        public Integer getMaxNumbers() {
                return maxNumbers;
        }

        public void setMaxNumbers(Integer maxNumbers) {
                this.maxNumbers = maxNumbers;
        }
}
