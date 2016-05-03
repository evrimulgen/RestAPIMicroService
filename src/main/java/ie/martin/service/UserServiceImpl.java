package ie.martin.service;

import ie.martin.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 18/02/16.
 */
@Service
public class UserServiceImpl implements UserService {


    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private DiscoveryClient discoveryClient;

    URI uriDBCommMS  = null;

    @Override
    public void getURIs() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<ServiceInstance> services1 = discoveryClient.getInstances("dbCommMS");
        uriDBCommMS = services1.get(0).getUri();

        System.out.println(uriDBCommMS);
    }


    @Override
    public UserDet createUser(UserDet u) {

        String latestDBURL = uriDBCommMS+"add-user/";

        RequestObject requestObject = new RequestObject();
        requestObject.setUser(u);

        ResponseObject response = restTemplate.postForObject(latestDBURL, requestObject, ResponseObject.class);

        return u;



       /* ResponseEntity<UserDet> responseEntity = restTemplate.getForEntity(latestDBURL, UserDet.class);
        UserDet u  = responseEntity.getBody();
        System.out.println("in here");*/
/*
        if(u.getStocks()==null)
        {
            ArrayList<PurchasedShare> shares = new ArrayList<PurchasedShare>();
           // UserDet usr = new UserDet();
            u.setName("Martin");
            u.setBalance(1000000);
            u.setStocks(shares);
        }

        else
        {
            System.out.println(u.toString());
        }*/
       // System.out.println("just below here"+u.toString());



    }

    @Override
    public UserDet getPortfolio(String username) {
        String getPortfolioURL = uriDBCommMS+"get-portfolio";

        UserDet user = restTemplate.postForObject(getPortfolioURL, username, UserDet.class);

        return user;

    }
}
 /*   ResponseEntity<ShareAnalysis[]> responseEntity = restTemplate.getForEntity(latestSharesURL, ShareAnalysis[].class);
    ShareAnalysis [] share = responseEntity.getBody();
    //List <Share> shares = responseEntity.getBody();
    List<ShareAnalysis> shares = new ArrayList<ShareAnalysis>(Arrays.asList(share));*/





