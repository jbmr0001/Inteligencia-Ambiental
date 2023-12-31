/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uja.ssccdd.probando.interfaz;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author Pc
 */
public class Interfaz extends javax.swing.JFrame {

    /**
     * Creates new form Interfaz
     */
    public Interfaz() throws MqttException {
        
        String broker = "tcp://platinumvulture693.cloud.shiftr.io:1883";
        String clientId = "Interfaz";
        //String topic = "mi-topic";
        
        MemoryPersistence persistence = new MemoryPersistence();

        
            MqttClient client = new MqttClient(broker, clientId, persistence);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName("*");
            options.setPassword("*".toCharArray());

            System.out.println("Conectando al broker: " + broker);
            client.connect(options);
            System.out.println("Conectado");

            //System.out.println("Suscribiéndose al topic: " + topic);
           
            System.out.println("Suscripción exitosa");
            
        
        initComponents();
        jButton1.setText("Subir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //System.out.println("Encender rojo");
                String mensaje="s";
                try {
                    client.publish("subir", new MqttMessage(mensaje.getBytes()));
                } catch (MqttException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jButton2.setText("Bajar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String mensaje="b";
                try {
                    client.publish("bajar", new MqttMessage(mensaje.getBytes()));
                } catch (MqttException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jButton3.setText("Parar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String mensaje="p";
                try {
                    client.publish("parar", new MqttMessage(mensaje.getBytes()));
                } catch (MqttException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    
        jButton5.setText("Apagar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String mensaje="a";
                try {
                    client.publish("apagar", new MqttMessage(mensaje.getBytes()));
                } catch (MqttException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        jButton3.setText("jButton3");

        jButton5.setText("jButton5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(27, 27, 27)
                        .addComponent(jButton3)))
                .addGap(58, 58, 58))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addGap(67, 67, 67)
                .addComponent(jButton5)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Interfaz().setVisible(true);
                } catch (MqttException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    
    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    // End of variables declaration//GEN-END:variables
}
