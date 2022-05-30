package tcgstudio2022.openMC.util.input;


import tcgstudio2022.openMC.util.LogHandler;
import org.lwjgl.input.Keyboard;

import java.util.HashMap;
import java.util.Set;

public class KeyInputHandler {
    public static HashMap<String, KeyEvent> registeredKeyEvents=new HashMap<>(131072);
    public static void registerKeyEvent(String id,KeyEvent event){
        registeredKeyEvents.put(id,event);
        LogHandler.log("registered new key event,id="+id, LogHandler.Errors.INFO,"KeyInputHandler");
    }
    public static void deleteKeyEvent(String id){
        if(registeredKeyEvents.containsKey(id)){
            registeredKeyEvents.remove(id);
        }else{
            LogHandler.log("invalid key event id"+id, LogHandler.Errors.WARNING,"KeyInputHandler");
        }
    }
    public static void tick(){
        boolean[] triggers=new boolean[256];
        while (Keyboard.next()) {
            triggers[Keyboard.getEventKey()]=Keyboard.getEventKeyState();
        }
        //read all keys
        Set<String> toSet=registeredKeyEvents.keySet();
        for(String s:toSet){
            registeredKeyEvents.get(s).action(triggers);
        }
    }
}
