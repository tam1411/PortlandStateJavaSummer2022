package edu.pdx.cs410J.tn27;

import com.google.common.annotations.VisibleForTesting;
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

  @VisibleForTesting
  public void SortCollectionPhoneCalls() throws Exception {
    //Assume that we already have a list of Phone Calls in random order.
    //Using selection sort to sort the time from most recent to the farthest"
    if (this.call == null) throw new Exception("Empty List");
    for (int left = 0; left < this.call.size() -1; ++left){

      for ( int right = left + 1; right < this.call.size(); ++right){
           Date date_1 = this.call.get(left).getBeginTime(); //"08/01"
           Date date_2 = this.call.get(right).getBeginTime();//"09/02"
           //date_1 before date 2
           if (date_1.compareTo(date_2) < 0){
               Collections.swap(this.call,left,right);
           }
           else if (date_1.compareTo(date_2) == 0){
              String caller1 = this.call.get(left).getCaller();
              String caller2 = this.call.get(right).getCaller();
              if (caller1.compareTo(caller2)  < 0){
                Collections.swap(this.call,left,right);
              }

           }
      }
    }
    //return this.call;
  }
}
