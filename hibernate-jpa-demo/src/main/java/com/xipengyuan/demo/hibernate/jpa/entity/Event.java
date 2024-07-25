package com.xipengyuan.demo.hibernate.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_events")
@NoArgsConstructor
@Getter
@Setter
public class Event {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "event_date")
    private Date date;

    public Event(String title, Date date) {
        this.title = title;
        this.date = date;
    }
}
