package com.sj.partybooking.repository;

import com.sj.partybooking.domain.UserModel;

public interface UserAddressDao extends GenericDao<UserModel> {

    UserModel saveOrUpdateUser(UserModel userModel);

    UserModel findByEmail(String email);

}
