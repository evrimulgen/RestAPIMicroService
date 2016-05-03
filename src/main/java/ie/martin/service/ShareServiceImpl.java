package ie.martin.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ie.martin.model.*;
import org.aspectj.apache.bcel.generic.ArrayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ShareServiceImpl implements ShareService {

	@Autowired
	private DiscoveryClient discoveryClient;

	URI uriSharePriceMS = null;
	URI uriDBCommMS  = null;



	RestTemplate restTemplate = new RestTemplate();

	@Override
	public void getURIs() {
		//sleep for 10 s - allows others services to register with Eureka
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//gets back list of services with name matching one specified
		List<ServiceInstance> services = discoveryClient.getInstances("sharePriceMS");
		uriSharePriceMS = services.get(0).getUri();

		//System.out.println(uriSharePriceMS);

		//gets back list of services with name matching one specified
		List<ServiceInstance> services1 = discoveryClient.getInstances("dbCommMS");
		uriDBCommMS = services1.get(0).getUri();

		//System.out.println(uriDBCommMS);
	}

	@Override
	public List<ShareAnalysis> getLatestShares() {



		String latestSharesURL = uriSharePriceMS+"get-latest-shares";
		//here we query SharePriceMS for latest shares
		ResponseEntity<ShareAnalysis[]> responseEntity = restTemplate.getForEntity
				(latestSharesURL, ShareAnalysis[].class);
		ShareAnalysis [] share = responseEntity.getBody();
		//convert ResponseEntity to list of ShareAnalysis objects
		List<ShareAnalysis> shares = new ArrayList<ShareAnalysis>(Arrays.asList(share));

		if(shares!=null) {

			String latestDBURL = uriDBCommMS+"cache-shares-to-redis/";

			//now need to get share object into seperate arraylist from list of ShareAnalysis
			//create new list to store share name + price - to be cached in Redis
			List<Share> shareList = new ArrayList<Share>() ;

			for(ShareAnalysis s: shares)
			{
				shareList.add(s.getShare());
			}

			RequestObject requestObject = new RequestObject();
			//create shareList and add to requestObject
			requestObject.setShareList(shareList);

			ResponseObject response = restTemplate.postForObject(latestDBURL, requestObject, ResponseObject.class);


		}
		return shares;
	}

	@Override
	public String getHistoricalData(CheckedItem check) {

		RequestObject request = new RequestObject();
		request.setCheck(check);
		String shares = restTemplate.postForObject(uriSharePriceMS+"get-historical-data", request, String.class);

		return shares;

	}

	@Override
	public List<ShareAnalysis> getCachedShares() {
		List<Share>shares = restTemplate.getForObject(uriDBCommMS+"get-cached-shares", List.class);
		List<ShareAnalysis> shareAnal =new ArrayList<ShareAnalysis>();
		for(Share s:shares)
		{
			ShareAnalysis analysis = new ShareAnalysis();
			analysis.setShare(s);
			shareAnal.add(analysis);
		}
		return shareAnal;
	}

	@Override
	public Double findShare() {
		Double d = restTemplate.getForObject(uriDBCommMS+"get-share", Double.class);
		System.out.println("inside rest api share price is = " + d);
 		return d;
	}



}
