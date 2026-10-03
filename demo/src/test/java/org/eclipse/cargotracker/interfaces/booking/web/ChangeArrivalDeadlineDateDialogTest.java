package org.eclipse.cargotracker.interfaces.booking.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.primefaces.PrimeFaces;

class ChangeArrivalDeadlineDateDialogTest {

  @Test
  void showDialogOpensWithExpectedOptionsAndTrackingId() {
    TestPrimeFaces primeFaces = new TestPrimeFaces();
    PrimeFaces.setCurrent(primeFaces);

    try {
      new ChangeArrivalDeadlineDateDialog().showDialog("DEF789");
    } finally {
      PrimeFaces.setCurrent(null);
    }

    assertEquals("/admin/dialogs/changeArrivalDeadlineDate.xhtml", primeFaces.outcome);
    assertEquals(
        Map.of(
            "modal", true,
            "draggable", true,
            "resizable", false,
            "contentWidth", 410,
            "contentHeight", 280),
        primeFaces.options);
    assertEquals(Map.of("trackingId", List.of("DEF789")), primeFaces.params);
  }

  @Test
  void cancelClosesWithEmptyResult() {
    TestPrimeFaces primeFaces = new TestPrimeFaces();
    PrimeFaces.setCurrent(primeFaces);

    try {
      new ChangeArrivalDeadlineDateDialog().cancel();
    } finally {
      PrimeFaces.setCurrent(null);
    }

    assertEquals("", primeFaces.closeData);
  }

  private static class TestPrimeFaces extends PrimeFaces {
    private String outcome;
    private Map<String, Object> options;
    private Map<String, List<String>> params;
    private Object closeData;

    @Override
    public Dialog dialog() {
      return new Dialog() {
        @Override
        public void openDynamic(
            String outcome, Map<String, Object> options, Map<String, List<String>> params) {
          TestPrimeFaces.this.outcome = outcome;
          TestPrimeFaces.this.options = options;
          TestPrimeFaces.this.params = params;
        }

        @Override
        public void closeDynamic(Object data) {
          closeData = data;
        }
      };
    }
  }
}
