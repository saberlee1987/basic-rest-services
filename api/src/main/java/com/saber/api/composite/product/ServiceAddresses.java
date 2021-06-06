package com.saber.api.composite.product;

import java.util.Objects;

public class ServiceAddresses {
    private final String cmp;
    private final String pro;
    private final String rev;
    private final String rec;

    public ServiceAddresses(){
        this.cmp=null;
        this.pro=null;
        this.rev=null;
        this.rec=null;
    }

    public ServiceAddresses(String cmp, String pro, String rev, String rec) {
        this.cmp = cmp;
        this.pro = pro;
        this.rev = rev;
        this.rec = rec;
    }

    public String getCmp() {
        return cmp;
    }

    public String getPro() {
        return pro;
    }

    public String getRev() {
        return rev;
    }

    public String getRec() {
        return rec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceAddresses that = (ServiceAddresses) o;
        return Objects.equals(cmp, that.cmp) &&
                Objects.equals(pro, that.pro) &&
                Objects.equals(rev, that.rev) &&
                Objects.equals(rec, that.rec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cmp, pro, rev, rec);
    }

    @Override
    public String toString() {
        return "ServiceAddresses{" +
                "cmp='" + cmp + '\'' +
                ", pro='" + pro + '\'' +
                ", rev='" + rev + '\'' +
                ", rec='" + rec + '\'' +
                '}';
    }
}
