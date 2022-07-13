package edu.pdx.cs410J.tn27;

import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.*;

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {
  private final String customer;
  private final List<PhoneCall> call;
  public PhoneBill(String customer) {

    this.customer = customer;
    this.call = new ArrayList<PhoneCall>() {
    };

  }

  @Override
  public String getCustomer() {
    return this.customer;
  }

  //Function to add the new call at the end of the array
  @Override
  public void addPhoneCall(PhoneCall call) {

    if (call != null) {

      this.call.add(call);
    }

  }

  @Override
  public Collection<PhoneCall> getPhoneCalls() {
    return this.call;
  }
}
