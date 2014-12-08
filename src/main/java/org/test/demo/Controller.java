package org.test.demo;

import java.net.URI;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.justinsb.etcd.EtcdClient;
import com.justinsb.etcd.EtcdClientException;
import com.justinsb.etcd.EtcdResult;


@RestController
public class Controller {
	
	@RequestMapping(value="/api/v1/counter",method=RequestMethod.GET)
	public int getCount() throws EtcdClientException {
		EtcdClient client = new EtcdClient(URI.create("http://54.183.252.233:4001/"));
		EtcdResult result ;
		String key = "/009340253";
		
		result = client.get(key);
		int counter = Integer.parseInt(result.node.value);
		System.out.println("Current value counter for "+key+" is: "+counter);
		counter++;
		
		result = client.set(key, String.valueOf(counter));
		System.out.println("New value counter for "+key+" is: "+counter);
		
		EtcdClient.close(null);
		return counter;
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String systemUp(){
		return "System is up!";
	}

}
