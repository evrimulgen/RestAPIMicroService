package ie.martin.rest;

import java.util.List;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import ie.martin.model.CheckedItem;
import ie.martin.model.RequestObject;
import ie.martin.model.ShareAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ie.martin.model.Share;
import ie.martin.service.ShareService;

@RestController
public class ShareRestAPI {
	
	@Autowired
	private ShareService share;

	@HystrixCommand(fallbackMethod = "getCachedShares")
	@RequestMapping(value="/get-latest-shares", method=RequestMethod.GET)
	public @ResponseBody List<ShareAnalysis> getLatestShares() {
		return share.getLatestShares();
	}

	@RequestMapping(value="get-cached-shares", method=RequestMethod.GET)
	public List<ShareAnalysis> getCachedShares()
	{
		List<ShareAnalysis> shares = share.getCachedShares();
		System.out.println("HERE IN FALLBACK_____£$%£");
		System.out.println(shares.toString());
		return shares;
	}
	@RequestMapping(value="/get-share", method=RequestMethod.GET)
	public Double getShare() {
		return share.findShare();
	}

	//the /get-historical-data endpoint which is called from the GUI
	@RequestMapping(value="/get-historical-data", method=RequestMethod.POST)
	public String getHistoricalShareData(@RequestBody RequestObject request)
	{
		CheckedItem item = request.getCheck();
	//	System.out.println(item.toString());
		return share.getHistoricalData(item);
	}
	
	/*public void cacheLatestSharesRedis() {
		return share.ca
	}*/


}
