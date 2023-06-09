import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class CreateStickers {
  
  public void create(InputStream inputStream, String nomeArquivo, String textoFigurinha) throws IOException {
    BufferedImage imgOriginal = ImageIO.read(inputStream);

    int largura = imgOriginal.getWidth();
    int novaAltura = imgOriginal.getHeight() + 200;
    BufferedImage novaImg = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

    //copiar imagem original p/ nova img (em memória)
    Graphics2D graficos = (Graphics2D) novaImg.getGraphics();
    graficos.drawImage(imgOriginal, 0, 0, null);

    //escreve frase em nova img
    var fonte = new Font("Sans_serif", Font.BOLD, 72);
    graficos.setFont(fonte);
    graficos.setColor(Color.RED);

    FontMetrics fonteMetrica = graficos.getFontMetrics();
    Rectangle2D retangulo = fonteMetrica.getStringBounds(textoFigurinha, graficos);
    int posicaoTextoX = (largura - (int) retangulo.getWidth()) / 2;

    graficos.drawString(textoFigurinha, posicaoTextoX, novaAltura - 100);

    //escreve nova img no arquivo
    ImageIO.write(novaImg, "png", new File(nomeArquivo));
  }

}