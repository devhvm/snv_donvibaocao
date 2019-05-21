package com.manager.donvibaocao.service;

import com.manager.donvibaocao.service.dto.BaoCaoDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing BaoCao.
 */
public interface BaoCaoService {

    /**
     * Save a baoCao.
     *
     * @param baoCaoDTO the entity to save
     * @return the persisted entity
     */
    BaoCaoDTO save(BaoCaoDTO baoCaoDTO);

    /**
     * Get all the baoCaos.
     *
     * @return the list of entities
     */
    List<BaoCaoDTO> findAll();


    /**
     * Get the "id" baoCao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BaoCaoDTO> findOne(String id);

    /**
     * Delete the "id" baoCao.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
