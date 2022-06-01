package tcgstudio2022.openMC.render.renderer;


import tcgstudio2022.openMC.render.ShapeRenderer;
import tcgstudio2022.openMC.resources.textures.TextureManagerDirect;

public class FontRenderer {
   public static final int UNICODE_FONT_START_PAGE=50;
   public static final int UNICODE_FONT_END_PAGE=99;
   public enum Alignment{
      LEFT,
      MIDDLE,
      RIGHT
   }
   public static void render(String s, int x, int y, int color,int size,Alignment alignment) {
      char[] rawData = s.toCharArray();
      int contWidth=0;
      for (char c : rawData) {
         int pageCode = (int) Math.floor(c / 256.0f);
         int charPos_Page = c % 256;
         String s2=Integer.toHexString(pageCode);
         if(s2.equals("0")){
            contWidth+=size/2;
         }else {
            contWidth+= size;
         }
      }
      int charPos_scr=0;
      switch (alignment){
         case LEFT -> charPos_scr=x;
         case MIDDLE -> charPos_scr= (int) (x-contWidth/2.0f);
         case RIGHT -> charPos_scr=x-contWidth;
      }
      ShapeRenderer.setColor(color);
      for (char c : rawData) {
         int pageCode = (int) Math.floor(c / 256.0f);
         int charPos_Page = c % 256;
         String s2=Integer.toHexString(pageCode);
         TextureManagerDirect.bind("/assets/minecraft/textures/font/unicode_page_" + s2+".png");
         int charPos_V = charPos_Page / 16;
         int charPos_H = charPos_Page % 16;
         if(c==0x0020) {
            charPos_scr+=size*0.75;
         }else{
            ShapeRenderer.drawRectUV(charPos_scr, charPos_scr + size, y, y + size, 0, 0, charPos_H / 16.0, charPos_H / 16.0 + 1 / 16.0, charPos_V / 16.0, charPos_V / 16.0 + 1 / 16.0);
            if(s2.equals("0")){
               charPos_scr+=size/2;
            }else {
               charPos_scr += size;
            }
         }
      }
   }
}
