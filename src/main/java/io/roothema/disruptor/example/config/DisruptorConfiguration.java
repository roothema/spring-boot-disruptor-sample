package io.roothema.disruptor.example.config;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import io.roothema.disruptor.example.consumer.SingleEventPrintConsumer;
import io.roothema.disruptor.example.event.ValueEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadFactory;

@Configuration
public class DisruptorConfiguration {

    @Bean
    public Disruptor<ValueEvent> getValueEventDisruptor(SingleEventPrintConsumer singleEventPrintConsumer) {
        ThreadFactory threadFactory = DaemonThreadFactory.INSTANCE;

        WaitStrategy waitStrategy = new BusySpinWaitStrategy();
        Disruptor<ValueEvent> disruptor
                = new Disruptor<>(
                ValueEvent.EVENT_FACTORY,
                16,
                threadFactory,
                ProducerType.SINGLE,
                waitStrategy);

        disruptor.handleEventsWith(singleEventPrintConsumer.getEventHandler());

        disruptor.start();

        return disruptor;
    }
}

