package com.example.wowebackand.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Service implements Parcelable
{
    private int serviceId;

    private String serviceName;

    private int serviceImageId;

    private String linkImage;

    public Service() {

    }

    public Service(int serviceId, String serviceName, int serviceImageId, String linkImage) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceImageId = serviceImageId;
        this.linkImage = linkImage;
    }

    protected Service(Parcel in) {
        serviceId = in.readInt();
        serviceName = in.readString();
        serviceImageId = in.readInt();
        linkImage = in.readString();
    }

    public static final Creator<Service> CREATOR = new Creator<Service>() {
        @Override
        public Service createFromParcel(Parcel in) {
            return new Service(in);
        }

        @Override
        public Service[] newArray(int size) {
            return new Service[size];
        }
    };

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getServiceImageId() {
        return serviceImageId;
    }

    public void setServiceImageId(int serviceImageId) {
        this.serviceImageId = serviceImageId;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(serviceId);
        dest.writeString(serviceName);
        dest.writeInt(serviceImageId);
        dest.writeString(linkImage);
    }
}
