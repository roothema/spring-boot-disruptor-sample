package io.roothema.disruptor.example.producer;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import io.roothema.disruptor.example.event.ValueEvent;
import org.springframework.stereotype.Component;

@Component
public class SingleEventProducer implements EventProducer {

    private final Disruptor<ValueEvent> valueEventDisruptor;

    public SingleEventProducer(Disruptor<ValueEvent> valueEventDisruptor) {
        this.valueEventDisruptor = valueEventDisruptor;
    }

    @Override
    public void startProducing(int count) {
        final Runnable producer = () -> produce(valueEventDisruptor.getRingBuffer(), count);
        new Thread(producer).start();
    }

    private void produce(final RingBuffer<ValueEvent> ringBuffer, final int count) {
        for (int i = 0; i < count; i++) {
            final long seq = ringBuffer.next();
            final ValueEvent valueEvent = ringBuffer.get(seq);
            valueEvent.setValue(i);
            ringBuffer.publish(seq);
        }
    }
}
