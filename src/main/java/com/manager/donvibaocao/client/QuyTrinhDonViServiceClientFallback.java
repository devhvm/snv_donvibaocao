package com.manager.phathanhmaubaocao.client;

import com.manager.phathanhmaubaocao.service.dto.quytrinhdonvi.DuLieuTienTrinhDTO;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class QuyTrinhDonViServiceClientFallback implements QuyTrinhDonViServiceClient {


    @Override
    public DuLieuTienTrinhDTO createDuLieuTienTrinh(@Valid DuLieuTienTrinhDTO duLieuTienTrinhDTO) {
        throw new RuntimeException("Loi truy cap service");
    }
}
