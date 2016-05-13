import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetector {
		  public void run(){
		    CascadeClassifier faceDetector = new CascadeClassifier(".\\img\\lbpcascade_frontalface.xml");
		    //Mat image = Imgcodecs.imread("D:\\¿â\\ÎÄµµ\\eclipse workspace\\OpenCV_Demo\\img\\3905167450155406.jpg");
		    Mat image = Imgcodecs.imread(".\\img\\3905167450155406.jpg");
		     showResult(image);
		    		    MatOfRect faceDetections = new MatOfRect();
		    faceDetector.detectMultiScale(image, faceDetections);

		    System.out.println(String.format("Detected %s faces",
		        faceDetections.toArray().length));
		    for (Rect rect : faceDetections.toArray()) {
		    	Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x
		          + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
		    }

		    String filename = "./Result/FaceDetect.png";
		    System.out.println(String.format("Writing %s", filename));
		    Imgcodecs.imwrite(filename, image);
		    showResult(image);
		  }
		  public static void showResult(Mat img) {
			    //Imgproc.resize(img, img, new Size(640, 480));
			    MatOfByte matOfByte = new MatOfByte();
			    Imgcodecs.imencode(".jpg", img, matOfByte);
			    byte[] byteArray = matOfByte.toArray();
			    BufferedImage bufImage = null;
			    try {
			        InputStream in = new ByteArrayInputStream(byteArray);
			        bufImage = ImageIO.read(in);
			        JFrame frame = new JFrame();
			        frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));
			        frame.pack();
			        frame.setVisible(true);
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
		}

