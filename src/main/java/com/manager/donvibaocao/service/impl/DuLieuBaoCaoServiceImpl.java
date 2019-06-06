package com.manager.donvibaocao.service.impl;

import com.manager.donvibaocao.domain.DuLieuBaoCao;
import com.manager.donvibaocao.repository.DuLieuBaoCaoRepository;
import com.manager.donvibaocao.service.DuLieuBaoCaoService;
import com.manager.donvibaocao.service.dto.DuLieuBaoCaoDTO;
import com.manager.donvibaocao.service.mapper.DuLieuBaoCaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing DuLieuBaoCao.
 */
@Service
public class DuLieuBaoCaoServiceImpl implements DuLieuBaoCaoService {

    private final Logger log = LoggerFactory.getLogger(DuLieuBaoCaoServiceImpl.class);

    private final DuLieuBaoCaoRepository duLieuBaoCaoRepository;

    private final DuLieuBaoCaoMapper duLieuBaoCaoMapper;

    public DuLieuBaoCaoServiceImpl(DuLieuBaoCaoRepository duLieuBaoCaoRepository, DuLieuBaoCaoMapper duLieuBaoCaoMapper) {
        this.duLieuBaoCaoRepository = duLieuBaoCaoRepository;
        this.duLieuBaoCaoMapper = duLieuBaoCaoMapper;
    }

    /**
     * Save a duLieuBaoCao.
     *
     * @param duLieuBaoCaoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DuLieuBaoCaoDTO save(DuLieuBaoCaoDTO duLieuBaoCaoDTO) {
        log.debug("Request to save DuLieuBaoCao : {}", duLieuBaoCaoDTO);
        DuLieuBaoCao duLieuBaoCao = duLieuBaoCaoMapper.toEntity(duLieuBaoCaoDTO);
        duLieuBaoCao = duLieuBaoCaoRepository.save(duLieuBaoCao);
        return duLieuBaoCaoMapper.toDto(duLieuBaoCao);
    }

    /**
     * Get all the duLieuBaoCaos.
     *
     * @return the list of entities
     */
    @Override
    public List<DuLieuBaoCaoDTO> findAll() {
        log.debug("Request to get all DuLieuBaoCaos");
        return duLieuBaoCaoRepository.findAll().stream()
            .map(duLieuBaoCaoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one duLieuBaoCao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<DuLieuBaoCaoDTO> findOne(String id) {
        log.debug("Request to get DuLieuBaoCao : {}", id);
        return duLieuBaoCaoRepository.findById(id)
            .map(duLieuBaoCaoMapper::toDto);
    }

    /**
     * Delete the duLieuBaoCao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete DuLieuBaoCao : {}", id);
        duLieuBaoCaoRepository.deleteById(id);
    }
}
