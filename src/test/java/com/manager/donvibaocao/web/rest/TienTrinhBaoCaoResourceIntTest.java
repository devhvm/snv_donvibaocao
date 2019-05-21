package com.manager.donvibaocao.web.rest;

import com.manager.donvibaocao.DonvibaocaoApp;

import com.manager.donvibaocao.domain.TienTrinhBaoCao;
import com.manager.donvibaocao.repository.TienTrinhBaoCaoRepository;
import com.manager.donvibaocao.service.TienTrinhBaoCaoService;
import com.manager.donvibaocao.service.dto.TienTrinhBaoCaoDTO;
import com.manager.donvibaocao.service.mapper.TienTrinhBaoCaoMapper;
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
 * Test class for the TienTrinhBaoCaoResource REST controller.
 *
 * @see TienTrinhBaoCaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DonvibaocaoApp.class)
public class TienTrinhBaoCaoResourceIntTest {

    private static final String DEFAULT_TIEN_TRINH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TIEN_TRINH_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DU_LIEU_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DU_LIEU_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FROM_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_FROM_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TO_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_TO_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final Long DEFAULT_QUY_TRINH_DON_VI_ID = 1L;
    private static final Long UPDATED_QUY_TRINH_DON_VI_ID = 2L;

    private static final String DEFAULT_QUY_TRINH_DON_VI_NAME = "AAAAAAAAAA";
    private static final String UPDATED_QUY_TRINH_DON_VI_NAME = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.NEW;
    private static final Status UPDATED_STATUS = Status.ACTIVE;

    @Autowired
    private TienTrinhBaoCaoRepository tienTrinhBaoCaoRepository;

    @Autowired
    private TienTrinhBaoCaoMapper tienTrinhBaoCaoMapper;

    @Autowired
    private TienTrinhBaoCaoService tienTrinhBaoCaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restTienTrinhBaoCaoMockMvc;

    private TienTrinhBaoCao tienTrinhBaoCao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TienTrinhBaoCaoResource tienTrinhBaoCaoResource = new TienTrinhBaoCaoResource(tienTrinhBaoCaoService);
        this.restTienTrinhBaoCaoMockMvc = MockMvcBuilders.standaloneSetup(tienTrinhBaoCaoResource)
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
    public static TienTrinhBaoCao createEntity() {
        TienTrinhBaoCao tienTrinhBaoCao = new TienTrinhBaoCao()
            .tienTrinhCode(DEFAULT_TIEN_TRINH_CODE)
            .duLieuCode(DEFAULT_DU_LIEU_CODE)
            .name(DEFAULT_NAME)
            .fromUserId(DEFAULT_FROM_USER_ID)
            .toUserId(DEFAULT_TO_USER_ID)
            .note(DEFAULT_NOTE)
            .quyTrinhDonViId(DEFAULT_QUY_TRINH_DON_VI_ID)
            .quyTrinhDonViName(DEFAULT_QUY_TRINH_DON_VI_NAME)
            .status(DEFAULT_STATUS);
        return tienTrinhBaoCao;
    }

    @Before
    public void initTest() {
        tienTrinhBaoCaoRepository.deleteAll();
        tienTrinhBaoCao = createEntity();
    }

    @Test
    public void createTienTrinhBaoCao() throws Exception {
        int databaseSizeBeforeCreate = tienTrinhBaoCaoRepository.findAll().size();

        // Create the TienTrinhBaoCao
        TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO = tienTrinhBaoCaoMapper.toDto(tienTrinhBaoCao);
        restTienTrinhBaoCaoMockMvc.perform(post("/api/tien-trinh-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhBaoCaoDTO)))
            .andExpect(status().isCreated());

        // Validate the TienTrinhBaoCao in the database
        List<TienTrinhBaoCao> tienTrinhBaoCaoList = tienTrinhBaoCaoRepository.findAll();
        assertThat(tienTrinhBaoCaoList).hasSize(databaseSizeBeforeCreate + 1);
        TienTrinhBaoCao testTienTrinhBaoCao = tienTrinhBaoCaoList.get(tienTrinhBaoCaoList.size() - 1);
        assertThat(testTienTrinhBaoCao.getTienTrinhCode()).isEqualTo(DEFAULT_TIEN_TRINH_CODE);
        assertThat(testTienTrinhBaoCao.getDuLieuCode()).isEqualTo(DEFAULT_DU_LIEU_CODE);
        assertThat(testTienTrinhBaoCao.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTienTrinhBaoCao.getFromUserId()).isEqualTo(DEFAULT_FROM_USER_ID);
        assertThat(testTienTrinhBaoCao.getToUserId()).isEqualTo(DEFAULT_TO_USER_ID);
        assertThat(testTienTrinhBaoCao.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testTienTrinhBaoCao.getQuyTrinhDonViId()).isEqualTo(DEFAULT_QUY_TRINH_DON_VI_ID);
        assertThat(testTienTrinhBaoCao.getQuyTrinhDonViName()).isEqualTo(DEFAULT_QUY_TRINH_DON_VI_NAME);
        assertThat(testTienTrinhBaoCao.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    public void createTienTrinhBaoCaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tienTrinhBaoCaoRepository.findAll().size();

        // Create the TienTrinhBaoCao with an existing ID
        tienTrinhBaoCao.setId("existing_id");
        TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO = tienTrinhBaoCaoMapper.toDto(tienTrinhBaoCao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTienTrinhBaoCaoMockMvc.perform(post("/api/tien-trinh-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TienTrinhBaoCao in the database
        List<TienTrinhBaoCao> tienTrinhBaoCaoList = tienTrinhBaoCaoRepository.findAll();
        assertThat(tienTrinhBaoCaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkTienTrinhCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienTrinhBaoCaoRepository.findAll().size();
        // set the field null
        tienTrinhBaoCao.setTienTrinhCode(null);

        // Create the TienTrinhBaoCao, which fails.
        TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO = tienTrinhBaoCaoMapper.toDto(tienTrinhBaoCao);

        restTienTrinhBaoCaoMockMvc.perform(post("/api/tien-trinh-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<TienTrinhBaoCao> tienTrinhBaoCaoList = tienTrinhBaoCaoRepository.findAll();
        assertThat(tienTrinhBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDuLieuCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienTrinhBaoCaoRepository.findAll().size();
        // set the field null
        tienTrinhBaoCao.setDuLieuCode(null);

        // Create the TienTrinhBaoCao, which fails.
        TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO = tienTrinhBaoCaoMapper.toDto(tienTrinhBaoCao);

        restTienTrinhBaoCaoMockMvc.perform(post("/api/tien-trinh-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<TienTrinhBaoCao> tienTrinhBaoCaoList = tienTrinhBaoCaoRepository.findAll();
        assertThat(tienTrinhBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienTrinhBaoCaoRepository.findAll().size();
        // set the field null
        tienTrinhBaoCao.setName(null);

        // Create the TienTrinhBaoCao, which fails.
        TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO = tienTrinhBaoCaoMapper.toDto(tienTrinhBaoCao);

        restTienTrinhBaoCaoMockMvc.perform(post("/api/tien-trinh-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<TienTrinhBaoCao> tienTrinhBaoCaoList = tienTrinhBaoCaoRepository.findAll();
        assertThat(tienTrinhBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkFromUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienTrinhBaoCaoRepository.findAll().size();
        // set the field null
        tienTrinhBaoCao.setFromUserId(null);

        // Create the TienTrinhBaoCao, which fails.
        TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO = tienTrinhBaoCaoMapper.toDto(tienTrinhBaoCao);

        restTienTrinhBaoCaoMockMvc.perform(post("/api/tien-trinh-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<TienTrinhBaoCao> tienTrinhBaoCaoList = tienTrinhBaoCaoRepository.findAll();
        assertThat(tienTrinhBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkToUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienTrinhBaoCaoRepository.findAll().size();
        // set the field null
        tienTrinhBaoCao.setToUserId(null);

        // Create the TienTrinhBaoCao, which fails.
        TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO = tienTrinhBaoCaoMapper.toDto(tienTrinhBaoCao);

        restTienTrinhBaoCaoMockMvc.perform(post("/api/tien-trinh-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<TienTrinhBaoCao> tienTrinhBaoCaoList = tienTrinhBaoCaoRepository.findAll();
        assertThat(tienTrinhBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNoteIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienTrinhBaoCaoRepository.findAll().size();
        // set the field null
        tienTrinhBaoCao.setNote(null);

        // Create the TienTrinhBaoCao, which fails.
        TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO = tienTrinhBaoCaoMapper.toDto(tienTrinhBaoCao);

        restTienTrinhBaoCaoMockMvc.perform(post("/api/tien-trinh-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<TienTrinhBaoCao> tienTrinhBaoCaoList = tienTrinhBaoCaoRepository.findAll();
        assertThat(tienTrinhBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkQuyTrinhDonViIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienTrinhBaoCaoRepository.findAll().size();
        // set the field null
        tienTrinhBaoCao.setQuyTrinhDonViId(null);

        // Create the TienTrinhBaoCao, which fails.
        TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO = tienTrinhBaoCaoMapper.toDto(tienTrinhBaoCao);

        restTienTrinhBaoCaoMockMvc.perform(post("/api/tien-trinh-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<TienTrinhBaoCao> tienTrinhBaoCaoList = tienTrinhBaoCaoRepository.findAll();
        assertThat(tienTrinhBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkQuyTrinhDonViNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienTrinhBaoCaoRepository.findAll().size();
        // set the field null
        tienTrinhBaoCao.setQuyTrinhDonViName(null);

        // Create the TienTrinhBaoCao, which fails.
        TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO = tienTrinhBaoCaoMapper.toDto(tienTrinhBaoCao);

        restTienTrinhBaoCaoMockMvc.perform(post("/api/tien-trinh-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<TienTrinhBaoCao> tienTrinhBaoCaoList = tienTrinhBaoCaoRepository.findAll();
        assertThat(tienTrinhBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienTrinhBaoCaoRepository.findAll().size();
        // set the field null
        tienTrinhBaoCao.setStatus(null);

        // Create the TienTrinhBaoCao, which fails.
        TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO = tienTrinhBaoCaoMapper.toDto(tienTrinhBaoCao);

        restTienTrinhBaoCaoMockMvc.perform(post("/api/tien-trinh-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<TienTrinhBaoCao> tienTrinhBaoCaoList = tienTrinhBaoCaoRepository.findAll();
        assertThat(tienTrinhBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllTienTrinhBaoCaos() throws Exception {
        // Initialize the database
        tienTrinhBaoCaoRepository.save(tienTrinhBaoCao);

        // Get all the tienTrinhBaoCaoList
        restTienTrinhBaoCaoMockMvc.perform(get("/api/tien-trinh-bao-caos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tienTrinhBaoCao.getId())))
            .andExpect(jsonPath("$.[*].tienTrinhCode").value(hasItem(DEFAULT_TIEN_TRINH_CODE.toString())))
            .andExpect(jsonPath("$.[*].duLieuCode").value(hasItem(DEFAULT_DU_LIEU_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].fromUserId").value(hasItem(DEFAULT_FROM_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].toUserId").value(hasItem(DEFAULT_TO_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())))
            .andExpect(jsonPath("$.[*].quyTrinhDonViId").value(hasItem(DEFAULT_QUY_TRINH_DON_VI_ID.intValue())))
            .andExpect(jsonPath("$.[*].quyTrinhDonViName").value(hasItem(DEFAULT_QUY_TRINH_DON_VI_NAME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    public void getTienTrinhBaoCao() throws Exception {
        // Initialize the database
        tienTrinhBaoCaoRepository.save(tienTrinhBaoCao);

        // Get the tienTrinhBaoCao
        restTienTrinhBaoCaoMockMvc.perform(get("/api/tien-trinh-bao-caos/{id}", tienTrinhBaoCao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tienTrinhBaoCao.getId()))
            .andExpect(jsonPath("$.tienTrinhCode").value(DEFAULT_TIEN_TRINH_CODE.toString()))
            .andExpect(jsonPath("$.duLieuCode").value(DEFAULT_DU_LIEU_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.fromUserId").value(DEFAULT_FROM_USER_ID.toString()))
            .andExpect(jsonPath("$.toUserId").value(DEFAULT_TO_USER_ID.toString()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE.toString()))
            .andExpect(jsonPath("$.quyTrinhDonViId").value(DEFAULT_QUY_TRINH_DON_VI_ID.intValue()))
            .andExpect(jsonPath("$.quyTrinhDonViName").value(DEFAULT_QUY_TRINH_DON_VI_NAME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    public void getNonExistingTienTrinhBaoCao() throws Exception {
        // Get the tienTrinhBaoCao
        restTienTrinhBaoCaoMockMvc.perform(get("/api/tien-trinh-bao-caos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTienTrinhBaoCao() throws Exception {
        // Initialize the database
        tienTrinhBaoCaoRepository.save(tienTrinhBaoCao);

        int databaseSizeBeforeUpdate = tienTrinhBaoCaoRepository.findAll().size();

        // Update the tienTrinhBaoCao
        TienTrinhBaoCao updatedTienTrinhBaoCao = tienTrinhBaoCaoRepository.findById(tienTrinhBaoCao.getId()).get();
        updatedTienTrinhBaoCao
            .tienTrinhCode(UPDATED_TIEN_TRINH_CODE)
            .duLieuCode(UPDATED_DU_LIEU_CODE)
            .name(UPDATED_NAME)
            .fromUserId(UPDATED_FROM_USER_ID)
            .toUserId(UPDATED_TO_USER_ID)
            .note(UPDATED_NOTE)
            .quyTrinhDonViId(UPDATED_QUY_TRINH_DON_VI_ID)
            .quyTrinhDonViName(UPDATED_QUY_TRINH_DON_VI_NAME)
            .status(UPDATED_STATUS);
        TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO = tienTrinhBaoCaoMapper.toDto(updatedTienTrinhBaoCao);

        restTienTrinhBaoCaoMockMvc.perform(put("/api/tien-trinh-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhBaoCaoDTO)))
            .andExpect(status().isOk());

        // Validate the TienTrinhBaoCao in the database
        List<TienTrinhBaoCao> tienTrinhBaoCaoList = tienTrinhBaoCaoRepository.findAll();
        assertThat(tienTrinhBaoCaoList).hasSize(databaseSizeBeforeUpdate);
        TienTrinhBaoCao testTienTrinhBaoCao = tienTrinhBaoCaoList.get(tienTrinhBaoCaoList.size() - 1);
        assertThat(testTienTrinhBaoCao.getTienTrinhCode()).isEqualTo(UPDATED_TIEN_TRINH_CODE);
        assertThat(testTienTrinhBaoCao.getDuLieuCode()).isEqualTo(UPDATED_DU_LIEU_CODE);
        assertThat(testTienTrinhBaoCao.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTienTrinhBaoCao.getFromUserId()).isEqualTo(UPDATED_FROM_USER_ID);
        assertThat(testTienTrinhBaoCao.getToUserId()).isEqualTo(UPDATED_TO_USER_ID);
        assertThat(testTienTrinhBaoCao.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testTienTrinhBaoCao.getQuyTrinhDonViId()).isEqualTo(UPDATED_QUY_TRINH_DON_VI_ID);
        assertThat(testTienTrinhBaoCao.getQuyTrinhDonViName()).isEqualTo(UPDATED_QUY_TRINH_DON_VI_NAME);
        assertThat(testTienTrinhBaoCao.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    public void updateNonExistingTienTrinhBaoCao() throws Exception {
        int databaseSizeBeforeUpdate = tienTrinhBaoCaoRepository.findAll().size();

        // Create the TienTrinhBaoCao
        TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO = tienTrinhBaoCaoMapper.toDto(tienTrinhBaoCao);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTienTrinhBaoCaoMockMvc.perform(put("/api/tien-trinh-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienTrinhBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TienTrinhBaoCao in the database
        List<TienTrinhBaoCao> tienTrinhBaoCaoList = tienTrinhBaoCaoRepository.findAll();
        assertThat(tienTrinhBaoCaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteTienTrinhBaoCao() throws Exception {
        // Initialize the database
        tienTrinhBaoCaoRepository.save(tienTrinhBaoCao);

        int databaseSizeBeforeDelete = tienTrinhBaoCaoRepository.findAll().size();

        // Delete the tienTrinhBaoCao
        restTienTrinhBaoCaoMockMvc.perform(delete("/api/tien-trinh-bao-caos/{id}", tienTrinhBaoCao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TienTrinhBaoCao> tienTrinhBaoCaoList = tienTrinhBaoCaoRepository.findAll();
        assertThat(tienTrinhBaoCaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TienTrinhBaoCao.class);
        TienTrinhBaoCao tienTrinhBaoCao1 = new TienTrinhBaoCao();
        tienTrinhBaoCao1.setId("id1");
        TienTrinhBaoCao tienTrinhBaoCao2 = new TienTrinhBaoCao();
        tienTrinhBaoCao2.setId(tienTrinhBaoCao1.getId());
        assertThat(tienTrinhBaoCao1).isEqualTo(tienTrinhBaoCao2);
        tienTrinhBaoCao2.setId("id2");
        assertThat(tienTrinhBaoCao1).isNotEqualTo(tienTrinhBaoCao2);
        tienTrinhBaoCao1.setId(null);
        assertThat(tienTrinhBaoCao1).isNotEqualTo(tienTrinhBaoCao2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TienTrinhBaoCaoDTO.class);
        TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO1 = new TienTrinhBaoCaoDTO();
        tienTrinhBaoCaoDTO1.setId("id1");
        TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO2 = new TienTrinhBaoCaoDTO();
        assertThat(tienTrinhBaoCaoDTO1).isNotEqualTo(tienTrinhBaoCaoDTO2);
        tienTrinhBaoCaoDTO2.setId(tienTrinhBaoCaoDTO1.getId());
        assertThat(tienTrinhBaoCaoDTO1).isEqualTo(tienTrinhBaoCaoDTO2);
        tienTrinhBaoCaoDTO2.setId("id2");
        assertThat(tienTrinhBaoCaoDTO1).isNotEqualTo(tienTrinhBaoCaoDTO2);
        tienTrinhBaoCaoDTO1.setId(null);
        assertThat(tienTrinhBaoCaoDTO1).isNotEqualTo(tienTrinhBaoCaoDTO2);
    }
}
