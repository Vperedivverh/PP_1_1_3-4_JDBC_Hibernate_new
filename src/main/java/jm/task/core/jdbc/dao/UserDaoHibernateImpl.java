package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {



    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users" +
                    " (id INT not null auto_increment, name VARCHAR(50), " +
                    "lastName VARCHAR(50), " +
                    "age INT, " +
                    "PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
            System.out.println("table created");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
            System.out.println("table drop");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.println("User deleted");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
        criteriaQuery.from(User.class);
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createQuery(criteriaQuery).getResultList();
        try {
            transaction.commit();
            return userList;
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery("TRUNCATE TABLE users;").executeUpdate();
            transaction.commit();
            System.out.println("table cleared");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
}

//    @Override
//    public void createUsersTable() {
//        try (Session session = Util.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users "
//                    +
//                    "(id INT NOT NULL AUTO_INCREMENT,  name VARCHAR(100), lastName VARCHAR(100), age INT, PRIMARY KEY (id))").executeUpdate();
//            transaction.commit();
//            System.out.println("sozdal table");
//        } catch (Exception e) {
//            System.out.println("ne sozdal table");
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void dropUsersTable() {
//        try (Session session = Util.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
//            transaction.commit();
//            System.out.println("dropnul table");
//        } catch (Exception e) {
//            System.out.println("ne sozdal table");
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//
//    @Override
//    public void saveUser(String name, String lastName, byte age) {
//        try(Session session = Util.getSessionFactory().openSession()){
//            Transaction transaction = session.beginTransaction();
//            session.save(new User(name, lastName, age));
//            transaction.commit();
//            System.out.println("dobavil usera");
//        }catch (Exception e) {
//            System.out.println("user ne dobavlen");
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void removeUserById(long id) {
//        try(Session session = Util.getSessionFactory().openSession()){
//        Transaction transaction = session.beginTransaction();
//        User user= session.get(User.class, id);
//        session.delete(user);
//        transaction.commit();
//            System.out.println("udalil usera");
//        }catch (Exception e) {
//            System.out.println("udalenie usera ne udalos");
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }}
//
//    @Override
//    public List<User> getAllUsers() {
//        List<User> userList = new ArrayList<>();
//        try (Session session = Util.getSessionFactory().openSession()){
//            Transaction transaction = session.beginTransaction();
//            userList = session.createSQLQuery("SELECT * FROM User").list();
//            transaction.commit();
//        }
//        catch (Exception e) {
//            System.out.println("vydat vseh userov ne udalos");
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//        return userList;
//    }
//
//
//
//
//
//    @Override
//    public void cleanUsersTable() {
//        try(Session session = Util.getSessionFactory().openSession()){
//            Transaction transaction = session.beginTransaction();
//            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
//
//            transaction.commit();
//            System.out.println("udalil table");
//        }catch (Exception e) {
//            System.out.println("udalenie table ne udalos");
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}

