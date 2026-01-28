package com.servertest.tests.booking.infrastructure.persistence;

import com.server.ServerApp;
import com.server.booking.domain.Booking;
import com.server.booking.domain.BookingStatus;
import com.server.booking.domain.TimeSlot;
import com.server.booking.infrastructure.BookingJpaEntity;
import com.server.booking.infrastructure.BookingMapper;
import com.server.booking.infrastructure.JpaBookingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ContextConfiguration(classes = ServerApp.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers(disabledWithoutDocker = true)
public class BookingRepositoryTest {
    @Container
    static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:8.0")
                    .withDatabaseName("booking_test")
                    .withUsername("test")
                    .withPassword("test");

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Autowired
    JpaBookingRepository bookingRepository;

    BookingMapper bookingMapper = new BookingMapper();

    @Test
    void saveBooking_persistsAndLoadsFromDatabase() {
        TimeSlot timeSlot = new TimeSlot(
                LocalDateTime.now().plusHours(3),
                LocalDateTime.now().plusHours(4)
        );

        Booking booking = new Booking(
                0,
                1,
                2,
                3,
                timeSlot,
                BookingStatus.PENDING,
                LocalDateTime.now()
        );

        BookingJpaEntity saved =
                bookingRepository.save(bookingMapper.toEntity(booking));

        assertThat(saved).isNotNull();
        assertThat(saved.getStatus()).isEqualTo(BookingStatus.PENDING);
    }
}
