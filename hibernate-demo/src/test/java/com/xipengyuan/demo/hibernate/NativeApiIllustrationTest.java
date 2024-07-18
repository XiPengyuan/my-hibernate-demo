package com.xipengyuan.demo.hibernate;

import com.xipengyuan.demo.hibernate.entity.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class NativeApiIllustrationTest {

    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        // 一个应用只需设置一次SessionFactory
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // 配置hibernate.cfg.xml中的设置
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // registry本会被SessionFactory销毁，
            // 但我们在构建SessionFactory时遇到了麻烦，所以手动销毁它
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }
    }

    @Test
    void testBasicUsage() {
        // 创建几个事件……
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Event("第一个事件", new Date()));
        session.save(new Event("后续事件", new Date()));
        session.getTransaction().commit();
        session.close();

        // 从数据库中提取事件并列出
        session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Event").list();
        for (Event event : (List<Event>) result) {
            System.out.println("Event (" + event.getDate() + "): " + event.getTitle());
        }
        session.getTransaction().commit();
        session.close();
    }

    @AfterEach
    void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
