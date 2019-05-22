package com.manager.donvibaocao.service.impl;

import com.manager.donvibaocao.client.QuyTrinhDonViServiceClient;
import com.manager.donvibaocao.domain.TienTrinhBaoCao;
import com.manager.donvibaocao.domain.enumeration.Status;
import com.manager.donvibaocao.security.SecurityUtils;
import com.manager.donvibaocao.service.BaoCaoService;
import com.manager.donvibaocao.domain.BaoCao;
import com.manager.donvibaocao.repository.BaoCaoRepository;
import com.manager.donvibaocao.service.dto.BaoCaoDTO;
import com.manager.donvibaocao.service.dto.SaveBaoCaoDTO;
import com.manager.donvibaocao.service.dto.TienTrinhBaoCaoDTO;
import com.manager.donvibaocao.service.mapper.BaoCaoMapper;
import com.manager.donvibaocao.service.mapper.TienTrinhBaoCaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URISyntaxException;
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
    private final TienTrinhBaoCaoMapper tienTrinhBaoCaoMapper;

    @Autowired
    @Qualifier("quytrinhdonvi")
    QuyTrinhDonViServiceClient quyTrinhDonViServiceClient;

    public BaoCaoServiceImpl(BaoCaoRepository baoCaoRepository, BaoCaoMapper baoCaoMapper,TienTrinhBaoCaoMapper tienTrinhBaoCaoMapper) {
        this.baoCaoRepository = baoCaoRepository;
        this.baoCaoMapper = baoCaoMapper;
        this.tienTrinhBaoCaoMapper = tienTrinhBaoCaoMapper;
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
     * @param baoCaoCode the id of the entity
     */
    @Override
    @Transactional
    public void delete(String baoCaoCode) {
        log.debug("Request to delete BaoCao : {}", baoCaoCode);
        Optional<BaoCaoDTO> baoCaoDTO = findOneByCode(baoCaoCode);

        if (baoCaoDTO.isPresent() && !baoCaoDTO.get().getStatus().isFited()) {
            throw new RuntimeException("Ma bao cao dang duoc xu ly");
        }
        if (baoCaoDTO.isPresent() && !baoCaoDTO.get().getStatus().isNew()) {
            baoCaoRepository.deleteByBaoCaoCode(baoCaoCode);
        }
        if(baoCaoDTO.isPresent()){
            baoCaoDTO.get().setStatus(Status.DELETED);
            BaoCao baoCao = baoCaoMapper.toEntity(baoCaoDTO.get());
            baoCaoRepository.save(baoCao);
        }
    }

    @Override
    @Transactional
    public BaoCaoDTO saveBaoCao(SaveBaoCaoDTO saveBaoCaoDTO) {

        Optional<BaoCaoDTO> baoCaoDTO = findOneByCode(saveBaoCaoDTO.getBaoCaoCode());

        if (baoCaoDTO.isPresent() && !baoCaoDTO.get().getStatus().isFited()) {
            throw new RuntimeException("Ma bao cao dang duoc xu ly");
        }
        if(baoCaoDTO.isPresent()){
            baoCaoDTO.get().setDulieubaocaos(saveBaoCaoDTO.getDulieubaocaos());
        }else {
            baoCaoDTO = Optional.ofNullable(saveBaoCaoDTO.ofBaoCao());
        }

        BaoCao baoCao = baoCaoMapper.toEntity(baoCaoDTO.get());

        TienTrinhBaoCaoDTO duLieuTienTrinhDTO = new TienTrinhBaoCaoDTO(saveBaoCaoDTO.getName(), saveBaoCaoDTO.getName(), saveBaoCaoDTO.getMauBaoCaoCode(), SecurityUtils.getCurrentUserLogin().get(), "", "Tao moi", saveBaoCaoDTO.getQuyTrinhDonViId(), saveBaoCaoDTO.getQuyTrinhDonViName(), Status.NEW);

        try {
            //call API thêm quy trình
            quyTrinhDonViServiceClient.createDuLieuTienTrinh(duLieuTienTrinhDTO);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Khong tao duoc quy trinh");
        }

        TienTrinhBaoCao duLieuTienTrinh = tienTrinhBaoCaoMapper.toEntity(duLieuTienTrinhDTO);

        baoCao.getTientrinhbaocaos().add(duLieuTienTrinh);
        baoCao.setStatus(duLieuTienTrinh.getStatus());

        baoCao = baoCaoRepository.save(baoCao);

        return baoCaoMapper.toDto(baoCao);
    }

    @Override
    public Optional<BaoCaoDTO> findOneByCode(String baoCaoCode) {
        log.debug("Request to get BaoCao : {}", baoCaoCode);
        return baoCaoRepository.findByBaoCaoCode(baoCaoCode)
                .map(baoCaoMapper::toDto);
    }

    @Override
    @Transactional
    public Optional<TienTrinhBaoCaoDTO> updateQuyTrinh(String baoCaoCode, TienTrinhBaoCaoDTO tienTrinhBaoCao) {
        Optional<BaoCao> mauPhatHanhOptional = baoCaoRepository.findByBaoCaoCode(baoCaoCode);

        BaoCao baoCao = mauPhatHanhOptional.orElseThrow(()->new RuntimeException("Bao cao khong ton tai."));

        TienTrinhBaoCao tienTrinhBaoCao1 = tienTrinhBaoCaoMapper.toEntity(tienTrinhBaoCao);

        baoCao.getTientrinhbaocaos().add(tienTrinhBaoCao1);
        baoCao.setStatus(tienTrinhBaoCao1.getStatus());
        baoCaoRepository.save(baoCao);

        return Optional.of(tienTrinhBaoCaoMapper.toDto(tienTrinhBaoCao1));
    }
}
