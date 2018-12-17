package com.jw.demoMaster.executor.actor;


import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.jw.demoMaster.executor.ActorFactory;
import com.jw.demoMaster.message.StationRegisterMessage;
import com.jw.demoMaster.message.TagRegisterMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 转发器
 * note：转发器的线程池独立出来，不要共用
 *
 * @author guest
 */
@Component
@Scope("prototype")
public class DispatcherActor extends AbstractActor {

    private final ActorFactory actorFactory;

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Autowired
    public DispatcherActor(ActorFactory actorFactory) {
        this.actorFactory = actorFactory;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(TagRegisterMessage.class, s -> {
                    System.out.println("reg");
                    actorFactory.getRegisterActor().tell(s, ActorRef.noSender());
                })
                .build();
    }
}

