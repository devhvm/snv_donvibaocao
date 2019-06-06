package com.manager.donvibaocao.service.impl;

import com.manager.donvibaocao.domain.TienTrinhBaoCao;
import com.manager.donvibaocao.repository.TienTrinhBaoCaoRepository;
import com.manager.donvibaocao.service.TienTrinhBaoCaoService;
import com.manager.donvibaocao.service.dto.TienTrinhBaoCaoDTO;
import com.manager.donvibaocao.service.mapper.TienTrinhBaoCaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing TienTrinhBaoCao.
 */
@Service
public class TienTrinhBaoCaoServiceImpl implements TienTrinhBaoCaoService {

    private final Logger log = LoggerFactory.getLogger(TienTrinhBaoCaoServiceImpl.class);

    private final TienTrinhBaoCaoRepository tienTrinhBaoCaoRepository;

    private final TienTrinhBaoCaoMapper tienTrinhBaoCaoMapper;

    public TienTrinhBaoCaoServiceImpl(TienTrinhBaoCaoRepository tienTrinhBaoCaoRepository, TienTrinhBaoCaoMapper tienTrinhBaoCaoMapper) {
        this.tienTrinhBaoCaoRepository = tienTrinhBaoCaoRepository;
        this.tienTrinhBaoCaoMapper = tienTrinhBaoCaoMapper;
    }

    /**
     * Save a tienTrinhBaoCao.
     *
     * @param tienTrinhBaoCaoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TienTrinhBaoCaoDTO save(TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO) {
        log.debug("Request to save TienTrinhBaoCao : {}", tienTrinhBaoCaoDTO);
        TienTrinhBaoCao tienTrinhBaoCao = tienTrinhBaoCaoMapper.toEntity(tienTrinhBaoCaoDTO);
        tienTrinhBaoCao = tienTrinhBaoCaoRepository.save(tienTrinhBaoCao);
        return tienTrinhBaoCaoMapper.toDto(tienTrinhBaoCao);
    }

    /**
     * Get all the tienTrinhBaoCaos.
     *
     * @return the list of entities
     */
    @Override
    public List<TienTrinhBaoCaoDTO> findAll() {
        log.debug("Request to get all TienTrinhBaoCaos");
        return tienTrinhBaoCaoRepository.findAll().stream()
            .map(tienTrinhBaoCaoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one tienTrinhBaoCao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<TienTrinhBaoCaoDTO> findOne(String id) {
        log.debug("Request to get TienTrinhBaoCao : {}", id);
        return tienTrinhBaoCaoRepository.findById(id)
            .map(tienTrinhBaoCaoMapper::toDto);
    }

    /**
     * Delete the tienTrinhBaoCao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete TienTrinhBaoCao : {}", id);
        tienTrinhBaoCaoRepository.deleteById(id);
    }
}
