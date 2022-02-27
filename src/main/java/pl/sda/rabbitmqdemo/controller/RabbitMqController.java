package pl.sda.rabbitmqdemo.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RabbitMqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // w kolejce RabbitMQ musi być utworzona kolejka o nazwie "sda"
    // domyślnie kolejka jest dostępna pod adresem localhost:15672

    // GET /api/publish?message=alamakota
    @GetMapping("/publish")
    public String send(@RequestParam String message) {
        rabbitTemplate.convertAndSend("sda", message);
        return "sent";
    }

    // GET /api/receive
    @GetMapping("/receive")
    public String get() {
        Object message = rabbitTemplate.receiveAndConvert("sda");
        return message.toString();
    }
}
