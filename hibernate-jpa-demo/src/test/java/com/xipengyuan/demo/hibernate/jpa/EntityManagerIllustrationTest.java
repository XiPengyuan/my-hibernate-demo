package com.xipengyuan.demo.hibernate.jpa;

import com.xipengyuan.demo.hibernate.jpa.entity.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

class EntityManagerIllustrationTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        // 和SessionFactory一样，一个应用仅需设置一次entityManagerFactory
        //     重要：注意这里的名称与我们在persistence.xml中提供的persistence-unit的名称是如何匹配的
        entityManagerFactory = Persistence.createEntityManagerFactory("com.xipengyuan.demo.hibernate.jpa");
    }

    @Test
    void testBasicUsage() {
        // 创建几个事件……
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new Event("第一个事件", new Date()));
        entityManager.persist(new Event("后续事件", new Date()));
        entityManager.getTransaction().commit();
        entityManager.close();

        // 从数据库中提取事件并列出
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Event> result = entityManager.createQuery("from Event", Event.class).getResultList();
        for (Event event : result) {
            System.out.println("Event (" + event.getDate() + ") : " + event.getTitle());
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @AfterEach
    void tearDown() {
        entityManagerFactory.close();
    }
}
