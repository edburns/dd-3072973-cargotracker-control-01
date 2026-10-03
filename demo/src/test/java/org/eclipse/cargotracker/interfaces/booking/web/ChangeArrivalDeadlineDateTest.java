package org.eclipse.cargotracker.interfaces.booking.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.eclipse.cargotracker.interfaces.booking.facade.BookingServiceFacade;
import org.eclipse.cargotracker.interfaces.booking.facade.dto.CargoRoute;
import org.eclipse.cargotracker.interfaces.booking.facade.dto.Location;
import org.eclipse.cargotracker.interfaces.booking.facade.dto.RouteCandidate;
import org.junit.jupiter.api.Test;
import org.primefaces.PrimeFaces;

class ChangeArrivalDeadlineDateTest {

  @Test
  void loadRequestsTrackingIdAndConvertsDate() throws Exception {
    FakeBookingServiceFacade facade = new FakeBookingServiceFacade();
    facade.cargo = cargoWithDeadline(deadline());
    ChangeArrivalDeadlineDate editor = editorWithFacade(facade);
    editor.setTrackingId("ABC123");

    editor.load();

    assertEquals("ABC123", facade.loadedTrackingId);
    assertSame(facade.cargo, editor.getCargo());
    assertEquals(
        facade.cargo.getArrivalDeadlineDate(),
        new SimpleDateFormat("MM/dd/yyyy").format(editor.getArrivalDeadlineDate()));
  }

  @Test
  void loadRejectsMalformedDeadline() throws Exception {
    FakeBookingServiceFacade facade = new FakeBookingServiceFacade();
    facade.cargo =
        new CargoRoute("ABC123", "CHICAGO", "HELSINKI", deadline(), false, false, "", "") {
          @Override
          public String getArrivalDeadlineDate() {
            return "02/30/2024";
          }
        };
    ChangeArrivalDeadlineDate editor = editorWithFacade(facade);
    editor.setTrackingId("ABC123");

    assertThrows(javax.faces.FacesException.class, editor::load);
  }

  @Test
  void changeArrivalDeadlineRejectsNullSelection() throws Exception {
    FakeBookingServiceFacade facade = new FakeBookingServiceFacade();
    TestChangeArrivalDeadlineDate editor = new TestChangeArrivalDeadlineDate();
    setFacade(editor, facade);

    editor.changeArrivalDeadline();

    assertEquals("An arrival deadline date is required.", editor.errorMessage);
    assertEquals(0, facade.changeDeadlineCalls);
  }

  @Test
  void changeArrivalDeadlineDelegatesSelectionAndDoesNotCloseOnFailure() throws Exception {
    FakeBookingServiceFacade facade = new FakeBookingServiceFacade();
    RuntimeException failure = new RuntimeException("facade failure");
    facade.changeDeadlineFailure = failure;
    ChangeArrivalDeadlineDate editor = editorWithFacade(facade);
    editor.setTrackingId("ABC123");
    Date selectedDate = new Date(0);
    editor.setArrivalDeadlineDate(selectedDate);

    RuntimeException thrown = assertThrows(RuntimeException.class, editor::changeArrivalDeadline);

    assertSame(failure, thrown);
    assertEquals(1, facade.changeDeadlineCalls);
    assertEquals("ABC123", facade.changedTrackingId);
    assertSame(selectedDate, facade.changedDeadline);
  }

  @Test
  void changeArrivalDeadlineClosesAfterSuccessfulDelegation() throws Exception {
    FakeBookingServiceFacade facade = new FakeBookingServiceFacade();
    ChangeArrivalDeadlineDate editor = editorWithFacade(facade);
    editor.setTrackingId("ABC123");
    Date selectedDate = new Date(0);
    editor.setArrivalDeadlineDate(selectedDate);
    TestPrimeFaces primeFaces = new TestPrimeFaces();
    PrimeFaces.setCurrent(primeFaces);

    try {
      editor.changeArrivalDeadline();
    } finally {
      PrimeFaces.setCurrent(null);
    }

    assertEquals(1, facade.changeDeadlineCalls);
    assertEquals("ABC123", facade.changedTrackingId);
    assertSame(selectedDate, facade.changedDeadline);
    assertEquals(1, primeFaces.closeCalls);
    assertEquals("DONE", primeFaces.closeData);
  }

  private static ChangeArrivalDeadlineDate editorWithFacade(BookingServiceFacade facade)
      throws Exception {
    ChangeArrivalDeadlineDate editor = new ChangeArrivalDeadlineDate();
    setFacade(editor, facade);
    return editor;
  }

  private static void setFacade(ChangeArrivalDeadlineDate editor, BookingServiceFacade facade)
      throws Exception {
    Field facadeField = ChangeArrivalDeadlineDate.class.getDeclaredField("bookingServiceFacade");
    facadeField.setAccessible(true);
    facadeField.set(editor, facade);
  }

  private static class TestChangeArrivalDeadlineDate extends ChangeArrivalDeadlineDate {
    private String errorMessage;

    @Override
    void addErrorMessage(String summary) {
      errorMessage = summary;
    }
  }

  private static CargoRoute cargoWithDeadline(Date deadline) {
    return new CargoRoute("ABC123", "CHICAGO", "HELSINKI", deadline, false, false, "", "");
  }

  private static Date deadline() {
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(2024, Calendar.MAY, 21);
    return calendar.getTime();
  }

  private static class TestPrimeFaces extends PrimeFaces {
    private int closeCalls;
    private Object closeData;

    @Override
    public Dialog dialog() {
      return new Dialog() {
        @Override
        public void closeDynamic(Object data) {
          closeCalls++;
          closeData = data;
        }
      };
    }
  }

  private static class FakeBookingServiceFacade implements BookingServiceFacade {
    private CargoRoute cargo;
    private String loadedTrackingId;
    private int changeDeadlineCalls;
    private String changedTrackingId;
    private Date changedDeadline;
    private RuntimeException changeDeadlineFailure;

    @Override
    public String bookNewCargo(String origin, String destination, Date arrivalDeadline) {
      throw new UnsupportedOperationException();
    }

    @Override
    public CargoRoute loadCargoForRouting(String trackingId) {
      loadedTrackingId = trackingId;
      return cargo;
    }

    @Override
    public void assignCargoToRoute(String trackingId, RouteCandidate route) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void changeDestination(String trackingId, String destinationUnLocode) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void changeDeadline(String trackingId, Date arrivalDeadline) {
      changeDeadlineCalls++;
      changedTrackingId = trackingId;
      changedDeadline = arrivalDeadline;
      if (changeDeadlineFailure != null) {
        throw changeDeadlineFailure;
      }
    }

    @Override
    public List<RouteCandidate> requestPossibleRoutesForCargo(String trackingId) {
      throw new UnsupportedOperationException();
    }

    @Override
    public List<Location> listShippingLocations() {
      return new ArrayList<>();
    }

    @Override
    public List<CargoRoute> listAllCargos() {
      return new ArrayList<>();
    }
  }
}
