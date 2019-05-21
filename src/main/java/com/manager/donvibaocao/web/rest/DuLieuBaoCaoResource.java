package com.manager.donvibaocao.web.rest;
import com.manager.donvibaocao.service.DuLieuBaoCaoService;
import com.manager.donvibaocao.web.rest.errors.BadRequestAlertException;
import com.manager.donvibaocao.web.rest.util.HeaderUtil;
import com.manager.donvibaocao.service.dto.DuLieuBaoCaoDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DuLieuBaoCao.
 */
@RestController
@RequestMapping("/api")
public class DuLieuBaoCaoResource {

    private final Logger log = LoggerFactory.getLogger(DuLieuBaoCaoResource.class);

    private static final String ENTITY_NAME = "donvibaocaoDuLieuBaoCao";

    private final DuLieuBaoCaoService duLieuBaoCaoService;

    public DuLieuBaoCaoResource(DuLieuBaoCaoService duLieuBaoCaoService) {
        this.duLieuBaoCaoService = duLieuBaoCaoService;
    }

    /**
     * POST  /du-lieu-bao-caos : Create a new duLieuBaoCao.
     *
     * @param duLieuBaoCaoDTO the duLieuBaoCaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new duLieuBaoCaoDTO, or with status 400 (Bad Request) if the duLieuBaoCao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/du-lieu-bao-caos")
    public ResponseEntity<DuLieuBaoCaoDTO> createDuLieuBaoCao(@Valid @RequestBody DuLieuBaoCaoDTO duLieuBaoCaoDTO) throws URISyntaxException {
        log.debug("REST request to save DuLieuBaoCao : {}", duLieuBaoCaoDTO);
        if (duLieuBaoCaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new duLieuBaoCao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DuLieuBaoCaoDTO result = duLieuBaoCaoService.save(duLieuBaoCaoDTO);
        return ResponseEntity.created(new URI("/api/du-lieu-bao-caos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /du-lieu-bao-caos : Updates an existing duLieuBaoCao.
     *
     * @param duLieuBaoCaoDTO the duLieuBaoCaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated duLieuBaoCaoDTO,
     * or with status 400 (Bad Request) if the duLieuBaoCaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the duLieuBaoCaoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/du-lieu-bao-caos")
    public ResponseEntity<DuLieuBaoCaoDTO> updateDuLieuBaoCao(@Valid @RequestBody DuLieuBaoCaoDTO duLieuBaoCaoDTO) throws URISyntaxException {
        log.debug("REST request to update DuLieuBaoCao : {}", duLieuBaoCaoDTO);
        if (duLieuBaoCaoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DuLieuBaoCaoDTO result = duLieuBaoCaoService.save(duLieuBaoCaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, duLieuBaoCaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /du-lieu-bao-caos : get all the duLieuBaoCaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of duLieuBaoCaos in body
     */
    @GetMapping("/du-lieu-bao-caos")
    public List<DuLieuBaoCaoDTO> getAllDuLieuBaoCaos() {
        log.debug("REST request to get all DuLieuBaoCaos");
        return duLieuBaoCaoService.findAll();
    }

    /**
     * GET  /du-lieu-bao-caos/:id : get the "id" duLieuBaoCao.
     *
     * @param id the id of the duLieuBaoCaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the duLieuBaoCaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/du-lieu-bao-caos/{id}")
    public ResponseEntity<DuLieuBaoCaoDTO> getDuLieuBaoCao(@PathVariable String id) {
        log.debug("REST request to get DuLieuBaoCao : {}", id);
        Optional<DuLieuBaoCaoDTO> duLieuBaoCaoDTO = duLieuBaoCaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(duLieuBaoCaoDTO);
    }

    /**
     * DELETE  /du-lieu-bao-caos/:id : delete the "id" duLieuBaoCao.
     *
     * @param id the id of the duLieuBaoCaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/du-lieu-bao-caos/{id}")
    public ResponseEntity<Void> deleteDuLieuBaoCao(@PathVariable String id) {
        log.debug("REST request to delete DuLieuBaoCao : {}", id);
        duLieuBaoCaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
