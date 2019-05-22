package com.manager.donvibaocao.jms;

import com.manager.donvibaocao.service.dto.TienTrinhBaoCaoDTO;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProcessProducer {
    private static Logger log = LoggerFactory.getLogger(ProcessProducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void updateProcessing(String queueName, TienTrinhBaoCaoDTO tienTrinhBaoCaoDTO) {
        log.info("sending with convertAndSend() to topic <" + queueName + ">");
        //jmsTemplate.convertAndSend(queueName, demo);
        jmsTemplate.convertAndSend(new ActiveMQTopic(String.format("VirtualTopic.%s",queueName)), tienTrinhBaoCaoDTO);
    }
}
