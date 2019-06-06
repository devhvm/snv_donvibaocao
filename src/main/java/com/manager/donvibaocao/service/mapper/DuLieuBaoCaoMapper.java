package com.manager.donvibaocao.service.mapper;

import com.manager.donvibaocao.domain.DuLieuBaoCao;
import com.manager.donvibaocao.service.dto.DuLieuBaoCaoDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity DuLieuBaoCao and its DTO DuLieuBaoCaoDTO.
 */
@Mapper(componentModel = "spring", uses = {BaoCaoMapper.class})
public interface DuLieuBaoCaoMapper extends EntityMapper<DuLieuBaoCaoDTO, DuLieuBaoCao> {

    //@Mapping(source = "baocao.id", target = "baocaoId")
    DuLieuBaoCaoDTO toDto(DuLieuBaoCao duLieuBaoCao);

    //@Mapping(source = "baocaoId", target = "baocao")
    DuLieuBaoCao toEntity(DuLieuBaoCaoDTO duLieuBaoCaoDTO);
}
