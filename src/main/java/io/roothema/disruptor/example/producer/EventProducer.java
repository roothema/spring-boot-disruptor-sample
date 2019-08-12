package io.roothema.disruptor.example.producer;

import com.lmax.disruptor.RingBuffer;
import io.roothema.disruptor.example.event.ValueEvent;

public interface EventProducer {

    void startProducing(final int count);

}
