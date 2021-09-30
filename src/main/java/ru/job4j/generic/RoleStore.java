package ru.job4j.generic;

public class RoleStore implements Store<Role>{

    private final Store<Role> roles = new MemStore<>();

    @Override
    public void add(Role model) {
        roles.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return roles.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return roles.delete(id);
    }

    @Override
    public Role findById(String id) {
        return roles.findById(id);
    }
}
