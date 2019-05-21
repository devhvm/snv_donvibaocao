package com.manager.donvibaocao.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.manager.donvibaocao.domain.enumeration.Status;

/**
 * A DTO for the BaoCao entity.
 */
public class BaoCaoDTO implements Serializable {

    private String id;

    @NotNull
    private String baoCaoCode;

    @NotNull
    private String mauBaoCaoCode;

    @NotNull
    private String name;

    @NotNull
    private Status status;


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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaoCaoDTO baoCaoDTO = (BaoCaoDTO) o;
        if (baoCaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), baoCaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BaoCaoDTO{" +
            "id=" + getId() +
            ", baoCaoCode='" + getBaoCaoCode() + "'" +
            ", mauBaoCaoCode='" + getMauBaoCaoCode() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
