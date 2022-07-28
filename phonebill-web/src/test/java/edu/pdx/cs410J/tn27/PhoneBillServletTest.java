package edu.pdx.cs410J.tn27;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

/**
 * A unit test for the {@link PhoneBillServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */


class PhoneBillServletTest {

  @Test
  void initiallyServletContainsNoDictionaryEntries() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);

    // Nothing is written to the response's PrintWriter
    verify(pw, never()).println(anyString());
    verify(response).setStatus(HttpServletResponse.SC_OK);
  }


  @Test
  void addOnePhoneBillToPhoneBillList() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    //String word = "TEST WORD";
    //String definition = "TEST DEFINITION";
    String customer = "TEST CUSTOMER";
    String caller = "123-456-7890";
    String callee = "456-789-1230";
    String begin_date = "01/12/2022", begin_time = "1:00", begin_zone = "am";
    String end_date = "01/12/2022", end_time = "1:20", end_zone = "am";
    PhoneCall new_call = new PhoneCall(caller,callee,begin_date,begin_time,begin_zone,
      end_date,end_time,end_zone);
    PhoneBill bill = new PhoneBill(customer);
    bill.addPhoneCall(new_call);

    HttpServletRequest request = mock(HttpServletRequest.class);

    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("caller")).thenReturn(caller);
    when(request.getParameter("callee")).thenReturn(callee);
    when(request.getParameter("begin_date")).thenReturn(begin_date);
    when(request.getParameter("begin_time")).thenReturn(begin_time);
    when(request.getParameter("begin_zone")).thenReturn(begin_zone);
    when(request.getParameter("end_date")).thenReturn(end_date);
    when(request.getParameter("end_time")).thenReturn(end_time);
    when(request.getParameter("end_zone")).thenReturn(end_zone);

    HttpServletResponse response = mock(HttpServletResponse.class);

    // Use a StringWriter to gather the text from multiple calls to println()
    StringWriter stringWriter = new StringWriter();
    PrintWriter pw = new PrintWriter(stringWriter, true);

    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);

    assertThat(stringWriter.toString(), equalTo("TEST CUSTOMER\r\nPhone call from 123-456-7890 to 456-789-1230 from 1/12/22, 1:00 AM to 1/12/22, 1:20 AM\r\n"));

    // Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
    ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
    verify(response).setStatus(statusCode.capture());

    assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));
     ;

    assertThat(servlet.getAPhoneBill(customer).toString(), equalTo(bill.toString()));
  }
  @Test
  void PostOnePhoneBillAndGetThatPhoneBill() throws IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    //String word = "TEST WORD";
    //String definition = "TEST DEFINITION";
    String customer = "TEST CUSTOMER";
    String caller = "123-456-7890";
    String callee = "456-789-1230";
    String begin_date = "01/12/2022", begin_time = "1:00", begin_zone = "am";
    String end_date = "01/12/2022", end_time = "1:20", end_zone = "am";
    PhoneCall new_call = new PhoneCall(caller,callee,begin_date,begin_time,begin_zone,
            end_date,end_time,end_zone);
    PhoneBill bill = new PhoneBill(customer);
    bill.addPhoneCall(new_call);

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("caller")).thenReturn(caller);
    when(request.getParameter("callee")).thenReturn(callee);
    when(request.getParameter("begin_date")).thenReturn(begin_date);
    when(request.getParameter("begin_time")).thenReturn(begin_time);
    when(request.getParameter("begin_zone")).thenReturn(begin_zone);
    when(request.getParameter("end_date")).thenReturn(end_date);
    when(request.getParameter("end_time")).thenReturn(end_time);
    when(request.getParameter("end_zone")).thenReturn(end_zone);
    HttpServletResponse response = mock(HttpServletResponse.class);

    // Use a StringWriter to gather the text from multiple calls to println()
    StringWriter stringWriter = new StringWriter();
    PrintWriter pw = new PrintWriter(stringWriter, true);

    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);

    servlet.doGet(request, response);

  }

}
