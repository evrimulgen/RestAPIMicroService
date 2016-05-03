package ie.martin.model;

import java.io.Serializable;

/**
 * Created by martin on 16/02/16.
 */
public class ResponseObject implements Serializable {

    public Boolean success  = false;
    public Integer statusCode  = null;
    public String status    = null;
    public Share share  = null;
}
