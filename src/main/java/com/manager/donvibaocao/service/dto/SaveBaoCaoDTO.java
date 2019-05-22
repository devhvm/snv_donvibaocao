package com.manager.donvibaocao.service.dto;


import com.manager.donvibaocao.domain.enumeration.Status;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the BaoCao entity.
 */
public class SaveBaoCaoDTO implements Serializable {

    @NotNull
    private String baoCaoCode;

    @NotNull
    private String mauBaoCaoCode;

    @NotNull
    private String name;

    @NotNull
    private String tienTrinhCode;
    @NotNull
    private Long quyTrinhDonViId;
    @NotNull
    private String quyTrinhDonViName;

    @NotNull
    private List<DuLieuBaoCaoDTO> dulieubaocaos = new ArrayList<>();



    public String getBaoCaoCode() {
        return baoCaoCode;
    }

    public void setBaoCaoCode(String baoCaoCode) {
        this.baoCaoCode = baoCaoCode;
    }

    public String getMauBaoCaoCode() {
        return mauBaoCaoCode;
    }

    public void setMauBaoCaoCode(String mauBaoCaoCode) {
        this.mauBaoCaoCode = mauBaoCaoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DuLieuBaoCaoDTO> getDulieubaocaos() {
        return dulieubaocaos;
    }

    public void setDulieubaocaos(List<DuLieuBaoCaoDTO> dulieubaocaos) {
        this.dulieubaocaos = dulieubaocaos;
    }

    public String getTienTrinhCode() {
        return tienTrinhCode;
    }

    public void setTienTrinhCode(String tienTrinhCode) {
        this.tienTrinhCode = tienTrinhCode;
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

    public BaoCaoDTO ofBaoCao(){
        return new BaoCaoDTO(this.baoCaoCode,this.mauBaoCaoCode,this.name, Status.NEW);
    }
}
