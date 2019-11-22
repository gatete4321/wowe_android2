package com.example.wowebackand.models;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Appoitement implements Parcelable
{
    @PrimaryKey
    private Integer appoitementId;
    private Integer clientId;
    private Integer techId;
    private Date createTime;
    private Date doneTime;
    private String feedBack;
    private Integer status;
    private Integer rate;
    private String plaque;
    private String description;


    private Integer serviceId;
    private Integer callNumber;

    public Appoitement(Integer appoitementId,Integer clientId, Integer techId, Date createTime, Date doneTime, String feedBack, Integer status, Integer rate, String plaque, String description, Integer serviceId, Integer callNumber) {
        this.appoitementId=appoitementId;
        this.clientId = clientId;
        this.techId = techId;
        this.createTime = createTime;
        this.doneTime = doneTime;
        this.feedBack = feedBack;
        this.status = status;
        this.rate = rate;
        this.plaque = plaque;
        this.description = description;
        this.serviceId = serviceId;
        this.callNumber = callNumber;
    }

    @Ignore
    public Appoitement() {
    }
@Ignore
    protected Appoitement(Parcel in) {

    if (in.readByte() == 0) {
        appoitementId= null;
    } else {
        appoitementId = in.readInt();
    }

        if (in.readByte() == 0) {
            clientId = null;
        } else {
            clientId = in.readInt();
        }
        if (in.readByte() == 0) {
            techId = null;
        } else {
            techId = in.readInt();
        }
        feedBack = in.readString();
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        if (in.readByte() == 0) {
            rate = null;
        } else {
            rate = in.readInt();
        }
        plaque = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            serviceId = null;
        } else {
            serviceId = in.readInt();
        }
        if (in.readByte() == 0) {
            callNumber = null;
        } else {
            callNumber = in.readInt();
        }
    }

    @Ignore
    public static final Creator<Appoitement> CREATOR = new Creator<Appoitement>() {
        @Override
        public Appoitement createFromParcel(Parcel in) {
            return new Appoitement(in);
        }

        @Override
        public Appoitement[] newArray(int size) {
            return new Appoitement[size];
        }
    };

    public Integer getAppoitementId() {
        return appoitementId;
    }

    public void setAppoitementId(Integer appoitementId) {
        this.appoitementId = appoitementId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getTechId() {
        return techId;
    }

    public void setTechId(Integer techId) {
        this.techId = techId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(Date doneTime) {
        this.doneTime = doneTime;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(Integer callNumber) {
        this.callNumber = callNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (clientId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(clientId);
        }
        if (techId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(techId);
        }
        dest.writeString(feedBack);
        if (status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(status);
        }
        if (rate == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(rate);
        }
        dest.writeString(plaque);
        dest.writeString(description);
        if (serviceId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(serviceId);
        }
        if (callNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(callNumber);
        }
    }
}
