package com.manager.donvibaocao.web.rest;

import com.manager.donvibaocao.DonvibaocaoApp;

import com.manager.donvibaocao.domain.BaoCao;
import com.manager.donvibaocao.repository.BaoCaoRepository;
import com.manager.donvibaocao.service.BaoCaoService;
import com.manager.donvibaocao.service.dto.BaoCaoDTO;
import com.manager.donvibaocao.service.mapper.BaoCaoMapper;
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

import com.manager.donvibaocao.domain.enumeration.Status;
/**
 * Test class for the BaoCaoResource REST controller.
 *
 * @see BaoCaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DonvibaocaoApp.class)
public class BaoCaoResourceIntTest {

    private static final String DEFAULT_BAO_CAO_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BAO_CAO_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_MAU_BAO_CAO_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MAU_BAO_CAO_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.NEW;
    private static final Status UPDATED_STATUS = Status.ACTIVE;

    @Autowired
    private BaoCaoRepository baoCaoRepository;

    @Autowired
    private BaoCaoMapper baoCaoMapper;

    @Autowired
    private BaoCaoService baoCaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restBaoCaoMockMvc;

    private BaoCao baoCao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BaoCaoResource baoCaoResource = new BaoCaoResource(baoCaoService);
        this.restBaoCaoMockMvc = MockMvcBuilders.standaloneSetup(baoCaoResource)
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
    public static BaoCao createEntity() {
        BaoCao baoCao = new BaoCao()
            .baoCaoCode(DEFAULT_BAO_CAO_CODE)
            .mauBaoCaoCode(DEFAULT_MAU_BAO_CAO_CODE)
            .name(DEFAULT_NAME)
            .status(DEFAULT_STATUS);
        return baoCao;
    }

    @Before
    public void initTest() {
        baoCaoRepository.deleteAll();
        baoCao = createEntity();
    }

    @Test
    public void createBaoCao() throws Exception {
        int databaseSizeBeforeCreate = baoCaoRepository.findAll().size();

        // Create the BaoCao
        BaoCaoDTO baoCaoDTO = baoCaoMapper.toDto(baoCao);
        restBaoCaoMockMvc.perform(post("/api/bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baoCaoDTO)))
            .andExpect(status().isCreated());

        // Validate the BaoCao in the database
        List<BaoCao> baoCaoList = baoCaoRepository.findAll();
        assertThat(baoCaoList).hasSize(databaseSizeBeforeCreate + 1);
        BaoCao testBaoCao = baoCaoList.get(baoCaoList.size() - 1);
        assertThat(testBaoCao.getBaoCaoCode()).isEqualTo(DEFAULT_BAO_CAO_CODE);
        assertThat(testBaoCao.getMauBaoCaoCode()).isEqualTo(DEFAULT_MAU_BAO_CAO_CODE);
        assertThat(testBaoCao.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBaoCao.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    public void createBaoCaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = baoCaoRepository.findAll().size();

        // Create the BaoCao with an existing ID
        baoCao.setId("existing_id");
        BaoCaoDTO baoCaoDTO = baoCaoMapper.toDto(baoCao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBaoCaoMockMvc.perform(post("/api/bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baoCaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BaoCao in the database
        List<BaoCao> baoCaoList = baoCaoRepository.findAll();
        assertThat(baoCaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkBaoCaoCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = baoCaoRepository.findAll().size();
        // set the field null
        baoCao.setBaoCaoCode(null);

        // Create the BaoCao, which fails.
        BaoCaoDTO baoCaoDTO = baoCaoMapper.toDto(baoCao);

        restBaoCaoMockMvc.perform(post("/api/bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<BaoCao> baoCaoList = baoCaoRepository.findAll();
        assertThat(baoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkMauBaoCaoCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = baoCaoRepository.findAll().size();
        // set the field null
        baoCao.setMauBaoCaoCode(null);

        // Create the BaoCao, which fails.
        BaoCaoDTO baoCaoDTO = baoCaoMapper.toDto(baoCao);

        restBaoCaoMockMvc.perform(post("/api/bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<BaoCao> baoCaoList = baoCaoRepository.findAll();
        assertThat(baoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = baoCaoRepository.findAll().size();
        // set the field null
        baoCao.setName(null);

        // Create the BaoCao, which fails.
        BaoCaoDTO baoCaoDTO = baoCaoMapper.toDto(baoCao);

        restBaoCaoMockMvc.perform(post("/api/bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<BaoCao> baoCaoList = baoCaoRepository.findAll();
        assertThat(baoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = baoCaoRepository.findAll().size();
        // set the field null
        baoCao.setStatus(null);

        // Create the BaoCao, which fails.
        BaoCaoDTO baoCaoDTO = baoCaoMapper.toDto(baoCao);

        restBaoCaoMockMvc.perform(post("/api/bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<BaoCao> baoCaoList = baoCaoRepository.findAll();
        assertThat(baoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllBaoCaos() throws Exception {
        // Initialize the database
        baoCaoRepository.save(baoCao);

        // Get all the baoCaoList
        restBaoCaoMockMvc.perform(get("/api/bao-caos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(baoCao.getId())))
            .andExpect(jsonPath("$.[*].baoCaoCode").value(hasItem(DEFAULT_BAO_CAO_CODE.toString())))
            .andExpect(jsonPath("$.[*].mauBaoCaoCode").value(hasItem(DEFAULT_MAU_BAO_CAO_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    public void getBaoCao() throws Exception {
        // Initialize the database
        baoCaoRepository.save(baoCao);

        // Get the baoCao
        restBaoCaoMockMvc.perform(get("/api/bao-caos/{id}", baoCao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(baoCao.getId()))
            .andExpect(jsonPath("$.baoCaoCode").value(DEFAULT_BAO_CAO_CODE.toString()))
            .andExpect(jsonPath("$.mauBaoCaoCode").value(DEFAULT_MAU_BAO_CAO_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    public void getNonExistingBaoCao() throws Exception {
        // Get the baoCao
        restBaoCaoMockMvc.perform(get("/api/bao-caos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBaoCao() throws Exception {
        // Initialize the database
        baoCaoRepository.save(baoCao);

        int databaseSizeBeforeUpdate = baoCaoRepository.findAll().size();

        // Update the baoCao
        BaoCao updatedBaoCao = baoCaoRepository.findById(baoCao.getId()).get();
        updatedBaoCao
            .baoCaoCode(UPDATED_BAO_CAO_CODE)
            .mauBaoCaoCode(UPDATED_MAU_BAO_CAO_CODE)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS);
        BaoCaoDTO baoCaoDTO = baoCaoMapper.toDto(updatedBaoCao);

        restBaoCaoMockMvc.perform(put("/api/bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baoCaoDTO)))
            .andExpect(status().isOk());

        // Validate the BaoCao in the database
        List<BaoCao> baoCaoList = baoCaoRepository.findAll();
        assertThat(baoCaoList).hasSize(databaseSizeBeforeUpdate);
        BaoCao testBaoCao = baoCaoList.get(baoCaoList.size() - 1);
        assertThat(testBaoCao.getBaoCaoCode()).isEqualTo(UPDATED_BAO_CAO_CODE);
        assertThat(testBaoCao.getMauBaoCaoCode()).isEqualTo(UPDATED_MAU_BAO_CAO_CODE);
        assertThat(testBaoCao.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBaoCao.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    public void updateNonExistingBaoCao() throws Exception {
        int databaseSizeBeforeUpdate = baoCaoRepository.findAll().size();

        // Create the BaoCao
        BaoCaoDTO baoCaoDTO = baoCaoMapper.toDto(baoCao);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBaoCaoMockMvc.perform(put("/api/bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baoCaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BaoCao in the database
        List<BaoCao> baoCaoList = baoCaoRepository.findAll();
        assertThat(baoCaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteBaoCao() throws Exception {
        // Initialize the database
        baoCaoRepository.save(baoCao);

        int databaseSizeBeforeDelete = baoCaoRepository.findAll().size();

        // Delete the baoCao
        restBaoCaoMockMvc.perform(delete("/api/bao-caos/{id}", baoCao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BaoCao> baoCaoList = baoCaoRepository.findAll();
        assertThat(baoCaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BaoCao.class);
        BaoCao baoCao1 = new BaoCao();
        baoCao1.setId("id1");
        BaoCao baoCao2 = new BaoCao();
        baoCao2.setId(baoCao1.getId());
        assertThat(baoCao1).isEqualTo(baoCao2);
        baoCao2.setId("id2");
        assertThat(baoCao1).isNotEqualTo(baoCao2);
        baoCao1.setId(null);
        assertThat(baoCao1).isNotEqualTo(baoCao2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BaoCaoDTO.class);
        BaoCaoDTO baoCaoDTO1 = new BaoCaoDTO();
        baoCaoDTO1.setId("id1");
        BaoCaoDTO baoCaoDTO2 = new BaoCaoDTO();
        assertThat(baoCaoDTO1).isNotEqualTo(baoCaoDTO2);
        baoCaoDTO2.setId(baoCaoDTO1.getId());
        assertThat(baoCaoDTO1).isEqualTo(baoCaoDTO2);
        baoCaoDTO2.setId("id2");
        assertThat(baoCaoDTO1).isNotEqualTo(baoCaoDTO2);
        baoCaoDTO1.setId(null);
        assertThat(baoCaoDTO1).isNotEqualTo(baoCaoDTO2);
    }
}
