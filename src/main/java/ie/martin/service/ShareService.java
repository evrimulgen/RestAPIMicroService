package ie.martin.service;

import java.util.List;

import ie.martin.model.CheckedItem;
import ie.martin.model.Share;
import ie.martin.model.ShareAnalysis;

public interface ShareService {

	void getURIs();
	
	List<ShareAnalysis> getLatestShares();

	String getHistoricalData(CheckedItem check);

	List<ShareAnalysis>getCachedShares();
	
	Double findShare();
	


}
