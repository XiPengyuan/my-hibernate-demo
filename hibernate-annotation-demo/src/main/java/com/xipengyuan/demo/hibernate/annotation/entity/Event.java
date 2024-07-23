package com.xipengyuan.demo.hibernate.annotation.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_events")
@NoArgsConstructor
@Setter
public class Event {

    private Long id;

    @Getter
    private String title;

    private Date date;

    public Event(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "event_date")
    public Date getDate() {
        return date;
    }
}
