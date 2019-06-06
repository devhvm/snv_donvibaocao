package com.manager.donvibaocao.service.mapper;

import com.manager.donvibaocao.domain.TienTrinhBaoCao;
import com.manager.donvibaocao.service.dto.TienTrinhBaoCaoDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity TienTrinhBaoCao and its DTO TienTrinhBaoCaoDTO.
 */
@Mapper(componentModel = "spring", uses = {BaoCaoMapper.class})
public interface TienTrinhBaoCaoMapper extends EntityMapper<TienTrinhBaoCaoDTO, TienTrinhBaoCao> {

    //@Mapping(source = "baocao.id", target = "baocaoId")
    TienTrinhBaoCaoDTO toDto(TienTrinhBaoCao tienTrinhBaoCao);

    //@Mapping(source = "baocaoId", target = "baocao")
    TienTrinhBaoCao toEntity(TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO);
}
