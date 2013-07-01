package org.jmrtd.jj2000.test;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jmrtd.jj2000.Bitmap;
import org.jmrtd.jj2000.JJ2000Decoder;

public class ImageTest {

//	private static final String J2K_FILE_IN = "./tmp/1.jp2";
//	private static final String J2K_FILE_IN = "t:/paspoort/test/mo_0102_face.jp2";
	private static final String J2K_FILE_IN = "t:/Pictures/jpeg2k/potholes2.jp2";


	/**
	 * For experimenting with bit rates.
	 */
	public ImageTest() {
		try {
			final File fileIn = new File(J2K_FILE_IN);
			InputStream inputStream = new FileInputStream(fileIn);

			Bitmap bitmap = JJ2000Decoder.decode(inputStream);
			show("Bitmap", bitmap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void show(String title, Bitmap bitmap) {
		BufferedImage image = toBufferedImage(bitmap);		
		JFrame frame = new JFrame(title);
		frame.setLayout(new BorderLayout());
		frame.getContentPane().add(new JLabel(new ImageIcon(image)));
		frame.pack();
		frame.setVisible(true);
	}

	private static BufferedImage toBufferedImage(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int[] pixels = bitmap.getPixels();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		WritableRaster raster = image.getRaster();
		raster.setDataElements(0, 0, width, height, pixels);
		return image;
	}

	public static void main(String[] arg) {
		new ImageTest();
	}
}