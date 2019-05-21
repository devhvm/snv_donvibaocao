package com.manager.donvibaocao.web.rest;

import com.manager.donvibaocao.DonvibaocaoApp;

import com.manager.donvibaocao.domain.DuLieuBaoCao;
import com.manager.donvibaocao.repository.DuLieuBaoCaoRepository;
import com.manager.donvibaocao.service.DuLieuBaoCaoService;
import com.manager.donvibaocao.service.dto.DuLieuBaoCaoDTO;
import com.manager.donvibaocao.service.mapper.DuLieuBaoCaoMapper;
import com.manager.donvibaocao.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import java.util.List;


import static com.manager.donvibaocao.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DuLieuBaoCaoResource REST controller.
 *
 * @see DuLieuBaoCaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DonvibaocaoApp.class)
public class DuLieuBaoCaoResourceIntTest {

    private static final String DEFAULT_BAO_CAO_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BAO_CAO_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NHOM_DANH_MUC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_NHOM_DANH_MUC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DANH_MUC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DANH_MUC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DOI_TUONG_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DOI_TUONG_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DON_VI_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DON_VI_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_GIA_TRI = 1;
    private static final Integer UPDATED_GIA_TRI = 2;

    @Autowired
    private DuLieuBaoCaoRepository duLieuBaoCaoRepository;

    @Autowired
    private DuLieuBaoCaoMapper duLieuBaoCaoMapper;

    @Autowired
    private DuLieuBaoCaoService duLieuBaoCaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restDuLieuBaoCaoMockMvc;

    private DuLieuBaoCao duLieuBaoCao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DuLieuBaoCaoResource duLieuBaoCaoResource = new DuLieuBaoCaoResource(duLieuBaoCaoService);
        this.restDuLieuBaoCaoMockMvc = MockMvcBuilders.standaloneSetup(duLieuBaoCaoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DuLieuBaoCao createEntity() {
        DuLieuBaoCao duLieuBaoCao = new DuLieuBaoCao()
            .baoCaoCode(DEFAULT_BAO_CAO_CODE)
            .nhomDanhMucCode(DEFAULT_NHOM_DANH_MUC_CODE)
            .danhMucCode(DEFAULT_DANH_MUC_CODE)
            .doiTuongCode(DEFAULT_DOI_TUONG_CODE)
            .donViCode(DEFAULT_DON_VI_CODE)
            .giaTri(DEFAULT_GIA_TRI);
        return duLieuBaoCao;
    }

    @Before
    public void initTest() {
        duLieuBaoCaoRepository.deleteAll();
        duLieuBaoCao = createEntity();
    }

    @Test
    public void createDuLieuBaoCao() throws Exception {
        int databaseSizeBeforeCreate = duLieuBaoCaoRepository.findAll().size();

        // Create the DuLieuBaoCao
        DuLieuBaoCaoDTO duLieuBaoCaoDTO = duLieuBaoCaoMapper.toDto(duLieuBaoCao);
        restDuLieuBaoCaoMockMvc.perform(post("/api/du-lieu-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuBaoCaoDTO)))
            .andExpect(status().isCreated());

        // Validate the DuLieuBaoCao in the database
        List<DuLieuBaoCao> duLieuBaoCaoList = duLieuBaoCaoRepository.findAll();
        assertThat(duLieuBaoCaoList).hasSize(databaseSizeBeforeCreate + 1);
        DuLieuBaoCao testDuLieuBaoCao = duLieuBaoCaoList.get(duLieuBaoCaoList.size() - 1);
        assertThat(testDuLieuBaoCao.getBaoCaoCode()).isEqualTo(DEFAULT_BAO_CAO_CODE);
        assertThat(testDuLieuBaoCao.getNhomDanhMucCode()).isEqualTo(DEFAULT_NHOM_DANH_MUC_CODE);
        assertThat(testDuLieuBaoCao.getDanhMucCode()).isEqualTo(DEFAULT_DANH_MUC_CODE);
        assertThat(testDuLieuBaoCao.getDoiTuongCode()).isEqualTo(DEFAULT_DOI_TUONG_CODE);
        assertThat(testDuLieuBaoCao.getDonViCode()).isEqualTo(DEFAULT_DON_VI_CODE);
        assertThat(testDuLieuBaoCao.getGiaTri()).isEqualTo(DEFAULT_GIA_TRI);
    }

    @Test
    public void createDuLieuBaoCaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = duLieuBaoCaoRepository.findAll().size();

        // Create the DuLieuBaoCao with an existing ID
        duLieuBaoCao.setId("existing_id");
        DuLieuBaoCaoDTO duLieuBaoCaoDTO = duLieuBaoCaoMapper.toDto(duLieuBaoCao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDuLieuBaoCaoMockMvc.perform(post("/api/du-lieu-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DuLieuBaoCao in the database
        List<DuLieuBaoCao> duLieuBaoCaoList = duLieuBaoCaoRepository.findAll();
        assertThat(duLieuBaoCaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkBaoCaoCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = duLieuBaoCaoRepository.findAll().size();
        // set the field null
        duLieuBaoCao.setBaoCaoCode(null);

        // Create the DuLieuBaoCao, which fails.
        DuLieuBaoCaoDTO duLieuBaoCaoDTO = duLieuBaoCaoMapper.toDto(duLieuBaoCao);

        restDuLieuBaoCaoMockMvc.perform(post("/api/du-lieu-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<DuLieuBaoCao> duLieuBaoCaoList = duLieuBaoCaoRepository.findAll();
        assertThat(duLieuBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNhomDanhMucCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = duLieuBaoCaoRepository.findAll().size();
        // set the field null
        duLieuBaoCao.setNhomDanhMucCode(null);

        // Create the DuLieuBaoCao, which fails.
        DuLieuBaoCaoDTO duLieuBaoCaoDTO = duLieuBaoCaoMapper.toDto(duLieuBaoCao);

        restDuLieuBaoCaoMockMvc.perform(post("/api/du-lieu-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<DuLieuBaoCao> duLieuBaoCaoList = duLieuBaoCaoRepository.findAll();
        assertThat(duLieuBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDanhMucCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = duLieuBaoCaoRepository.findAll().size();
        // set the field null
        duLieuBaoCao.setDanhMucCode(null);

        // Create the DuLieuBaoCao, which fails.
        DuLieuBaoCaoDTO duLieuBaoCaoDTO = duLieuBaoCaoMapper.toDto(duLieuBaoCao);

        restDuLieuBaoCaoMockMvc.perform(post("/api/du-lieu-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<DuLieuBaoCao> duLieuBaoCaoList = duLieuBaoCaoRepository.findAll();
        assertThat(duLieuBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDoiTuongCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = duLieuBaoCaoRepository.findAll().size();
        // set the field null
        duLieuBaoCao.setDoiTuongCode(null);

        // Create the DuLieuBaoCao, which fails.
        DuLieuBaoCaoDTO duLieuBaoCaoDTO = duLieuBaoCaoMapper.toDto(duLieuBaoCao);

        restDuLieuBaoCaoMockMvc.perform(post("/api/du-lieu-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<DuLieuBaoCao> duLieuBaoCaoList = duLieuBaoCaoRepository.findAll();
        assertThat(duLieuBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDonViCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = duLieuBaoCaoRepository.findAll().size();
        // set the field null
        duLieuBaoCao.setDonViCode(null);

        // Create the DuLieuBaoCao, which fails.
        DuLieuBaoCaoDTO duLieuBaoCaoDTO = duLieuBaoCaoMapper.toDto(duLieuBaoCao);

        restDuLieuBaoCaoMockMvc.perform(post("/api/du-lieu-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<DuLieuBaoCao> duLieuBaoCaoList = duLieuBaoCaoRepository.findAll();
        assertThat(duLieuBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkGiaTriIsRequired() throws Exception {
        int databaseSizeBeforeTest = duLieuBaoCaoRepository.findAll().size();
        // set the field null
        duLieuBaoCao.setGiaTri(null);

        // Create the DuLieuBaoCao, which fails.
        DuLieuBaoCaoDTO duLieuBaoCaoDTO = duLieuBaoCaoMapper.toDto(duLieuBaoCao);

        restDuLieuBaoCaoMockMvc.perform(post("/api/du-lieu-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<DuLieuBaoCao> duLieuBaoCaoList = duLieuBaoCaoRepository.findAll();
        assertThat(duLieuBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllDuLieuBaoCaos() throws Exception {
        // Initialize the database
        duLieuBaoCaoRepository.save(duLieuBaoCao);

        // Get all the duLieuBaoCaoList
        restDuLieuBaoCaoMockMvc.perform(get("/api/du-lieu-bao-caos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(duLieuBaoCao.getId())))
            .andExpect(jsonPath("$.[*].baoCaoCode").value(hasItem(DEFAULT_BAO_CAO_CODE.toString())))
            .andExpect(jsonPath("$.[*].nhomDanhMucCode").value(hasItem(DEFAULT_NHOM_DANH_MUC_CODE.toString())))
            .andExpect(jsonPath("$.[*].danhMucCode").value(hasItem(DEFAULT_DANH_MUC_CODE.toString())))
            .andExpect(jsonPath("$.[*].doiTuongCode").value(hasItem(DEFAULT_DOI_TUONG_CODE.toString())))
            .andExpect(jsonPath("$.[*].donViCode").value(hasItem(DEFAULT_DON_VI_CODE.toString())))
            .andExpect(jsonPath("$.[*].giaTri").value(hasItem(DEFAULT_GIA_TRI)));
    }
    
    @Test
    public void getDuLieuBaoCao() throws Exception {
        // Initialize the database
        duLieuBaoCaoRepository.save(duLieuBaoCao);

        // Get the duLieuBaoCao
        restDuLieuBaoCaoMockMvc.perform(get("/api/du-lieu-bao-caos/{id}", duLieuBaoCao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(duLieuBaoCao.getId()))
            .andExpect(jsonPath("$.baoCaoCode").value(DEFAULT_BAO_CAO_CODE.toString()))
            .andExpect(jsonPath("$.nhomDanhMucCode").value(DEFAULT_NHOM_DANH_MUC_CODE.toString()))
            .andExpect(jsonPath("$.danhMucCode").value(DEFAULT_DANH_MUC_CODE.toString()))
            .andExpect(jsonPath("$.doiTuongCode").value(DEFAULT_DOI_TUONG_CODE.toString()))
            .andExpect(jsonPath("$.donViCode").value(DEFAULT_DON_VI_CODE.toString()))
            .andExpect(jsonPath("$.giaTri").value(DEFAULT_GIA_TRI));
    }

    @Test
    public void getNonExistingDuLieuBaoCao() throws Exception {
        // Get the duLieuBaoCao
        restDuLieuBaoCaoMockMvc.perform(get("/api/du-lieu-bao-caos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDuLieuBaoCao() throws Exception {
        // Initialize the database
        duLieuBaoCaoRepository.save(duLieuBaoCao);

        int databaseSizeBeforeUpdate = duLieuBaoCaoRepository.findAll().size();

        // Update the duLieuBaoCao
        DuLieuBaoCao updatedDuLieuBaoCao = duLieuBaoCaoRepository.findById(duLieuBaoCao.getId()).get();
        updatedDuLieuBaoCao
            .baoCaoCode(UPDATED_BAO_CAO_CODE)
            .nhomDanhMucCode(UPDATED_NHOM_DANH_MUC_CODE)
            .danhMucCode(UPDATED_DANH_MUC_CODE)
            .doiTuongCode(UPDATED_DOI_TUONG_CODE)
            .donViCode(UPDATED_DON_VI_CODE)
            .giaTri(UPDATED_GIA_TRI);
        DuLieuBaoCaoDTO duLieuBaoCaoDTO = duLieuBaoCaoMapper.toDto(updatedDuLieuBaoCao);

        restDuLieuBaoCaoMockMvc.perform(put("/api/du-lieu-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuBaoCaoDTO)))
            .andExpect(status().isOk());

        // Validate the DuLieuBaoCao in the database
        List<DuLieuBaoCao> duLieuBaoCaoList = duLieuBaoCaoRepository.findAll();
        assertThat(duLieuBaoCaoList).hasSize(databaseSizeBeforeUpdate);
        DuLieuBaoCao testDuLieuBaoCao = duLieuBaoCaoList.get(duLieuBaoCaoList.size() - 1);
        assertThat(testDuLieuBaoCao.getBaoCaoCode()).isEqualTo(UPDATED_BAO_CAO_CODE);
        assertThat(testDuLieuBaoCao.getNhomDanhMucCode()).isEqualTo(UPDATED_NHOM_DANH_MUC_CODE);
        assertThat(testDuLieuBaoCao.getDanhMucCode()).isEqualTo(UPDATED_DANH_MUC_CODE);
        assertThat(testDuLieuBaoCao.getDoiTuongCode()).isEqualTo(UPDATED_DOI_TUONG_CODE);
        assertThat(testDuLieuBaoCao.getDonViCode()).isEqualTo(UPDATED_DON_VI_CODE);
        assertThat(testDuLieuBaoCao.getGiaTri()).isEqualTo(UPDATED_GIA_TRI);
    }

    @Test
    public void updateNonExistingDuLieuBaoCao() throws Exception {
        int databaseSizeBeforeUpdate = duLieuBaoCaoRepository.findAll().size();

        // Create the DuLieuBaoCao
        DuLieuBaoCaoDTO duLieuBaoCaoDTO = duLieuBaoCaoMapper.toDto(duLieuBaoCao);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDuLieuBaoCaoMockMvc.perform(put("/api/du-lieu-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DuLieuBaoCao in the database
        List<DuLieuBaoCao> duLieuBaoCaoList = duLieuBaoCaoRepository.findAll();
        assertThat(duLieuBaoCaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteDuLieuBaoCao() throws Exception {
        // Initialize the database
        duLieuBaoCaoRepository.save(duLieuBaoCao);

        int databaseSizeBeforeDelete = duLieuBaoCaoRepository.findAll().size();

        // Delete the duLieuBaoCao
        restDuLieuBaoCaoMockMvc.perform(delete("/api/du-lieu-bao-caos/{id}", duLieuBaoCao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DuLieuBaoCao> duLieuBaoCaoList = duLieuBaoCaoRepository.findAll();
        assertThat(duLieuBaoCaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DuLieuBaoCao.class);
        DuLieuBaoCao duLieuBaoCao1 = new DuLieuBaoCao();
        duLieuBaoCao1.setId("id1");
        DuLieuBaoCao duLieuBaoCao2 = new DuLieuBaoCao();
        duLieuBaoCao2.setId(duLieuBaoCao1.getId());
        assertThat(duLieuBaoCao1).isEqualTo(duLieuBaoCao2);
        duLieuBaoCao2.setId("id2");
        assertThat(duLieuBaoCao1).isNotEqualTo(duLieuBaoCao2);
        duLieuBaoCao1.setId(null);
        assertThat(duLieuBaoCao1).isNotEqualTo(duLieuBaoCao2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DuLieuBaoCaoDTO.class);
        DuLieuBaoCaoDTO duLieuBaoCaoDTO1 = new DuLieuBaoCaoDTO();
        duLieuBaoCaoDTO1.setId("id1");
        DuLieuBaoCaoDTO duLieuBaoCaoDTO2 = new DuLieuBaoCaoDTO();
        assertThat(duLieuBaoCaoDTO1).isNotEqualTo(duLieuBaoCaoDTO2);
        duLieuBaoCaoDTO2.setId(duLieuBaoCaoDTO1.getId());
        assertThat(duLieuBaoCaoDTO1).isEqualTo(duLieuBaoCaoDTO2);
        duLieuBaoCaoDTO2.setId("id2");
        assertThat(duLieuBaoCaoDTO1).isNotEqualTo(duLieuBaoCaoDTO2);
        duLieuBaoCaoDTO1.setId(null);
        assertThat(duLieuBaoCaoDTO1).isNotEqualTo(duLieuBaoCaoDTO2);
    }
}
