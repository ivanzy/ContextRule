package br.com.mqtt;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import br.com.Param.Param;;

public class MqttPublish implements MqttCallback {

	protected CountDownLatch latch;
	protected MqttClient client;
	protected MqttConnectOptions options;
	protected Random fullRand = new Random();

	public void disconnect() {
		try {
			client.disconnect();
		} catch (MqttException ex) {
			// Logger.getLogger(TimeDrivenSensor.class.getName()).log(Level.SEVERE, null,
			// ex);
		}
	}

	public void publish(String m, String topic) throws IOException {
		if (client == null) {
			this.connectMQTT(Param.address);
		}
		try {
			MqttMessage message = new MqttMessage();
			message.setPayload(m.getBytes());
			client.publish(topic, m.getBytes(), Param.qos, false);
		} catch (MqttPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		latch.countDown();
	}

	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
		// TODO Auto-generated method stub
	}

	public void connectMQTT(String address) {
		if (client == null) {
			try {
				client = new MqttClient(address, "teste" + fullRand.nextInt(9999999), null);
				client.setCallback(this);
				options = new MqttConnectOptions();
				options.setUserName("admin");
				options.setPassword("password".toCharArray());
				client.connect(options);
			} catch (MqttException e) {
				// Logger.getLogger(TimeDrivenSensor.class.getName()).log(Level.SEVERE, null,
				// e);
			}
		}
	}

}
