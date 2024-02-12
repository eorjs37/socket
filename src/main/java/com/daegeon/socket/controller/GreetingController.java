package com.daegeon.socket.controller;

import com.daegeon.socket.model.Greeting;
import com.daegeon.socket.model.HelloMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
@Slf4j
public class GreetingController {
    @MessageMapping(value = "/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws  Exception{
        Thread.sleep(3000);
        return  new Greeting(HtmlUtils.htmlEscape(message.getName())+"!!");
    }
}
