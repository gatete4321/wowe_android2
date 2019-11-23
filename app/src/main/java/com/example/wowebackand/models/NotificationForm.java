package com.example.wowebackand.models;

public class NotificationForm extends Notification {
    public Integer getRecentNotificationId() {
        return recentNotificationId;
    }

    public void setRecentNotificationId(Integer recentNotificationId) {
        this.recentNotificationId = recentNotificationId;
    }

    /**
     * iyi field recentNotificationId yongeyemo kugirango nze kwa updatinga iyo notification kugirango itazajya ihindagurika cyane
     */
    private Integer recentNotificationId;
}
