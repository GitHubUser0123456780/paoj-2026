package com.pao.laboratory13.exercise1;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int nrOfCommands;
        Session sesiune = new Session();
        Scanner sc = new Scanner(System.in);
        nrOfCommands = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<nrOfCommands;i++)
        {
            String[] split = sc.nextLine().split(" ");
            try{
                switch (split[0]) {
                    case "AUTH":
                        if (split.length!=2)
                            throw new InvalidParametersException("ERR E_PARSE AUTH");
                        if (sesiune.getState() == SessionState.CLOSED)
                            throw new InvalidStateException("ERR E_STATE CLOSED");
                        sesiune.setState(SessionState.AUTH);
                        sesiune.setHistoryCount(0);
                        System.out.println("OK AUTH user=" + split[1]);
                        break;
                    case "OPEN":
                        if (split.length!=1)
                            throw new InvalidParametersException("ERR E_PARSE OPEN");
                        if (sesiune.getState() == SessionState.OPEN)
                            throw new InvalidStateException("ERR E_STATE ALREADY_OPEN");
                        if (sesiune.getState() == SessionState.CLOSED)
                            throw new InvalidStateException("ERR E_STATE CLOSED");
                        if (sesiune.getState() == SessionState.OPEN)
                            throw new InvalidStateException("ERR E_STATE NOT_OPEN");
                        sesiune.setState(SessionState.OPEN);
                        System.out.println("OK OPEN");
                        break;
                    case "SEND":
                        if (split.length!=2)
                            throw new InvalidParametersException("ERR E_PARSE SEND");
                        if (sesiune.getState() == SessionState.CLOSED)
                            throw new InvalidStateException("ERR E_STATE CLOSED");
                        if (sesiune.getState() != SessionState.OPEN)
                            throw new InvalidStateException("ERR E_STATE NOT_OPEN");
                        sesiune.setHistoryCount(sesiune.getHistoryCount() + 1);
                        System.out.println("OK OPEN sent");
                        break;
                    case "BROADCAST":
                        if (split.length!=2)
                            throw new InvalidParametersException("ERR E_PARSE BROADCAST");
                        if (sesiune.getState() == SessionState.CLOSED)
                            throw new InvalidStateException("ERR E_STATE CLOSED");
                        if (sesiune.getState() != SessionState.OPEN)
                            throw new InvalidStateException("ERR E_STATE NOT_OPEN");
                        sesiune.setHistoryCount(sesiune.getHistoryCount() + 1);
                        System.out.println("OK OPEN broadcast");
                        break;
                    case "HISTORY":
                        if (split.length!=1)
                            throw new InvalidParametersException("ERR E_PARSE HISTORY");
                        if (sesiune.getState() == SessionState.CLOSED)
                            throw new InvalidStateException("ERR E_STATE CLOSED");
                        if (sesiune.getState() != SessionState.OPEN)
                            throw new InvalidStateException("ERR E_STATE NOT_OPEN");
                        System.out.println("OK OPEN history="+sesiune.getHistoryCount());
                        break;
                    case "CLOSE":
                        if (split.length!=1)
                            throw new InvalidParametersException("ERR E_PARSE CLOSED");
                        if (sesiune.getState() == SessionState.CLOSED)
                            throw new InvalidStateException("ERR E_STATE CLOSED");
                        if (sesiune.getState() != SessionState.OPEN)
                            throw new InvalidStateException("ERR E_STATE NOT_OPEN");
                        sesiune.setState(SessionState.CLOSED);
                        System.out.println("OK CLOSED");
                        break;
                    default:
                        throw new InvalidParametersException("ERR E_PARSE UNKNOWN_COMMAND");
                }
            }
            catch(InvalidStateException e){
                System.out.println(e.getMessage());
            }
            catch(InvalidParametersException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
