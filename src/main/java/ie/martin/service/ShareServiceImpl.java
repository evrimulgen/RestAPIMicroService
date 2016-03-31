package ie.martin.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ie.martin.model.RequestObject;
import ie.martin.model.ResponseObject;
import ie.martin.model.ShareAnalysis;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ie.martin.model.Share;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ShareServiceImpl implements ShareService {

	final String uriSharePriceMS = "http://127.0.0.1:8081/";
	final String uriDBCommMS = "http://127.0.0.1:8082/";
		
	RestTemplate restTemplate = new RestTemplate();

	@Override
	public List<ShareAnalysis> getLatestShares() {

	//	List<Share> shares = restTemplate.getForObject(uriSharePriceMS+"get-latest-shares", List.class);
	String latestSharesURL = uriSharePriceMS+"get-latest-shares";
		ResponseEntity<ShareAnalysis[]> responseEntity = restTemplate.getForEntity(latestSharesURL, ShareAnalysis[].class);
		ShareAnalysis [] share = responseEntity.getBody();
		//List <Share> shares = responseEntity.getBody();
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


			/*UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(latestDBURL).queryParam("shares", shareList);
            java.net.URI buildUrl = builder.build().encode().toUri();*/


			RequestObject requestObject = new RequestObject();
			//create shareList and add to requestObject
			requestObject.setShareList(shareList);

			System.out.println(shareList.toString());
			ResponseObject response = restTemplate.postForObject(latestDBURL, requestObject, ResponseObject.class);


		}
		return shares;
	}

	@Override
	public Double findShare() {
		Double d = restTemplate.getForObject(uriDBCommMS+"get-share", Double.class);
		System.out.println("inside rest api share price is = " + d);
 		return d;
	}



}
