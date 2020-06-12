package com.thoughtworks.capability.gtb.vo;

/**
 * @author itutry
 * @create 2020-05-21_16:26
 */
public enum EventType {
  UPLOAD("U"), DOWNLOAD("D");

  private String code;

  public EventType getEventType(String type){
    if(type.equals("DOWNLOAD")){
      return EventType.DOWNLOAD;
    } else {
      return EventType.UPLOAD;
    }
  }

  EventType(String code) {
    this.code = code;
  }
}
