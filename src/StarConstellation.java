/*
This Program generates random stars field and then allows the user to create a constellation by connecting stars with lines.
The user is prompted to enter the X and Y stars coordinates in the constellation and the constellation's title.
The program will also displays the programmer's name on the screen.
*/

import java.io.File;
import javafx.application.Application;
import javafx.animation.Timeline;
import javafx.animation.KeyValue;
import javafx.animation.KeyFrame;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;


public class StarConstellation extends Application {

    private String title;
    public static final int WIDTH = 900;
    public static final int HEIGHT = 700;
    public static final int STARS = 72;
    public static final int RECTANGLES = 67;
    private ArrayList<Circle> stars = new ArrayList<>();
    private ArrayList<Circle> constellation = new ArrayList<>();
    private Group root;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage){
        root = new Group();

        //Adding background image for the constellation
        Image backgroundImage = new Image(new File("C:\\Users\\User\\Desktop\\Star Constellation Program\\src\\pics\\background 2.jpeg").toURI().toString());
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(WIDTH);
        backgroundView.setFitHeight(HEIGHT);
        root.getChildren().add(backgroundView);
        
        //Generating a random star field
        //Add circle stars to the random star field
        for(int i=0;i<STARS;i++){
            double x = Math.random()*WIDTH;
            double y = Math.random()*HEIGHT;
            Circle star = new Circle(x,y,3,Color.AQUAMARINE);
            stars.add(star);
            KeyValue xValue = new KeyValue(star.centerXProperty(),200);
            KeyValue yValue = new KeyValue(star.centerYProperty(),200);
            KeyFrame startFrame = new KeyFrame(Duration.ZERO,xValue);
            KeyFrame endFrame = new KeyFrame(Duration.millis(100000),yValue);
            
            Timeline timeline = new Timeline(startFrame,endFrame);
            timeline.setAutoReverse(true);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
            
        }
        //Add rectangles to the random star field
        for (int i=0;i<RECTANGLES;i++){
        double x = Math.random()*WIDTH;
        double y = Math.random()*HEIGHT;
        Rectangle rectangle = new Rectangle(x-2, y-2, 5, 5);
        rectangle.setFill(Color.YELLOWGREEN);
        root.getChildren().addAll(rectangle);
        
            KeyValue startWidth = new KeyValue(rectangle.widthProperty(),5);
            KeyValue endWidth = new KeyValue(rectangle.widthProperty(),5);
            KeyValue startHeight = new KeyValue(rectangle.heightProperty(),5);
            KeyValue endHeight = new KeyValue(rectangle.heightProperty(),5);
            KeyFrame startFrame = new KeyFrame(Duration.ZERO,startWidth,startHeight);
            KeyFrame endFrame = new KeyFrame(Duration.millis(70000),endWidth,endHeight);
            
            Timeline timeline = new Timeline(startFrame,endFrame);
            timeline.setAutoReverse(true);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }

        
        //Allowing user to create the constellation
        Scanner scanner = new Scanner(System.in);
        //Getting the title of the constellation from user
        System.out.println("Enter a title for the constellation:");
        String title = scanner.nextLine();
        //Getting the X and Y coordinates of the stars from users
        System.out.println("Enter the X and Y coordinates of the stars in the constellation Bound(0>X<800, 0>Y<600) (q to quit):");
        int count=0;
        while(scanner.hasNextDouble()&&count<4){
            try{
           double x = scanner.nextDouble();
           double y = scanner.nextDouble();
           //checking if the values are within the screen bounds
           if(x>=0&&x<=800&&y>=0&&y<=600){
               Circle star = new Circle(x,y,5,Color.DARKBLUE);
               constellation.add(star);
               
               count++;
           }else{
               System.out.println("Invalid input. please enter values within the screen bounds(0>X<900, 0>Y<700).");
           }}catch(InputMismatchException e){
               System.out.println("Invalid input, please enter a valid number.");
           }
        }
        
        
        //Adding all the generated stars to the constellation
        root.getChildren().addAll(stars);
        root.getChildren().addAll(constellation);
        
        //connecting the stars
        for(int i=0;i<constellation.size()-1;i++){
            Line line = new Line(constellation.get(i).getCenterX(),constellation.get(i).getCenterY(),constellation.get(i+1).getCenterX(),constellation.get(i+1).getCenterY());
            line.setStroke(Color.RED);
            root.getChildren().add(line);
            
            //Connect last star to first
            Line lastLine = new Line(constellation.get(constellation.size()-1).getCenterX(),constellation.get(constellation.size()-1).getCenterY(),constellation.get(0).getCenterX(),constellation.get(0).getCenterY());
            lastLine.setStroke(Color.BEIGE);
            lastLine.setStrokeWidth(4);
            root.getChildren().addAll(lastLine);

        }
        
        //Display the title
        Text titleText = new Text(10,20,title);
        titleText.setFill(Color.WHITE);
        root.getChildren().add(titleText);
        
        //Display programmer's credit
        Text credits = new Text(10,HEIGHT-20,"Programmed by [Programmer's Name]");
        credits.setFill(Color.WHITE);
        root.getChildren().add(credits);
        

        Scene scene = new Scene(root,WIDTH,HEIGHT);
        scene.setFill(Color.DARKBLUE);

       
        primaryStage.setTitle("Constellation");
        primaryStage.setScene(scene);
        primaryStage.show();
        //background music
         Media sound= new Media (new File("C:\\Users\\User\\Desktop\\Star Constellation Program\\src\\audio\\Far Out & Karra - Constellation.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(()->mediaPlayer.stop());
        mediaPlayer.play();
        
    }
}
