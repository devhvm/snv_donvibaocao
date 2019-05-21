package com.manager.donvibaocao.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.manager.donvibaocao.domain.enumeration.Status;

/**
 * A BaoCao.
 */
@Document(collection = "bao_cao")
public class BaoCao implements Serializable {

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

    @DBRef
    @Field("dulieubaocao")
    private Set<DuLieuBaoCao> dulieubaocaos = new HashSet<>();
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

    public Set<DuLieuBaoCao> getDulieubaocaos() {
        return dulieubaocaos;
    }

    public BaoCao dulieubaocaos(Set<DuLieuBaoCao> duLieuBaoCaos) {
        this.dulieubaocaos = duLieuBaoCaos;
        return this;
    }

    public BaoCao addDulieubaocao(DuLieuBaoCao duLieuBaoCao) {
        this.dulieubaocaos.add(duLieuBaoCao);
        duLieuBaoCao.setBaocao(this);
        return this;
    }

    public BaoCao removeDulieubaocao(DuLieuBaoCao duLieuBaoCao) {
        this.dulieubaocaos.remove(duLieuBaoCao);
        duLieuBaoCao.setBaocao(null);
        return this;
    }

    public void setDulieubaocaos(Set<DuLieuBaoCao> duLieuBaoCaos) {
        this.dulieubaocaos = duLieuBaoCaos;
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
