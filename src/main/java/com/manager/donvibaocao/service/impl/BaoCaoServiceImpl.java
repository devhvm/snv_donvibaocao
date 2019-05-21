package com.manager.donvibaocao.service.impl;

import com.manager.donvibaocao.service.BaoCaoService;
import com.manager.donvibaocao.domain.BaoCao;
import com.manager.donvibaocao.repository.BaoCaoRepository;
import com.manager.donvibaocao.service.dto.BaoCaoDTO;
import com.manager.donvibaocao.service.mapper.BaoCaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing BaoCao.
 */
@Service
public class BaoCaoServiceImpl implements BaoCaoService {

    private final Logger log = LoggerFactory.getLogger(BaoCaoServiceImpl.class);

    private final BaoCaoRepository baoCaoRepository;

    private final BaoCaoMapper baoCaoMapper;

    public BaoCaoServiceImpl(BaoCaoRepository baoCaoRepository, BaoCaoMapper baoCaoMapper) {
        this.baoCaoRepository = baoCaoRepository;
        this.baoCaoMapper = baoCaoMapper;
    }

    /**
     * Save a baoCao.
     *
     * @param baoCaoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BaoCaoDTO save(BaoCaoDTO baoCaoDTO) {
        log.debug("Request to save BaoCao : {}", baoCaoDTO);
        BaoCao baoCao = baoCaoMapper.toEntity(baoCaoDTO);
        baoCao = baoCaoRepository.save(baoCao);
        return baoCaoMapper.toDto(baoCao);
    }

    /**
     * Get all the baoCaos.
     *
     * @return the list of entities
     */
    @Override
    public List<BaoCaoDTO> findAll() {
        log.debug("Request to get all BaoCaos");
        return baoCaoRepository.findAll().stream()
            .map(baoCaoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one baoCao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<BaoCaoDTO> findOne(String id) {
        log.debug("Request to get BaoCao : {}", id);
        return baoCaoRepository.findById(id)
            .map(baoCaoMapper::toDto);
    }

    /**
     * Delete the baoCao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete BaoCao : {}", id);
        baoCaoRepository.deleteById(id);
    }
}
