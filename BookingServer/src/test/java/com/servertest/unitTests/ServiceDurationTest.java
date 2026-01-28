package com.servertest.unitTests;

import com.server.service.domain.ServiceDuration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceDurationTest {

    @Test
    void ServiceDurationIsPositive() {
        ServiceDuration serviceDuration = new ServiceDuration(60);
        assertEquals(60, serviceDuration.minutes());
    }

    @Test
    void ServiceDurationIsZero_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new ServiceDuration(0));
    }

    @Test
    void ServiceDurationIsTooLong_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new ServiceDuration(9 * 60));
    }

}
