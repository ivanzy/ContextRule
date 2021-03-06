package br.com.mqtt;

import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import br.com.Param.Param;
import br.ufabc.context.Action;
import br.ufabc.context.FireRules;
import br.ufabc.context.Rule;

public class MqttSubscribeRule extends MqttSubscribe {

	public MqttSubscribeRule(String address, String topic) {
		super(address, topic);
	}

	public static int cont = 0;

	@Override
	public void run() {
		this.subscribe();
	}

	public void subscribe() {
		try {
			MqttClientPersistence m = new MemoryPersistence();
			MqttClient client = new MqttClient(address, "Rule", m);
			client.setCallback(this);

			MqttConnectOptions options = new MqttConnectOptions();
			options.setUserName("adm");
			options.setPassword("pass".toCharArray());

			client.connect(options);
			System.out.println("RULE ENGINE: Subscribing in address " + this.address + " and topic " + this.topic);
			client.subscribe(topic);
			try {
				System.in.read();
			} catch (IOException e) {
				System.out.println("erro: " + e.getMessage().toString());
			}

			client.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
			System.out.println("erro: " + e.getMessage().toString());
		}
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("***************************NEW*RULE**********************************");
		long time = (System.currentTimeMillis());
		System.out.println("Time:\t" + time + "  Topic:\t" + topic + "  Message:\t" + new String(message.getPayload())
				+ "  QoS:\t" + message.getQos() + " cont: " + cont);
		System.out.println("*********************************************************************");
		String m = new String(message.getPayload());
		Rule rule = encapsuleRule(m);
		FireRules.addRule(rule);
		// writeFile(m, time);
	}

	public Rule encapsuleRule(String m) {
		Rule rule = new Rule();
		// rule: address=172.11.11.11;topic=/topic;operation=<18;type=air;

		String[] parse = m.split(";");
		String[] address = parse[0].split("=");
		String[] topic = parse[1].split("=");
		//System.out.println("Rule topic: "+topic[1]);
		String[] operation = parse[2].split("=");
		String[] type = parse[3].split("=");

		Action action = new Action();
		action.setAddress(address[1]);
		action.setTopic(topic[1]);
		rule.setAction(action);
		rule.setOperation(operation[1]);
		rule.setType(type[1]);

		return rule;
	}
}
