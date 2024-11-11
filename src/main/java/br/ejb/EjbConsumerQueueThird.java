package br.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Rafael de Luca
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup",
            propertyValue = "java/Fila"),
    @ActivationConfigProperty(propertyName = "destinationType",
            propertyValue = "javax.jms.Queue")
})
public class EjbConsumerQueueThird implements MessageListener {

    @Override
    public void onMessage(Message msg) {
        //throw new UnsupportedOperationException("Not supported yet."); 
        System.out.println("Message received by " + this.getClass().getSimpleName());
        try {
            TextMessage textMessage = (TextMessage) msg;
            System.out.println(textMessage.getText());
        } catch (Exception exce) {
            exce.getMessage();
        }
    }

}
