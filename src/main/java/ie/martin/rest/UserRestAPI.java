package ie.martin.rest;

import ie.martin.model.UserDet;
import ie.martin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by martin on 18/02/16.
 */
@RestController
public class UserRestAPI {

    @Autowired
    private UserService user;

    @RequestMapping(value="/add-user", method = RequestMethod.GET)
    public UserDet addUser (UserDet u)
    {
        return user.createUser(u);
    }

    @RequestMapping(value="/get-portfolio", method=RequestMethod.GET)
    public  UserDet getPortfolio()
    {
        return user.getPortfolio();
    }
}


