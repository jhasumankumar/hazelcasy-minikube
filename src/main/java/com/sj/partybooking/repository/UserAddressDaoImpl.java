package com.sj.partybooking.repository;


import com.sj.partybooking.domain.UserModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserAddressDaoImpl extends GenericDaoImpl<UserModel> implements UserAddressDao{

    @Override
    public UserModel saveOrUpdateUser(UserModel userModel) {
        UserModel saved = null;
        if (null != userModel) {
            if (userModel.getId() == null || userModel.getId() == 0) {
                saved = this.save(userModel);
            } else {
                saved = this.em.merge(userModel);
            }
        }
        return saved;
    }

    @Override
    public UserModel findByEmail(String email) {
        List<UserModel> list = new ArrayList<>();
        list = em.createNamedQuery("findByEmail", UserModel.class)
                .setParameter("email", email)
                .getResultList();
        return list.size() == 0 ? null : list.get(0);
    }
}