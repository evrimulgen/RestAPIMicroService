package ie.martin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by martin on 16/02/16.
 */
public class RequestObject implements Serializable{

        public List<Share> shareList = null;

        public UserDet user = null;

        public CheckedItem check = null;

        public CheckedItem getCheck() {
                return check;
        }

        public void setCheck(CheckedItem check) {
                this.check = check;
        }

        public UserDet getUser() {
                return user;
        }

        public void setUser(UserDet user) {
                this.user = user;
        }

        public List<Share> getShareList() {
                return shareList;
        }

        public void setShareList(List<Share> shareList) {
                this.shareList = shareList;
        }
}
