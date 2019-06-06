package com.manager.donvibaocao.domain;


import com.manager.donvibaocao.domain.enumeration.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A BaoCao.
 */
@Document(collection = "bao_cao")
public class BaoCao extends  AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("bao_cao_code")
    private String baoCaoCode;

    @NotNull
    @Field("mau_bao_cao_code")
    private String mauBaoCaoCode;

    @NotNull
    @Field("name")
    private String name;

    @NotNull
    @Field("status")
    private Status status;

    //@DBRef
    @Field("dulieubaocao")
    private List<DuLieuBaoCao> dulieubaocaos = new ArrayList<>();
    //@DBRef
    @Field("tientrinhbaocao")
    private List<TienTrinhBaoCao> tientrinhbaocaos = new ArrayList<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaoCaoCode() {
        return baoCaoCode;
    }

    public BaoCao baoCaoCode(String baoCaoCode) {
        this.baoCaoCode = baoCaoCode;
        return this;
    }

    public void setBaoCaoCode(String baoCaoCode) {
        this.baoCaoCode = baoCaoCode;
    }

    public String getMauBaoCaoCode() {
        return mauBaoCaoCode;
    }

    public BaoCao mauBaoCaoCode(String mauBaoCaoCode) {
        this.mauBaoCaoCode = mauBaoCaoCode;
        return this;
    }

    public void setMauBaoCaoCode(String mauBaoCaoCode) {
        this.mauBaoCaoCode = mauBaoCaoCode;
    }

    public String getName() {
        return name;
    }

    public BaoCao name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public BaoCao status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<DuLieuBaoCao> getDulieubaocaos() {
        return dulieubaocaos;
    }



    public void setDulieubaocaos(List<DuLieuBaoCao> duLieuBaoCaos) {
        this.dulieubaocaos = duLieuBaoCaos;
    }

    public List<TienTrinhBaoCao> getTientrinhbaocaos() {
        return tientrinhbaocaos;
    }

    public void setTientrinhbaocaos(List<TienTrinhBaoCao> tienTrinhBaoCaos) {
        this.tientrinhbaocaos = tienTrinhBaoCaos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaoCao baoCao = (BaoCao) o;
        if (baoCao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), baoCao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BaoCao{" +
            "id=" + getId() +
            ", baoCaoCode='" + getBaoCaoCode() + "'" +
            ", mauBaoCaoCode='" + getMauBaoCaoCode() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
