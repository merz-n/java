package org.example.dao;

import org.example.model.Address;

import java.util.List;

public interface AddressDao extends Dao<Address>{
    @Override
    Address findById(int id);
    List<Address> findByUserId(int userId);
}
