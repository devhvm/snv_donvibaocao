package com.manager.donvibaocao.service.mapper;

import com.manager.donvibaocao.domain.*;
import com.manager.donvibaocao.service.dto.DuLieuBaoCaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DuLieuBaoCao and its DTO DuLieuBaoCaoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DuLieuBaoCaoMapper extends EntityMapper<DuLieuBaoCaoDTO, DuLieuBaoCao> {



    default DuLieuBaoCao fromId(String id) {
        if (id == null) {
            return null;
        }
        DuLieuBaoCao duLieuBaoCao = new DuLieuBaoCao();
        duLieuBaoCao.setId(id);
        return duLieuBaoCao;
    }
}
