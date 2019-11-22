package com.example.wowebackand.dao;

import android.os.AsyncTask;

/**
 * iyi class niyogukora operattion zose za database kuri background
 * @param <T> ni ubwoko bwiyo table
 * @param <D> ni ubwoko bwiyo dao
 */
public class DbOpsAsync<T,D> extends AsyncTask<T,Void,Void> {

    private D dao;
    private DbIpml<T,D> ipml;

    public DbOpsAsync(D dao, DbIpml ipml) {
        this.dao = dao;
        this.ipml = ipml;
    }

    @Override
    protected Void doInBackground(T... ts) {
        ipml.operation(ts[0],dao);
        return null;
    }
}
