package tcgstudio2022.openMC.util;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogHandler {

    public enum Errors{
        INFO,
        WARNING,
        ERROR,
        EXCEPTION,
        GL_ERROR
    }

    public static void checkGLError(String status) {
        int errorStatus= GL11.glGetError();
        if (errorStatus != 0) {
            String errorString = GLU.gluErrorString(errorStatus);
            log(errorString,Errors.GL_ERROR,status);
            System.exit(0);
        }
    }

    public static String log(String message,Errors error,String sourceName){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd ',' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        StringBuilder textBuilder=new StringBuilder(512);
        textBuilder.append("[").append(formatter.format(date)).append("]");
        String type="unknown";
        switch (error){
            case INFO -> type="info";
            case WARNING -> type="warning";
            case ERROR -> type="error";
            case EXCEPTION -> type="exception";
            case GL_ERROR -> type="GL-Error";
        }
        textBuilder.append("[").append(type).append("]");
        textBuilder.append("[").append(sourceName).append("]");
        textBuilder.append(message);
        System.out.println(textBuilder);
        return textBuilder.toString();
    }

}