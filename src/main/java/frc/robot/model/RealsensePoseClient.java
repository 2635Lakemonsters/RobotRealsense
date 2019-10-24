/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.model;

/**
 * Add your docs here.
 */
import java.io.*;  
import java.net.*;  
public class RealsensePoseClient {  
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
 
    public void startConnection(String ip, int port) {
    try {
        //InetAddress localHost = InetAddress.getLocalHost();
        InetAddress address = InetAddress.getByName(ip);
        clientSocket = new Socket(address, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    } catch(Exception e) {
        System.out.println(e);
    }
    }
 
    public Double[] sendMessage(String msg) throws IOException {
        String resp = "";
        Double[] respI = {0.0,0.0,0.0,0.0,0.0,0.0,0.0};



        char[] buff = msg.toCharArray();
        out.write(buff);
        out.flush();

        //out.println(msg);

        resp = in.readLine();

        //remove the brackets
        //as backslash mentioned, str.substring is a better approach than using str.replaceAll with regex
        
        //split the string into an array
        String[] strArray = resp.split(" ");
        
        for (int i = 0; i < strArray.length; i++) {
            respI[i] = Double.parseDouble(strArray[i]);
        }

        
        
 
    return respI;
    }
 
    public void stopConnection() {
    try {
        in.close();
        out.close();
        clientSocket.close();
    } catch(Exception e) {
        System.out.println(e);
    }
    }
}  
