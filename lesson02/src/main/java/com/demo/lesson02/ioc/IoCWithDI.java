package com.demo.lesson02.ioc;

class IoCService {
    public void serve() {
        System.out.println("Service is running...");
    }
}

class IoCClient {
    private IoCService ioCService;

    public IoCClient(IoCService ioCService) {
        this.ioCService = ioCService;
    }

    public void doSomething() {
        ioCService.serve();
    }
}

public class IoCWithDI {
    public static void main(String[] args) {
        IoCService ioCService = new IoCService();
        IoCClient ioCClient = new IoCClient(ioCService);
        ioCClient.doSomething();
    }
}
