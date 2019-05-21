package com.manager.donvibaocao.service.mapper;

import com.manager.donvibaocao.domain.*;
import com.manager.donvibaocao.service.dto.BaoCaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BaoCao and its DTO BaoCaoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaoCaoMapper extends EntityMapper<BaoCaoDTO, BaoCao> {


    @Mapping(target = "dulieubaocaos", ignore = true)
    @Mapping(target = "tientrinhbaocaos", ignore = true)
    BaoCao toEntity(BaoCaoDTO baoCaoDTO);

    default BaoCao fromId(String id) {
        if (id == null) {
            return null;
        }
        BaoCao baoCao = new BaoCao();
        baoCao.setId(id);
        return baoCao;
    }
}
