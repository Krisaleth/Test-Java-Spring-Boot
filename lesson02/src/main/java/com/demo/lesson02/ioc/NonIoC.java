package com.demo.lesson02.ioc;

class Service{
    public void serve() {
        System.out.println("Service is running!");
    }
}

class Client {
    private Service service;

    public Client() {
        service = new Service();
    }

    public void doSomething() {
        service.serve();
    }
}

public class NonIoC {
    public static void main(String[] args) {
        Client client = new Client();
        client.doSomething();
    }
}
