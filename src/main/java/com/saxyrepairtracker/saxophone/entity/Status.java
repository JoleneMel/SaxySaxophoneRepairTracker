package com.saxyrepairtracker.saxophone.entity;

public enum Status {
  AWAITING_ARRIVAL,
  WAITING_FOR_REPAIR, 
  BEING_REPAIRED, 
  WAITING_FOR_PARTS,
  READY_FOR_PICKUP,
  PICKED_UP;

  public boolean isEmpty() {
    // TODO Auto-generated method stub
    return false;
  }

}
