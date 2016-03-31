package ie.martin.service;

import java.util.List;

import ie.martin.model.Share;
import ie.martin.model.ShareAnalysis;

public interface ShareService {
	
	List<ShareAnalysis> getLatestShares();
	
	Double findShare();
	


}
