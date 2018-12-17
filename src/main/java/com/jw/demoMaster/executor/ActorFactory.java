package com.jw.demoMaster.executor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ActorFactory {
    private final ActorSystem system;

    private ActorRef dispatcherActor;

    private ActorRef registerActor;

    @Autowired
    public ActorFactory(ActorSystem system) {
        this.system = system;
    }

    @PostConstruct
    public void init() {
        dispatcherActor = system.actorOf(SpringExtension.SpringExtProvider.get(system).props("dispatcherActor"), "dispatcherActor");
//        registerActor = system.actorOf(SpringExtension.SpringExtProvider.get(system).props("registerActor").withDispatcher("my-dispatcher"), "registerActor");
        registerActor = system.actorOf(SpringExtension.SpringExtProvider.get(system).props("registerActor"), "registerActor");

    }

    public ActorRef getDispatcherActor() {
        return dispatcherActor;
    }

    public ActorRef getRegisterActor() {
        return registerActor;
    }
}
