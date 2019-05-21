package com.manager.donvibaocao.web.rest;
import com.manager.donvibaocao.service.TienTrinhBaoCaoService;
import com.manager.donvibaocao.web.rest.errors.BadRequestAlertException;
import com.manager.donvibaocao.web.rest.util.HeaderUtil;
import com.manager.donvibaocao.service.dto.TienTrinhBaoCaoDTO;
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
 * REST controller for managing TienTrinhBaoCao.
 */
@RestController
@RequestMapping("/api")
public class TienTrinhBaoCaoResource {

    private final Logger log = LoggerFactory.getLogger(TienTrinhBaoCaoResource.class);

    private static final String ENTITY_NAME = "donvibaocaoTienTrinhBaoCao";

    private final TienTrinhBaoCaoService tienTrinhBaoCaoService;

    public TienTrinhBaoCaoResource(TienTrinhBaoCaoService tienTrinhBaoCaoService) {
        this.tienTrinhBaoCaoService = tienTrinhBaoCaoService;
    }

    /**
     * POST  /tien-trinh-bao-caos : Create a new tienTrinhBaoCao.
     *
     * @param tienTrinhBaoCaoDTO the tienTrinhBaoCaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tienTrinhBaoCaoDTO, or with status 400 (Bad Request) if the tienTrinhBaoCao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tien-trinh-bao-caos")
    public ResponseEntity<TienTrinhBaoCaoDTO> createTienTrinhBaoCao(@Valid @RequestBody TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO) throws URISyntaxException {
        log.debug("REST request to save TienTrinhBaoCao : {}", tienTrinhBaoCaoDTO);
        if (tienTrinhBaoCaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new tienTrinhBaoCao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TienTrinhBaoCaoDTO result = tienTrinhBaoCaoService.save(tienTrinhBaoCaoDTO);
        return ResponseEntity.created(new URI("/api/tien-trinh-bao-caos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tien-trinh-bao-caos : Updates an existing tienTrinhBaoCao.
     *
     * @param tienTrinhBaoCaoDTO the tienTrinhBaoCaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tienTrinhBaoCaoDTO,
     * or with status 400 (Bad Request) if the tienTrinhBaoCaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the tienTrinhBaoCaoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tien-trinh-bao-caos")
    public ResponseEntity<TienTrinhBaoCaoDTO> updateTienTrinhBaoCao(@Valid @RequestBody TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO) throws URISyntaxException {
        log.debug("REST request to update TienTrinhBaoCao : {}", tienTrinhBaoCaoDTO);
        if (tienTrinhBaoCaoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TienTrinhBaoCaoDTO result = tienTrinhBaoCaoService.save(tienTrinhBaoCaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tienTrinhBaoCaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tien-trinh-bao-caos : get all the tienTrinhBaoCaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tienTrinhBaoCaos in body
     */
    @GetMapping("/tien-trinh-bao-caos")
    public List<TienTrinhBaoCaoDTO> getAllTienTrinhBaoCaos() {
        log.debug("REST request to get all TienTrinhBaoCaos");
        return tienTrinhBaoCaoService.findAll();
    }

    /**
     * GET  /tien-trinh-bao-caos/:id : get the "id" tienTrinhBaoCao.
     *
     * @param id the id of the tienTrinhBaoCaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tienTrinhBaoCaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tien-trinh-bao-caos/{id}")
    public ResponseEntity<TienTrinhBaoCaoDTO> getTienTrinhBaoCao(@PathVariable String id) {
        log.debug("REST request to get TienTrinhBaoCao : {}", id);
        Optional<TienTrinhBaoCaoDTO> tienTrinhBaoCaoDTO = tienTrinhBaoCaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tienTrinhBaoCaoDTO);
    }

    /**
     * DELETE  /tien-trinh-bao-caos/:id : delete the "id" tienTrinhBaoCao.
     *
     * @param id the id of the tienTrinhBaoCaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tien-trinh-bao-caos/{id}")
    public ResponseEntity<Void> deleteTienTrinhBaoCao(@PathVariable String id) {
        log.debug("REST request to delete TienTrinhBaoCao : {}", id);
        tienTrinhBaoCaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
