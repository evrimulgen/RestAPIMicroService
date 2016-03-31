package ie.martin.service;

import ie.martin.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * Created by martin on 18/02/16.
 */
@Service
public class UserServiceImpl implements UserService {
    final String uriDBCommMS = "http://127.0.0.1:8082/";

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public UserDet createUser(UserDet u) {

        String latestDBURL = uriDBCommMS+"add-user/";

        ArrayList<PurchasedShare> shares = new ArrayList<PurchasedShare>();
        UserDet usr = new UserDet();
        usr.setName("Martin1");
        usr.setBalance(1000000);
        usr.setStocks(shares);

        RequestObject requestObject = new RequestObject();
        requestObject.setUser(usr);

        ResponseObject response = restTemplate.postForObject(latestDBURL, requestObject, ResponseObject.class);

        return u;

    }

    @Override
    public UserDet getPortfolio() {
        String getPortfolioURL = uriDBCommMS+"get-portfolio";

        UserDet user = restTemplate.getForObject(getPortfolioURL, UserDet.class);

        return user;

    }
}
 /*   ResponseEntity<ShareAnalysis[]> responseEntity = restTemplate.getForEntity(latestSharesURL, ShareAnalysis[].class);
    ShareAnalysis [] share = responseEntity.getBody();
    //List <Share> shares = responseEntity.getBody();
    List<ShareAnalysis> shares = new ArrayList<ShareAnalysis>(Arrays.asList(share));*/





