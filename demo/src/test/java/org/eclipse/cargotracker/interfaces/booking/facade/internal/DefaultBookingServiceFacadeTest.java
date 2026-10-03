package org.eclipse.cargotracker.interfaces.booking.facade.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.eclipse.cargotracker.application.BookingService;
import org.eclipse.cargotracker.domain.model.cargo.TrackingId;
import org.junit.jupiter.api.Test;

class DefaultBookingServiceFacadeTest {

  @Test
  void changeDeadlineDelegatesOnceWithoutRepositoryAccess() throws Exception {
    AtomicInteger calls = new AtomicInteger();
    AtomicReference<TrackingId> receivedTrackingId = new AtomicReference<>();
    AtomicReference<Date> receivedDeadline = new AtomicReference<>();
    BookingService bookingService =
        (BookingService)
            Proxy.newProxyInstance(
                BookingService.class.getClassLoader(),
                new Class<?>[] {BookingService.class},
                (proxy, method, arguments) -> {
                  assertEquals("changeDeadline", method.getName());
                  calls.incrementAndGet();
                  receivedTrackingId.set((TrackingId) arguments[0]);
                  receivedDeadline.set((Date) arguments[1]);
                  return null;
                });
    DefaultBookingServiceFacade facade = new DefaultBookingServiceFacade();
    Field bookingServiceField =
        DefaultBookingServiceFacade.class.getDeclaredField("bookingService");
    bookingServiceField.setAccessible(true);
    bookingServiceField.set(facade, bookingService);
    Date arrivalDeadline = new Date(0);

    facade.changeDeadline("ABC123", arrivalDeadline);

    assertEquals(1, calls.get());
    assertEquals(new TrackingId("ABC123"), receivedTrackingId.get());
    assertSame(arrivalDeadline, receivedDeadline.get());
  }
}
