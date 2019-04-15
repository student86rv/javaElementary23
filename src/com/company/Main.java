package com.company;

public class Main {

    public static void main(String[] args) {

            StorageDao storageDao = new StorageDao();
            storageDao.removeAll();

            User user1 = new User("Alex", 25);
            storageDao.addUser(user1);

            User user2 = new User("Oleg", 20);
            storageDao.addUser(user2);

            User user3 = new User("Dmitrii", 27);
            storageDao.addUser(user3);

            User user4 = new User("Anna", 19);
            storageDao.addUser(user4);

            User user5 = new User("Andrey", 31);
            storageDao.addUser(user5);

            System.out.println("Users: " + storageDao.getAllUsers());

            storageDao.updateUser(new User(user1.getId(), "Victor", 24));
            System.out.println("Update user 1...");
            System.out.println("Users: " + storageDao.getAllUsers());

            System.out.println("Get user 2...");
            System.out.println("User 2: " + storageDao.getUser(user2.getId()));

            storageDao.removeUser(user3.getId());
            System.out.println("Remove user 3...");
            System.out.println("Users: " + storageDao.getAllUsers());

            storageDao.removeUserByName("Anna");
            System.out.println("Remove user Anna...");
            System.out.println("Users: " + storageDao.getAllUsers());

            storageDao.removeAll();
            System.out.println("Remove all users...");
            System.out.println("Users: " + storageDao.getAllUsers());

            storageDao.close();
    }
}
