package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;
import java.util.Objects;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getSessionFactory();



    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "create table if not exists user(id bigint primary key not null auto_increment, name varchar(45), lastName varchar(45), age tinyint(3) )";
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        }catch (HibernateException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = " DROP table if exists user";
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        }catch (HibernateException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        String sql = "insert into user (age, lastName, name ) values (?, ?, ?)";
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(new User(name,lastName,age));
            transaction.commit();
        }catch (HibernateException e){
            if (Objects.nonNull(transaction)){
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        }catch (HibernateException e){
            if (Objects.nonNull(transaction)){
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List <User> users;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery("from User", User.class) ;
            users = query.list();
            transaction.commit();
            System.out.println(users.toString());
        }catch (HibernateException e){
            if (Objects.nonNull(transaction)){
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "truncate table user";
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        }catch (HibernateException e){
            throw new RuntimeException(e);
        }
    }
}
