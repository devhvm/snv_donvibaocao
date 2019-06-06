package com.manager.donvibaocao.repository;

import com.manager.donvibaocao.domain.DuLieuBaoCao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the DuLieuBaoCao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DuLieuBaoCaoRepository extends MongoRepository<DuLieuBaoCao, String> {

}
