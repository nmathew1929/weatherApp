package App;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;

import JavaAPI.CurrentWeather;
import JavaAPI.OpenWeatherMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application  {
	private String city;

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start (Stage primaryStage) throws IOException, MalformedURLException, JSONException {
			
		
        BorderPane pane = new BorderPane();
		TextField input = new TextField(); // input text
		Text text1 = new Text(); // For the Temp.
		Text text2 = new Text(); // Date
		Text text3 = new Text(); // Weather Description 
		Text text4 = new Text();
		Text text5 = new Text(); //error message
		
		text1.setFont(Font.font ("Verdana", 20));
		text1.setFill(Color.WHITE);
		text2.setFont(Font.font ("Verdana", 20));
		text2.setFill(Color.WHITE);
		text3.setFont(Font.font ("Verdana", 20));
		text3.setFill(Color.WHITE);
		text4.setFont(Font.font ("Verdana", 20));
		text4.setFill(Color.WHITE);
		
		text5.setFont(Font.font ("Verdana", 10));
		text5.setFill(Color.RED);
		
		
		
		Label temp = new Label("Temperature: ");
		temp.setFont(Font.font("Helvetica Neue",15));
		temp.setTextFill(Color.WHITE);
		
		
		Label date = new Label("Date and Time: ");
		date.setFont(Font.font("Helvetica Neue",15));
		date.setTextFill(Color.WHITE);	
		
		Label weather = new Label("Weather Description: ");
		weather.setFont(Font.font("Helvetica Neue",15));
		weather.setTextFill(Color.WHITE);
		
		Image WeatherIcon = new Image("01d.png",100,100,false,false);

		ImageView imageView = new ImageView();
		imageView.setImage(./images/WeatherIcon);
	
		

		
		HBox box2 = new HBox();	
		pane.setTop(box2);
		box2.getChildren().add(text5);
		
		
		VBox box = new VBox(5);
		box.getChildren().addAll(new Label("City: "),input,temp,text1, date,text2,weather,text4,imageView);
		box.setPadding(new Insets(10,10,10,10));
		
		
		pane.setCenter(box);
		
		input.setOnAction(e -> {
	
			city = input.getText();

			
			try {
				OpenWeatherMap owm = new OpenWeatherMap("Enter your api here");
				CurrentWeather cwd = owm.currentWeatherByCityName(city);
				
				
				
				//DailyForecast dail = owm.currentWeatherByCityCode(city);
				
				System.out.println(cwd.getDateTime().toInstant());

				// printing city name from the retrieved data
				
				System.out.println("This is the pressure" + cwd.getMainInstance().getPressure());
				System.out.println("The sunrise and the sunset time: " + cwd.getSysInstance().getSunriseTime() + " "+cwd.getSysInstance().getSunsetTime());
				// printing the max./min. temperature
				System.out.println("Temperature: " + cwd.getMainInstance().getMaxTemperature() + "/"
						+ cwd.getMainInstance().getMinTemperature() + "\'F");
				
				
				System.out.println("Wind Speed: " + cwd.getWindInstance().getWindSpeed());
				
				System.out.println (cwd.getMainInstance().getHumidity());
				System.out.println("Description ? " + cwd.getWeatherInstance(0).getWeatherDescription());
				System.out.println("Weather forecast: " + cwd.getWeatherInstance(0).getWeatherName());
				
				
				System.out.println("Weather Icon Display: " + cwd.getWeatherInstance(0).getWeatherIconName());
				String imageString = "./images/";
				imageString += cwd.getWeatherInstance(0).getWeatherIconName().toString()+".png";
				
				 imageView.setImage(new Image(imageString,100,100,false,false));
				 imageView.setLayoutX(10);
				 				 
				 
				System.out.println();
				
				text5.setText("");
				text1.setText(cwd.getMainInstance().getMaxTemperature() + "/"
						+ cwd.getMainInstance().getMinTemperature() + "\'F");
				
				text2.setText((cwd.getDateTime().toString()));
				
				text3.setText(cwd.getWeatherInstance(0).getWeatherName());
				text4.setText(cwd.getWeatherInstance(0).getWeatherDescription());
				
				System.out.println((cwd.getDateTime().toString()));
				

			
				
			} catch (JSONException g) {
				System.out.println("Json Exception");
			} catch (MalformedURLException d) {
				System.out.println("Bad URL");
			} catch (IOException sa) {
				System.out.println("IOException");
			}
			catch(NullPointerException g) {
				System.out.println("Please enter a valid City name without any errors.");
				text5.setText("City not found, please enter a valid city name.");
			}
			

		});
		

		Image image = new Image("./images/morning.jpg");
		Image image2  = new Image ("./images/night.jpg");
		

		
	    BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
	    Background background1 = new Background(new BackgroundImage(image,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            bSize));
	    

	    
	    pane.setBackground(background1);
	  
		Scene scene = new Scene(pane, 600,350);

		
		primaryStage.setTitle("Weather App");
		primaryStage.setScene(scene);
		
		primaryStage.show();
        
      
		
		
	}
	
}
