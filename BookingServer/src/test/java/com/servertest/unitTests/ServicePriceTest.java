package com.servertest.unitTests;

import com.server.service.domain.ServicePrice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServicePriceTest {

    @Test
    void positivePrice_createsServicePrice() {
        ServicePrice price = new ServicePrice(100);
        assertEquals(100, price.price());
    }

    @Test
    void priceIsNegative_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new ServicePrice(-1));
    }

    @Test
    void priceIsZero_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new ServicePrice(0));
    }
}
