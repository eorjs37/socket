package com.daegeon.socket.controller;

import com.daegeon.socket.model.Blog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ScheduleController {

    @GetMapping("/api/getschedule")
    public Blog[] GetSchedule(){
        Blog[] blogArray = new Blog[3];

        for(int i = 0 ; i < 3 ; i++){
            Blog blog = new Blog();
            blog.setContent("블로그내용 "+(i+1));
            blog.setTitle("블로그제목 "+(i+1));
            blogArray[i] = blog;
        }

        return blogArray;
    }
}
