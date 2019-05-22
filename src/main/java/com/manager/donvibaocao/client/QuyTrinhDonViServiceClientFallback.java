package com.manager.donvibaocao.client;

import com.manager.donvibaocao.service.dto.TienTrinhBaoCaoDTO;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class QuyTrinhDonViServiceClientFallback implements QuyTrinhDonViServiceClient {


    @Override
    public TienTrinhBaoCaoDTO createDuLieuTienTrinh(@Valid TienTrinhBaoCaoDTO duLieuTienTrinhDTO) {
        throw new RuntimeException("Loi truy cap service");
    }
}
