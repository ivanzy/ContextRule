package br.ufabc.context;

import java.util.ArrayList;
import java.util.List;

import br.com.mqtt.MqttPublish;

public class FireRules {

	private static List<Rule> ruleList = new ArrayList<Rule>();
	private static MqttPublish mqttPublish;

	public FireRules() {
	}

	public static void fireRules(Message message) {
		for (Rule r : ruleList) {
			if (r.getType().equalsIgnoreCase(message.getType())) {
				mqttPublish = new MqttPublish(new Action(r.getAction().getTopic(), r.getAction().getAddress(),
						(message.getMessage() + ";P2=" + System.currentTimeMillis())));
			}
		}
	}

	public static void addRule(Rule rule) {
		ruleList.add(rule);
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
