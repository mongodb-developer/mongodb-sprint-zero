package com.example.ms0.model;

import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document("sandbox")
public class Thing {

        private int a;
        private Boolean example;
        private LocalDateTime time;
        
        public Thing(int a, Boolean example, LocalDateTime time) {
            super();
            this.a = a;
            this.example = example;
            this.time = time;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public Boolean isExample() {
            return example;
        }

        public void setExample(Boolean example) {
            this.example = example;
        }

        public LocalDateTime getTime() {
            return time;
        }

        public void setTime(LocalDateTime time) {
            this.time = time;
        }
}

