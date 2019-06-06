package com.manager.donvibaocao.web.rest;

import com.manager.donvibaocao.service.BaoCaoService;
import com.manager.donvibaocao.service.dto.BaoCaoDTO;
import com.manager.donvibaocao.service.dto.SaveBaoCaoDTO;
import com.manager.donvibaocao.service.dto.TienTrinhBaoCaoDTO;
import com.manager.donvibaocao.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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
     * POST  /bao-cao : Create a save baoCao.
     *
     * @param saveBaoCaoDTO the baoCaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new baoCaoDTO, or with status 400 (Bad Request) if the baoCao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bao-cao")
    public ResponseEntity<BaoCaoDTO> saveBaoCao(@Valid @RequestBody SaveBaoCaoDTO saveBaoCaoDTO) throws URISyntaxException {
        log.debug("REST request to save BaoCao : {}", saveBaoCaoDTO);

        BaoCaoDTO result = baoCaoService.saveBaoCao(saveBaoCaoDTO);
        return ResponseEntity.created(new URI("/api/bao-cao/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId()))
                .body(result);
    }
    /**
     * GET  /bao-cao/:baoCaoCode : get the "baoCaoCode" baoCao.
     *
     * @param baoCaoCode the id of the BaoCaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the baoCaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/bao-cao/{baoCaoCode}")
    public ResponseEntity<BaoCaoDTO> getBaoCao(@PathVariable String baoCaoCode) {
        log.debug("REST request to get BaoCao : {}", baoCaoCode);
        Optional<BaoCaoDTO> baoCaoDTO = baoCaoService.findOneByCode(baoCaoCode);
        return ResponseUtil.wrapOrNotFound(baoCaoDTO);
    }


    /**
     * DELETE  /bao-caos/:baoCaoCode : delete the "baoCaoCode" baoCao.
     *
     * @param baoCaoCode the id of the baoCaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bao-cao/{baoCaoCode}")
    public ResponseEntity<Void> deleteBaoCao(@PathVariable String baoCaoCode) {
        log.debug("REST request to delete BaoCao : {}", baoCaoCode);
        baoCaoService.delete(baoCaoCode);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, baoCaoCode)).build();
    }

    @PutMapping("/bao-cao/{baoCaoCode}/cap-nhat-quy-trinh")
    public ResponseEntity<TienTrinhBaoCaoDTO> updateMauPhatHanh(@PathVariable String baoCaoCode, @Valid @RequestBody TienTrinhBaoCaoDTO tienTrinhBaoCao) {
        log.debug("REST request to  update MauBaoCao : {}", tienTrinhBaoCao);

        TienTrinhBaoCaoDTO result = baoCaoService.updateQuyTrinh(baoCaoCode, tienTrinhBaoCao).get();

        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("DuLieuTienTrinh", result.getTienTrinhCode()))
                .body(result);

    }
}
