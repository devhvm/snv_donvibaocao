package com.manager.donvibaocao.domain;


import com.manager.donvibaocao.domain.enumeration.Status;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A TienTrinhBaoCao.
 */
//@Document(collection = "tien_trinh_bao_cao")
public class TienTrinhBaoCao implements Serializable {

    private static final long serialVersionUID = 1L;
    
//    @Id
//    private String id;

    @NotNull
    @Field("tien_trinh_code")
    private String tienTrinhCode;

    @NotNull
    @Field("du_lieu_code")
    private String duLieuCode;

    @NotNull
    @Field("name")
    private String name;

    @NotNull
    @Field("from_user_id")
    private String fromUserId;

    @NotNull
    @Field("to_user_id")
    private String toUserId;

    @NotNull
    @Field("note")
    private String note;

    @NotNull
    @Field("quy_trinh_don_vi_id")
    private Long quyTrinhDonViId;

    @NotNull
    @Field("quy_trinh_don_vi_name")
    private String quyTrinhDonViName;

    @NotNull
    @Field("status")
    private Status status;

//    @DBRef
//    @Field("baocao")
//    @JsonIgnoreProperties("tientrinhbaocaos")
//    private BaoCao baocao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getTienTrinhCode() {
        return tienTrinhCode;
    }

    public TienTrinhBaoCao tienTrinhCode(String tienTrinhCode) {
        this.tienTrinhCode = tienTrinhCode;
        return this;
    }

    public void setTienTrinhCode(String tienTrinhCode) {
        this.tienTrinhCode = tienTrinhCode;
    }

    public String getDuLieuCode() {
        return duLieuCode;
    }

    public TienTrinhBaoCao duLieuCode(String duLieuCode) {
        this.duLieuCode = duLieuCode;
        return this;
    }

    public void setDuLieuCode(String duLieuCode) {
        this.duLieuCode = duLieuCode;
    }

    public String getName() {
        return name;
    }

    public TienTrinhBaoCao name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public TienTrinhBaoCao fromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
        return this;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public TienTrinhBaoCao toUserId(String toUserId) {
        this.toUserId = toUserId;
        return this;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getNote() {
        return note;
    }

    public TienTrinhBaoCao note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getQuyTrinhDonViId() {
        return quyTrinhDonViId;
    }

    public TienTrinhBaoCao quyTrinhDonViId(Long quyTrinhDonViId) {
        this.quyTrinhDonViId = quyTrinhDonViId;
        return this;
    }

    public void setQuyTrinhDonViId(Long quyTrinhDonViId) {
        this.quyTrinhDonViId = quyTrinhDonViId;
    }

    public String getQuyTrinhDonViName() {
        return quyTrinhDonViName;
    }

    public TienTrinhBaoCao quyTrinhDonViName(String quyTrinhDonViName) {
        this.quyTrinhDonViName = quyTrinhDonViName;
        return this;
    }

    public void setQuyTrinhDonViName(String quyTrinhDonViName) {
        this.quyTrinhDonViName = quyTrinhDonViName;
    }

    public Status getStatus() {
        return status;
    }

    public TienTrinhBaoCao status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

//    public BaoCao getBaocao() {
//        return baocao;
//    }
//
//    public TienTrinhBaoCao baocao(BaoCao baoCao) {
//        this.baocao = baoCao;
//        return this;
//    }
//
//    public void setBaocao(BaoCao baoCao) {
//        this.baocao = baoCao;
//    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


}
