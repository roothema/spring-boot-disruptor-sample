package io.roothema.disruptor.example.consumer;

import com.lmax.disruptor.EventHandler;
import io.roothema.disruptor.example.event.ValueEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SingleEventPrintConsumer implements EventConsumer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public EventHandler<ValueEvent>[] getEventHandler() {
        final EventHandler<ValueEvent> eventHandler = (event, sequence, endOfBatch) -> print(event.getValue(), sequence);
        return new EventHandler[] { eventHandler };
    }

    private void print(final int id, final long sequenceId) {
        logger.info("Id is " + id + " sequence id that was used is " + sequenceId);
    }
}
