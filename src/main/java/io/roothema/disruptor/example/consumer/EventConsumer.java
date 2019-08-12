package io.roothema.disruptor.example.consumer;

import com.lmax.disruptor.EventHandler;

public interface EventConsumer {

    EventHandler[] getEventHandler();

}
