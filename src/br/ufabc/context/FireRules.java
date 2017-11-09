package br.ufabc.context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.mqtt.MqttPublish;

public class FireRules {

	private static List<Rule> ruleList = new ArrayList<Rule>();
	private static MqttPublish mqttPublish;

	public FireRules() {
	}

	public static void fireRules(Message message) {
		System.out.println("Testing Rules...");

		for (Rule r : ruleList) {
			System.out.println("Type: "+message.getType()+ "  Rule type:"+ r.getType());

			if (r.getType().equalsIgnoreCase(message.getType())) {
				System.out.println("Rule Fired!");
				System.out.println("Message: "+message.getSendMessage() );

				mqttPublish = new MqttPublish(new Action(r.getAction().getTopic(), r.getAction().getAddress(),
						(message.getSendMessage() + "P2=" + System.currentTimeMillis()))); //rule ou a messagem define???
				try {
					mqttPublish.publish();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void addRule(Rule rule) {
		ruleList.add(rule);
		System.out.println("New Rule Added");
	}

	public static List<Rule> getRuleList() {
		return ruleList;
	}

	public static void deleteRule(Rule rule) {
		ruleList.remove(rule);
	}
	
	public static void editRule(Rule oldRule, Rule newRule) {
		deleteRule(oldRule);
		addRule(newRule);
	}

}
