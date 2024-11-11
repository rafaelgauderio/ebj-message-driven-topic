/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author rafael
 */
@Named(value = "jsfProcuderQueue")
@RequestScoped
public class JsfProcuderQueue {

    public JsfProcuderQueue() {
    }

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java/Fila")
    private Queue queue;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private int quantity = 1;

    public void send() {
        try {
            JMSContext jmsContext = connectionFactory.createContext();
            for (int i = 0; i<= quantity; i++) {
                jmsContext.createProducer().send(queue, message + " with id number " + i);
            }
        } catch (Exception exp) {
            System.out.println("Error");
            System.out.println(exp.getMessage());
        }
    }

}
