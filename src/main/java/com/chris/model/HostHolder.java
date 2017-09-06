package com.chris.model;

import org.springframework.stereotype.Component;

/**
 * 
 * 这个类类似缓存，因为数据这时候可以存储在这个类的实例中（内存中），而不是数据库中。因为servlet是多线程的，
 * 因此，需要用线程安全的ThreadLocal,而且是static的，因为只是在类中存储数据。
 */
@Component
public class HostHolder {
    private static ThreadLocal<User> users = new ThreadLocal<>();

    public User getUser() {
        return users.get();
    }

    public void setUser(User user) {
        users.set(user);
    }

    //移除当前线程里存储的数据副本
    public void clear() {
        users.remove();
    }
}
