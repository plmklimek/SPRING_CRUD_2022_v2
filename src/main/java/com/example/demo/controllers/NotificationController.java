package com.example.demo.controllers;

import com.example.demo.models.Notification;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {
//    @GetMapping("/notify")
//    public String getNotification(){
//        Notification aSuper = new Notification("super");
//        template.convertAndSend("/topic/notification", aSuper.getMessage());
//        return "Notifications successfully sent to Angular !";
//    }
//
//    @PostMapping("/notify")
//    public String sendNotify(@RequestBody  HashMap<String, String> request){
//        template.convertAndSend("/topic/notification", new Notification(request.get("message")).getMessage());
//        return "Notifications successfully sent to Angular !";
//    }
    @MessageMapping("/notify")
    @SendTo("/topic/notification")
    public String sendNotify(Notification notification) throws Exception{
        Thread.sleep(1000);
        return String.format("Witaj: %s", notification.getMessage());
    }
}
