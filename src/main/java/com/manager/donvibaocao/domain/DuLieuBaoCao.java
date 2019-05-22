package com.manager.donvibaocao.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DuLieuBaoCao.
 */
//@Document(collection = "du_lieu_bao_cao")
public class DuLieuBaoCao implements Serializable {

    private static final long serialVersionUID = 1L;
    
//    @Id
//    private String id;

    @NotNull
    @Field("bao_cao_code")
    private String baoCaoCode;

    @NotNull
    @Field("nhom_danh_muc_code")
    private String nhomDanhMucCode;

    @NotNull
    @Field("danh_muc_code")
    private String danhMucCode;

    @NotNull
    @Field("doi_tuong_code")
    private String doiTuongCode;

    @NotNull
    @Field("don_vi_code")
    private String donViCode;

    @NotNull
    @Field("gia_tri")
    private Integer giaTri;

//    @DBRef
//    @Field("baocao")
//    @JsonIgnoreProperties("dulieubaocaos")
//    private BaoCao baocao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getBaoCaoCode() {
        return baoCaoCode;
    }

    public DuLieuBaoCao baoCaoCode(String baoCaoCode) {
        this.baoCaoCode = baoCaoCode;
        return this;
    }

    public void setBaoCaoCode(String baoCaoCode) {
        this.baoCaoCode = baoCaoCode;
    }

    public String getNhomDanhMucCode() {
        return nhomDanhMucCode;
    }

    public DuLieuBaoCao nhomDanhMucCode(String nhomDanhMucCode) {
        this.nhomDanhMucCode = nhomDanhMucCode;
        return this;
    }

    public void setNhomDanhMucCode(String nhomDanhMucCode) {
        this.nhomDanhMucCode = nhomDanhMucCode;
    }

    public String getDanhMucCode() {
        return danhMucCode;
    }

    public DuLieuBaoCao danhMucCode(String danhMucCode) {
        this.danhMucCode = danhMucCode;
        return this;
    }

    public void setDanhMucCode(String danhMucCode) {
        this.danhMucCode = danhMucCode;
    }

    public String getDoiTuongCode() {
        return doiTuongCode;
    }

    public DuLieuBaoCao doiTuongCode(String doiTuongCode) {
        this.doiTuongCode = doiTuongCode;
        return this;
    }

    public void setDoiTuongCode(String doiTuongCode) {
        this.doiTuongCode = doiTuongCode;
    }

    public String getDonViCode() {
        return donViCode;
    }

    public DuLieuBaoCao donViCode(String donViCode) {
        this.donViCode = donViCode;
        return this;
    }

    public void setDonViCode(String donViCode) {
        this.donViCode = donViCode;
    }

    public Integer getGiaTri() {
        return giaTri;
    }

    public DuLieuBaoCao giaTri(Integer giaTri) {
        this.giaTri = giaTri;
        return this;
    }

    public void setGiaTri(Integer giaTri) {
        this.giaTri = giaTri;
    }

//    public BaoCao getBaocao() {
//        return baocao;
//    }
//
//    public DuLieuBaoCao baocao(BaoCao baoCao) {
//        this.baocao = baoCao;
//        return this;
//    }
//
//    public void setBaocao(BaoCao baoCao) {
//        this.baocao = baoCao;
//    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


}
