package com.example.rentalapp.events;

import com.example.rentalapp.model.domain.Host;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.time.LocalDateTime;

@Getter
public class HostChangedEvent extends ApplicationEvent {

    private LocalDateTime when;

    public HostChangedEvent(Object source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public HostChangedEvent(Object source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}
