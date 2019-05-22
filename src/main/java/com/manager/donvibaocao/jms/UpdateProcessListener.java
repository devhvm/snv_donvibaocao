package com.manager.donvibaocao.jms;

import com.manager.donvibaocao.service.dto.TienTrinhBaoCaoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class UpdateProcessListener {
    private static Logger log = LoggerFactory.getLogger(UpdateProcessListener.class);

    @JmsListener(
        destination = "${spring.snv.process.updated:Consumer.SNV.VirtualTopic.PROCESS.UPDATED}",
        selector = "_type = 'TienTrinhBaoCaoDTO'"
    )
    public String onEvent(TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO) {
        log.info("received <" + tienTrinhBaoCaoDTO + ">");
        return tienTrinhBaoCaoDTO.toString();
    }
}
