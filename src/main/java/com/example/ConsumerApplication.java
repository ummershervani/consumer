package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.*;
import org.springframework.messaging.SubscribableChannel;

import java.io.Serializable;

@EnableBinding(ConsumerApplication.Sink.class)
@SpringBootApplication
public class ConsumerApplication {

    // Sink application definition
    @StreamListener(Sink.SAMPLE)
    public void receive(Person person) {
        System.out.println("Received message from :  " + person.getFirstName() + " " + person.getLastName());
    }

    public interface Sink {
        String SAMPLE = "foo";

        @Input(SAMPLE)
        SubscribableChannel input();
    }

    static class Person implements Serializable {
        static final long serialVersionUID = 42L;

        public Person() {
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        private String firstName;
        private String lastName;


        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
