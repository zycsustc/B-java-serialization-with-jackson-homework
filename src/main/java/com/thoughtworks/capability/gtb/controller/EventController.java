package com.thoughtworks.capability.gtb.controller;

import com.thoughtworks.capability.gtb.vo.EventType;
import com.thoughtworks.capability.gtb.vo.EventVo;
import com.thoughtworks.capability.gtb.vo.UserVo;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author itutry
 * @create 2020-05-21_16:29
 */
@RestController
@Slf4j
public class EventController {

  @GetMapping("/events/{id}")
  public EventVo getEventById(@PathVariable("id") String id) {
    UserVo user = new UserVo("3", "张三");
    return new EventVo(id, "下载文件", EventType.DOWNLOAD, LocalDateTime.now(), user);
  }

  @PostMapping("/events")
  public String createEvent(@RequestBody EventVo event) {
    System.out.println("We got "+event);
    return ("create event: " + event);
  }
}
