package com.manager.donvibaocao.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.manager.donvibaocao.domain.enumeration.Status;

/**
 * A DTO for the TienTrinhBaoCao entity.
 */
public class TienTrinhBaoCaoDTO implements Serializable {


    @NotNull
    private String tienTrinhCode;

    @NotNull
    private String duLieuCode;

    @NotNull
    private String name;

    @NotNull
    private String fromUserId;

    @NotNull
    private String toUserId;

    @NotNull
    private String note;

    @NotNull
    private Long quyTrinhDonViId;

    @NotNull
    private String quyTrinhDonViName;

    @NotNull
    private Status status;

    public TienTrinhBaoCaoDTO() {
    }

    public TienTrinhBaoCaoDTO(@NotNull String name, @NotNull String tienTrinhCode, @NotNull String duLieuCode, @NotNull String fromUserId, @NotNull String toUserId, String note, Long quyTrinhDonViId, String quyTrinhDonViName, @NotNull Status status) {
        this.name = name;
        this.tienTrinhCode = tienTrinhCode;
        this.duLieuCode = duLieuCode;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.note = note;
        this.quyTrinhDonViId = quyTrinhDonViId;
        this.quyTrinhDonViName = quyTrinhDonViName;
        this.status = status;
    }

    public String getTienTrinhCode() {
        return tienTrinhCode;
    }

    public void setTienTrinhCode(String tienTrinhCode) {
        this.tienTrinhCode = tienTrinhCode;
    }

    public String getDuLieuCode() {
        return duLieuCode;
    }

    public void setDuLieuCode(String duLieuCode) {
        this.duLieuCode = duLieuCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getQuyTrinhDonViId() {
        return quyTrinhDonViId;
    }

    public void setQuyTrinhDonViId(Long quyTrinhDonViId) {
        this.quyTrinhDonViId = quyTrinhDonViId;
    }

    public String getQuyTrinhDonViName() {
        return quyTrinhDonViName;
    }

    public void setQuyTrinhDonViName(String quyTrinhDonViName) {
        this.quyTrinhDonViName = quyTrinhDonViName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
