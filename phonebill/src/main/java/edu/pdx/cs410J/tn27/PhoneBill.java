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
  public void SortCollectionPhoneCalls() {
    //Assume that we already have a list of Phone Calls in random order.
    //Using selection sort to sort the time from most recent to the farthest"

    for (int left = 0; left < this.call.size() -1; ++left){

      for ( int right = left + 1; right < this.call.size(); ++right){
           if (this.call.get(left).compareTo(this.call.get(right)) < 0){
               Collections.swap(this.call,left,right);
           }
           else if (this.call.get(left).compareTo(this.call.get(right)) == 0){
                Collections.swap(this.call,left,right);
              }

           }
      }
    }
    public String PrintStandardOut(){
       PhoneCall call = this.call.get(0);
       String result = this.customer+ "\n" + call.getCaller()+ " "+ call.getCallee()+ " "+
               call.getBeginTimeString()+" "+ call.getEndTimeString();
       return result;

    }

  }
