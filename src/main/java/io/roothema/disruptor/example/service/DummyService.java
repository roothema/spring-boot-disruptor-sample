package io.roothema.disruptor.example.service;

import io.roothema.disruptor.example.producer.EventProducer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@Service
public class DummyService {

    private final EventProducer eventProducer;

    public DummyService(EventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }

    public void dummy() {
        eventProducer.startProducing(50);
    }

    @Scheduled(fixedRate = 2000)
    private void scheduled() {
        dummy();
    }
}
