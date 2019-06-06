package com.manager.donvibaocao.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the DuLieuBaoCao entity.
 */
public class DuLieuBaoCaoDTO implements Serializable {


    @NotNull
    private String baoCaoCode;

    @NotNull
    private String nhomDanhMucCode;

    @NotNull
    private String danhMucCode;

    @NotNull
    private String doiTuongCode;

    @NotNull
    private String donViCode;

    @NotNull
    private Integer giaTri;

    public String getBaoCaoCode() {
        return baoCaoCode;
    }

    public void setBaoCaoCode(String baoCaoCode) {
        this.baoCaoCode = baoCaoCode;
    }

    public String getNhomDanhMucCode() {
        return nhomDanhMucCode;
    }

    public void setNhomDanhMucCode(String nhomDanhMucCode) {
        this.nhomDanhMucCode = nhomDanhMucCode;
    }

    public String getDanhMucCode() {
        return danhMucCode;
    }

    public void setDanhMucCode(String danhMucCode) {
        this.danhMucCode = danhMucCode;
    }

    public String getDoiTuongCode() {
        return doiTuongCode;
    }

    public void setDoiTuongCode(String doiTuongCode) {
        this.doiTuongCode = doiTuongCode;
    }

    public String getDonViCode() {
        return donViCode;
    }

    public void setDonViCode(String donViCode) {
        this.donViCode = donViCode;
    }

    public Integer getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(Integer giaTri) {
        this.giaTri = giaTri;
    }

}
