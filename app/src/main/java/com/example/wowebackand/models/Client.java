package com.example.wowebackand.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * nongeyemo field 3
 * string clientAbout;
 * string clientLocation;
 * int rates;
 */
public class Client implements Parcelable
{
    private Integer clientId;

    //user phone number
    private String phoneNumber;


    private Integer rates;


    private String clientAbout;

    private String clientLocation;


    //username(unique 10 character string)
    private String username;


    //user password(encrypted)
    private String password;

    //user email address
    private String email;

    //user profile image
    private String profileImage;


    //user created time
    private Date createTime;
    //1:active;0:deactive;99 deleted
    private Integer status;
    //the client the service does
    private Integer serviceId;

    public Client(){

    }

    protected Client(Parcel in) {
        if (in.readByte() == 0) {
            clientId = null;
        } else {
            clientId = in.readInt();
        }
        phoneNumber = in.readString();
        if (in.readByte() == 0) {
            rates = null;
        } else {
            rates = in.readInt();
        }
        clientAbout = in.readString();
        clientLocation = in.readString();
        username = in.readString();
        password = in.readString();
        email = in.readString();
        profileImage = in.readString();
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        if (in.readByte() == 0) {
            serviceId = null;
        } else {
            serviceId = in.readInt();
        }
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    public Integer getRates() {
        return rates;
    }

    public void setRates(Integer rates) {
        this.rates = rates;
    }

    public String getClientAbout() {
        return clientAbout;
    }

    public void setClientAbout(String clientAbout) {
        this.clientAbout = clientAbout;
    }

    public String getClientLocation() {
        return clientLocation;
    }

    public void setClientLocation(String clientLocation) {
        this.clientLocation = clientLocation;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
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
        dest.writeString(phoneNumber);
        if (rates == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(rates);
        }
        dest.writeString(clientAbout);
        dest.writeString(clientLocation);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(email);
        dest.writeString(profileImage);
        if (status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(status);
        }
        if (serviceId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(serviceId);
        }
    }
}
