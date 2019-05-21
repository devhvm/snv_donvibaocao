package com.manager.donvibaocao.web.rest;
import com.manager.donvibaocao.service.BaoCaoService;
import com.manager.donvibaocao.web.rest.errors.BadRequestAlertException;
import com.manager.donvibaocao.web.rest.util.HeaderUtil;
import com.manager.donvibaocao.service.dto.BaoCaoDTO;
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
 * REST controller for managing BaoCao.
 */
@RestController
@RequestMapping("/api")
public class BaoCaoResource {

    private final Logger log = LoggerFactory.getLogger(BaoCaoResource.class);

    private static final String ENTITY_NAME = "donvibaocaoBaoCao";

    private final BaoCaoService baoCaoService;

    public BaoCaoResource(BaoCaoService baoCaoService) {
        this.baoCaoService = baoCaoService;
    }

    /**
     * POST  /bao-caos : Create a new baoCao.
     *
     * @param baoCaoDTO the baoCaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new baoCaoDTO, or with status 400 (Bad Request) if the baoCao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bao-caos")
    public ResponseEntity<BaoCaoDTO> createBaoCao(@Valid @RequestBody BaoCaoDTO baoCaoDTO) throws URISyntaxException {
        log.debug("REST request to save BaoCao : {}", baoCaoDTO);
        if (baoCaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new baoCao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BaoCaoDTO result = baoCaoService.save(baoCaoDTO);
        return ResponseEntity.created(new URI("/api/bao-caos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bao-caos : Updates an existing baoCao.
     *
     * @param baoCaoDTO the baoCaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated baoCaoDTO,
     * or with status 400 (Bad Request) if the baoCaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the baoCaoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bao-caos")
    public ResponseEntity<BaoCaoDTO> updateBaoCao(@Valid @RequestBody BaoCaoDTO baoCaoDTO) throws URISyntaxException {
        log.debug("REST request to update BaoCao : {}", baoCaoDTO);
        if (baoCaoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BaoCaoDTO result = baoCaoService.save(baoCaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, baoCaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bao-caos : get all the baoCaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of baoCaos in body
     */
    @GetMapping("/bao-caos")
    public List<BaoCaoDTO> getAllBaoCaos() {
        log.debug("REST request to get all BaoCaos");
        return baoCaoService.findAll();
    }

    /**
     * GET  /bao-caos/:id : get the "id" baoCao.
     *
     * @param id the id of the baoCaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the baoCaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/bao-caos/{id}")
    public ResponseEntity<BaoCaoDTO> getBaoCao(@PathVariable String id) {
        log.debug("REST request to get BaoCao : {}", id);
        Optional<BaoCaoDTO> baoCaoDTO = baoCaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(baoCaoDTO);
    }

    /**
     * DELETE  /bao-caos/:id : delete the "id" baoCao.
     *
     * @param id the id of the baoCaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bao-caos/{id}")
    public ResponseEntity<Void> deleteBaoCao(@PathVariable String id) {
        log.debug("REST request to delete BaoCao : {}", id);
        baoCaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
