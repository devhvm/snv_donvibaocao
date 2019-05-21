package com.manager.donvibaocao.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DuLieuBaoCao entity.
 */
public class DuLieuBaoCaoDTO implements Serializable {

    private String id;

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DuLieuBaoCaoDTO duLieuBaoCaoDTO = (DuLieuBaoCaoDTO) o;
        if (duLieuBaoCaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), duLieuBaoCaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DuLieuBaoCaoDTO{" +
            "id=" + getId() +
            ", baoCaoCode='" + getBaoCaoCode() + "'" +
            ", nhomDanhMucCode='" + getNhomDanhMucCode() + "'" +
            ", danhMucCode='" + getDanhMucCode() + "'" +
            ", doiTuongCode='" + getDoiTuongCode() + "'" +
            ", donViCode='" + getDonViCode() + "'" +
            ", giaTri=" + getGiaTri() +
            "}";
    }
}
