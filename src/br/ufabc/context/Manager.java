package br.ufabc.context;

import java.util.ArrayList;

import br.com.mqtt.MqttSubscribe;
import br.com.mqtt.MqttSubscribeRule;

public class Manager {
	private static ArrayList<Thread> threads = new ArrayList<Thread>();

	public static void main(String[] args) {
		System.out.println("************CONTEXT**RULE*************");
		String ruleAddress = null, ruleTopic = null;
		String brokerAddress = null, brokerTopic = null;

		// TODO CHECAR SE CONSEGUE PUBLICAR EM QUALQUER IP
		// Colocar as duas threads pra rodas: MqttSubscribe e MqttSubscribeRule
		// TODO fazer uma thread de gerenciamento para caso mude o endereço do server
		// que envia regras ou do broker

		// for (int i = 0; i < args.length; i++) {
		//
		// if (args[i].equalsIgnoreCase("-ruleAddress")) {
		// ruleAddress = args[i + 1];
		// }
		// else if(args[i].equalsIgnoreCase("-ruleTopic")) {
		// ruleTopic = args[i + 1];
		//
		// }
		// else if(args[i].equalsIgnoreCase("-brokerTopic")) {
		// brokerTopic = args[i + 1];
		//
		// }
		// else if(args[i].equalsIgnoreCase("-brokerAddress")) {
		// brokerAddress = args[i + 1];
		//
		// }
		// }

		ruleAddress = "tcp://localhost:1883";
		brokerAddress = "tcp://localhost:1883";
		ruleTopic = "/rule";
		brokerTopic = "/topic";

		MqttSubscribeRule mqttSubscribeRule = new MqttSubscribeRule(ruleAddress, ruleTopic);
		MqttSubscribe mqttSubscribe = new MqttSubscribe(brokerAddress, brokerTopic);

		Thread thread = new Thread(mqttSubscribe);
		threads.add(thread);
		thread = new Thread(mqttSubscribeRule);
		threads.add(thread);

		for (Thread t : threads) {
			t.start();
		}
	}

}
