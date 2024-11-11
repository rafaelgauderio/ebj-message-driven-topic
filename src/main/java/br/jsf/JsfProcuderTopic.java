package br.jsf;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author rafael
 */
@Named(value = "jsfProcuderTopic")
@RequestScoped
public class JsfProcuderTopic {

    public JsfProcuderTopic() {
    }

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java/Topic")
    private Topic topic;

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
                jmsContext.createProducer().send(topic, message + " with id number " + i);
            }
        } catch (Exception exp) {
            System.out.println("Error");
            System.out.println(exp.getMessage());
        }
    }

}
