package com.mywhm.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LBInterface {
   ServiceInstance instances(List<ServiceInstance>  serviceInstances);
}
