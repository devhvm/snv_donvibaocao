package com.manager.donvibaocao.service.mapper;

import com.manager.donvibaocao.domain.*;
import com.manager.donvibaocao.service.dto.TienTrinhBaoCaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TienTrinhBaoCao and its DTO TienTrinhBaoCaoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TienTrinhBaoCaoMapper extends EntityMapper<TienTrinhBaoCaoDTO, TienTrinhBaoCao> {



    default TienTrinhBaoCao fromId(String id) {
        if (id == null) {
            return null;
        }
        TienTrinhBaoCao tienTrinhBaoCao = new TienTrinhBaoCao();
        tienTrinhBaoCao.setId(id);
        return tienTrinhBaoCao;
    }
}
