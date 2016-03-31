package ie.martin.rest;

import java.util.List;

import ie.martin.model.ShareAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ie.martin.model.Share;
import ie.martin.service.ShareService;

@RestController
public class ShareRestAPI {
	
	@Autowired
	private ShareService share;
	
	@RequestMapping(value="/get-share", method=RequestMethod.GET)
	public Double getShare() {
		return share.findShare();
	}
	
	@RequestMapping(value="/get-latest-shares", method=RequestMethod.GET)
	public @ResponseBody List<ShareAnalysis> getLatestShares() {
		return share.getLatestShares();
	}
	
	/*public void cacheLatestSharesRedis() {
		return share.ca
	}*/


}
