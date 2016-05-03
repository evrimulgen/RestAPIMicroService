package ie.martin.rest;

import ie.martin.model.RequestObject;
import ie.martin.model.UserDet;
import ie.martin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by martin on 18/02/16.
 */
@RestController
public class UserRestAPI {

    @Autowired
    private UserService user;

    @RequestMapping(value="/add-user", method = RequestMethod.POST)
    public UserDet addUser (@RequestBody RequestObject request)
    {
        UserDet u = request.getUser();
        return user.createUser(u);
    }

    @RequestMapping(value="/get-portfolio", method=RequestMethod.POST)
    public  UserDet getPortfolio(@RequestBody String username)
    {
        return user.getPortfolio(username);
    }
}


