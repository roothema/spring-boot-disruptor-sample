package io.roothema.disruptor.example.event;

import com.lmax.disruptor.EventFactory;

public class ValueEvent {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public final static EventFactory<ValueEvent> EVENT_FACTORY = () -> new ValueEvent();

    @Override
    public String toString() {
        return "ValueEvent{" +
                "value=" + value +
                '}';
    }
}
