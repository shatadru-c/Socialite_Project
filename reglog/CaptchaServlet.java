/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package reglog;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;


public class CaptchaServlet extends HttpServlet {


  protected void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
                 throws ServletException, IOException {

    int width = 200;
    int height = 50;

    char data[][] = {
        { 'h', 'e', 'r', 'a', 't' },
        { 'p', 'l', 'a', 'c', 'i', 'd'},
        { 'm', 'c', 'l', 'a', 'r', 'e','n' },
        { 'g', 'a', 'l','l','a','r','d','o' },
        {'c','l','i','c','h','e'},
        {'v','o','x','p','o','p','u','l','i'},
        {'i','n','f','o','c','a','t','s'},
    };


    BufferedImage bufferedImage = new BufferedImage(width, height,
                  BufferedImage.TYPE_INT_RGB);

    Graphics2D g2d = bufferedImage.createGraphics();

    Font font = new Font("Georgia", Font.BOLD, 18);
    g2d.setFont(font);

    RenderingHints rh = new RenderingHints(
           RenderingHints.KEY_ANTIALIASING,
           RenderingHints.VALUE_ANTIALIAS_ON);

    rh.put(RenderingHints.KEY_RENDERING,
           RenderingHints.VALUE_RENDER_QUALITY);

    g2d.setRenderingHints(rh);

    GradientPaint gp = new GradientPaint(0, 0,
    Color.blue, 0, height/2, Color.green, true);

    g2d.setPaint(gp);
    g2d.fillRect(0, 0, width, height);

    g2d.setColor(new Color(230, 12, 0));

    Random r = new Random();
    int index = Math.abs(r.nextInt()) % 7;

    String captcha = String.copyValueOf(data[index]);
    request.getSession().setAttribute("captcha", captcha );

    int x = 0;
    int y = 0;

    for (int i=0; i<data[index].length; i++) {
        x += 10 + (Math.abs(r.nextInt()) % 15);
        y = 20 + Math.abs(r.nextInt()) % 20;
        g2d.drawChars(data[index], i, 1, x, y);
    }

    g2d.dispose();

    response.setContentType("image/png");
    OutputStream os = response.getOutputStream();
    ImageIO.write(bufferedImage, "png", os);
    os.close();
  }


    @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response)
                           throws ServletException, IOException {
      processRequest(request, response);
  }


  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
                            throws ServletException, IOException {
      processRequest(request, response);
  }
}
