package ie.martin.service;

import ie.martin.model.UserDet;

/**
 * Created by martin on 18/02/16.
 */
public interface UserService {

    UserDet createUser(UserDet u);
    UserDet getPortfolio();

}
