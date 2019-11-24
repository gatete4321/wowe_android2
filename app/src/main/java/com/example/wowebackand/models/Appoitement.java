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
    private String techName;

    public Appoitement(Integer appoitementId, Integer clientId, Integer techId, Date createTime, Date doneTime, String feedBack, Integer status, Integer rate, String plaque, String description, Integer serviceId, String techName) {
        this.appoitementId = appoitementId;
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
        this.techName = techName;
    }

    @Ignore
    public Appoitement() {
    }


    @Ignore
    protected Appoitement(Parcel in) {
        if (in.readByte() == 0) {
            appoitementId = null;
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
        techName = in.readString();
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

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }


    @Ignore
    @Override
    public int describeContents() {
        return 0;
    }

    @Ignore
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (appoitementId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(appoitementId);
        }
        if (clientId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(clientId);
        }
        if (techId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(techId);
        }
        parcel.writeString(feedBack);
        if (status == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(status);
        }
        if (rate == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(rate);
        }
        parcel.writeString(plaque);
        parcel.writeString(description);
        if (serviceId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(serviceId);
        }
        parcel.writeString(techName);
    }
}
