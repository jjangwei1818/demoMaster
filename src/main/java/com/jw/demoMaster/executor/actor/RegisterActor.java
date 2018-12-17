package com.jw.demoMaster.executor.actor;


import akka.actor.AbstractActor;
import com.jw.demoMaster.message.TagRegisterMessage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * 注册actor
 *
 * @author guest
 */
@Component
@Scope("prototype")
public class RegisterActor extends AbstractActor {


    @Override
    public Receive createReceive() {
        return receiveBuilder().match(TagRegisterMessage.class, s -> {


            for (int i = 0;i<10;i++)
            {
                System.out.println("================="+i);
//                TimeUnit.SECONDS.sleep(1);
                Thread.sleep(1000);
            }


        }).build();
    }
}