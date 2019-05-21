package com.manager.donvibaocao.service;

import com.manager.donvibaocao.service.dto.TienTrinhBaoCaoDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TienTrinhBaoCao.
 */
public interface TienTrinhBaoCaoService {

    /**
     * Save a tienTrinhBaoCao.
     *
     * @param tienTrinhBaoCaoDTO the entity to save
     * @return the persisted entity
     */
    TienTrinhBaoCaoDTO save(TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO);

    /**
     * Get all the tienTrinhBaoCaos.
     *
     * @return the list of entities
     */
    List<TienTrinhBaoCaoDTO> findAll();


    /**
     * Get the "id" tienTrinhBaoCao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TienTrinhBaoCaoDTO> findOne(String id);

    /**
     * Delete the "id" tienTrinhBaoCao.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
