package edu.pdx.cs410J.tn27;

import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.Collection;

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {
  private final String customer;
  private PhoneCall [] call;
  public PhoneBill(String customer) {

    this.customer = customer;
    call = new PhoneCall[20];
  }

  @Override
  public String getCustomer() {
    return this.customer;
  }

  //Function to add the new call at the end of the array
  @Override
  public void addPhoneCall(PhoneCall call) {

    int length = this.call.length;
    if (length == 0){
      this.call[length] = call;
    }
    if (length<19){
    this.call[length+1] = call;
    }
  }

  @Override
  public Collection<PhoneCall> getPhoneCalls() {
    return null;
  }
}
