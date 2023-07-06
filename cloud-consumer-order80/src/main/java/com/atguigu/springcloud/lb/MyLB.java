package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger counter = new AtomicInteger(0);

    public final int getIncrement() {
        int current;
        int next;
        do {
            current = counter.get();
            next = current >= Integer.MAX_VALUE ? 0 : current+1;
        } while (!counter.compareAndSet(current, next));
        System.out.println("*****next: "+next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int next = getIncrement();
        int index = next % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
