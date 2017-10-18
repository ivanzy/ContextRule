/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Param;
/**
 
 * @author fabrizio
 */
public class Param {

    // Metricas : 

    public static String name_experiment = "Exp";
   // public static final int number_of_devices = 600; // 50 - 200 - 600
//    public static final int number_of_topics = 1; // 1-5
//    public static final int number_of_events = 3; // 100
//    public static final int qos = 0; // 0 - 1 - 2
//    public static final int maxTimeBetweenEvents = 3000; // 120000 - 30000 - 5000 ms
//    public static final int minTimeBetweenEvents = 1000; // 60000 - 10000 - 1000 ms
//    public static final int number_of_messages = 3;
    public static  String path = "experimentoGabriela";
    public static int qos =0;
    public static  String address = "tcp://localhost:1883";
    //public static final String address = "tcp://localhost:1884";
    public static final String topic = "/ivan/publish";
    public static final String topic_publish = "/ivan/publish";

}