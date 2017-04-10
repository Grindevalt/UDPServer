package com.andersenlab;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Main {

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(5555);                             // Создаем серверный датаграмм соккет
            byte[] receiveData = new byte[1024];                                                     // Представляем наши данные в виде массива байтов
            byte[] sendData = new byte[1024];
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);  // Создаем пакет получаемых данных
                serverSocket.receive(receivePacket);                                                 // Получаем этот пакет через соккет
                String sentence = new String(receivePacket.getData());                               // Парсим строку из полученных байтов
                System.out.println("RECEIVED: " + sentence);                                         // Печатаем полученную строку
                InetAddress IPAAddress = receivePacket.getAddress();                                 // Вытягиваем адресс из полученного пакета
                int port = receivePacket.getPort();                                                  // Вытягиваем порт из полученного пакета
                String capitalizedSentence = sentence.toUpperCase();                                 // Модифицируем полученную строку
                sendData = capitalizedSentence.getBytes();                                           // Парсим строку в байты
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAAddress, port);   // Создаем пакет данных для отправки
                serverSocket.send(sendPacket);                                                                 // Отправляем пакет через соккет
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
