package com.manager.donvibaocao.repository;

import com.manager.donvibaocao.domain.TienTrinhBaoCao;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the TienTrinhBaoCao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TienTrinhBaoCaoRepository extends MongoRepository<TienTrinhBaoCao, String> {

}
