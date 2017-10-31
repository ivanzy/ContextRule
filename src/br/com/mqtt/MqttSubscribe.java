package br.com.mqtt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Objects;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import br.com.Param.Param;
import br.ufabc.context.FireRules;
import br.ufabc.context.Message;

public class MqttSubscribe implements MqttCallback,Runnable  {
	protected String address;
	protected String topic;
    public MqttSubscribe(String address, String topic) {
    	this.topic = topic;
    	this.address = address;
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < args.length; i++) {
//           
//            if (args[i].equalsIgnoreCase("-nome")) {
//                Param.path += args[i + 1];
//            }
//            else if(args[i].equalsIgnoreCase("-h")) {
//                Param.address = args[i + 1];
//
//            }
//        }
//        new MqttSubscribe().subscribe();
//    }
    
    
    public static int cont = 0;
	@Override
	public void run() {
		this.subscribe();
	}
    
    public void subscribe() {
        try {
            MqttClientPersistence m = new MemoryPersistence();
            MqttClient client = new MqttClient(this.address, "experimentoG", m);
            client.setCallback(this);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName("adm");
            options.setPassword("pass".toCharArray());

            client.connect(options);
            System.out.println("Subscribing in address " + this.address + " and topic " + this.topic);
            client.subscribe(Param.topic);
            try {
                System.in.read();
            } catch (IOException e) {
                // If we can't read we'll just exit
            }

            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
            System.out.println("erro: " + e.getMessage().toString());
        }
    }

    public void connectionLost(Throwable msg) {
        System.out.println("erro: " + msg.getMessage() + "  " + msg.getCause());
        msg.printStackTrace();
    }

    public void deliveryComplete(IMqttDeliveryToken arg0) {
        System.out.println("Delivery completed.");
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        //  String time = new Timestamp(System.currentTimeMillis()).toString();
        //type=poste;resource=#;message=Poste47;REP=1;P1=1504201346843;
        long time = (System.currentTimeMillis());
        System.out.println("Time:\t" + time
                + "  Topic:\t" + topic
                + "  Message:\t" + new String(message.getPayload())
                + "  QoS:\t" + message.getQos() + " cont: " + cont);
        String m = new String(message.getPayload());
        cont++;
        Message msg = encapsuleMessage(m, time);
        FireRules.fireRules(msg);
       // writeFile(m, time);

    }
    public Message encapsuleMessage(String m, long time) {
    	String[] msg = m.split(";");
        int i =0;
        String[] type = msg[i++].split("=");
        String[] resource = msg[i++].split("=");
        String[] payload = msg[i++].split("=");
        String[] rep = msg[i++].split("=");
        String[] p1 = msg[i++].split("=");
        Message message = new Message();
        message.setMessage(payload[1]);
        message.setP1(Long.parseLong(p1[1]));
        message.setP2(time);
        message.setRep(Integer.parseInt(rep[1]));
        message.setResource(resource[1]);
        
        return message;

    }
//UID=1;type=traffic;id=100068;message=72.89475,43.425323,16.28621,35.467022,75.55648,76.651764,78.75302,52.171783,27.653915,61.379642,3.7755003,67.0252,46.11978,43.396996,46.076477,62.13194,36.7783,78.21247,82.160355,18.115602,;P1=1474392066363;P2=1474392066370;P3=1474392067383;P4=1474392067421;P5=?;P6=?;P7=?;REP=1;EXP=1
    public void writeFile(String m, long time) throws IOException {
        //boolean erro = false; 
        String[] msg = m.split(";");
        String[] type = msg[0].split("=");
        int i =1;
        String[] resource = msg[i++].split("=");
        String[] message = msg[i++].split("=");
        String[] rep = msg[i++].split("=");
        String[] exp = msg[i++].split("=");
        String[] p1 = msg[i++].split("=");
        String[] p2 = msg[i++].split("=");
        String[] p3 = msg[i++].split("=");
        String[] p4 = msg[i++].split("=");
        String[] p5 = msg[i++].split("=");
        String[] p6 = msg[i++].split("=");
        String[] p7 = msg[i++].split("=");
        String p8 = String.valueOf(time);


       // Param.path += exp[1]; 
        File arquivo = new File(Param.path +exp[1]+ ".csv");
        try (FileWriter fw = new FileWriter(arquivo, true); BufferedWriter bw = new BufferedWriter(fw)) {
            m = "\"" + exp[1] + "\"" + ";" + "\"" + rep[1] + "\"" + ";" + "\"" + type[1] + "\"" + ";" + "\"" + resource[1] + "\"" + ";" + "\"" + p1[1] + "\"" + ";";
            m+=  "\"" + p2[1] + "\"" + ";";
            m+=  "\"" + p3[1] + "\"" + ";";
            m+=  "\"" + p4[1] + "\"" + ";";
            m+=  "\"" + p5[1] + "\"" + ";";
            m+=  "\"" + p6[1] + "\"" + ";";
            m+=  "\"" + p7[1] + "\"" + ";";
            m+=  "\"" + p8 + "\"" + ";";

            // devNo;msgNo;time
            System.out.println(m);
            bw.write(m);
            bw.newLine();

        }
    }


}
