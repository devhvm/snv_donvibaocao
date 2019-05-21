package com.manager.donvibaocao.service;

import com.manager.donvibaocao.service.dto.DuLieuBaoCaoDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing DuLieuBaoCao.
 */
public interface DuLieuBaoCaoService {

    /**
     * Save a duLieuBaoCao.
     *
     * @param duLieuBaoCaoDTO the entity to save
     * @return the persisted entity
     */
    DuLieuBaoCaoDTO save(DuLieuBaoCaoDTO duLieuBaoCaoDTO);

    /**
     * Get all the duLieuBaoCaos.
     *
     * @return the list of entities
     */
    List<DuLieuBaoCaoDTO> findAll();


    /**
     * Get the "id" duLieuBaoCao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DuLieuBaoCaoDTO> findOne(String id);

    /**
     * Delete the "id" duLieuBaoCao.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
