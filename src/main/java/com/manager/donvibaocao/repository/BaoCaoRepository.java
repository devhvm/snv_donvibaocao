package com.manager.donvibaocao.repository;

import com.manager.donvibaocao.domain.BaoCao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data MongoDB repository for the BaoCao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BaoCaoRepository extends MongoRepository<BaoCao, String> {

    Optional<BaoCao> findByBaoCaoCode(String baoCaoCode);

    void deleteByBaoCaoCode(String baoCaoCode);
}
