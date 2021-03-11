import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class genie_2_manyStyles extends PApplet {

int pics = 1; // how many pics fo you want to save? //<>// //<>//
//int num = 6; // what kind of shapes? 1: vertex polygon, 2: arcs, 3: polygon + arc 
int num = PApplet.parseInt(random(1, 7));


//these arent being used.. doing random
int divsLow = 2; // how many divison lines? this number minus 1 is the total number of divisons
int divsHigh = 20; // max
int vertexesLow = 3; // how many vertexes for each shape? min
int vertexesHigh = 3; // max
int shapesLow = 2; // how many shapes? min
int shapesHigh = 50; // max
int fadeVar = PApplet.parseInt(random(1, 8)); // how quickly the shapes fade away

int divs;
int vertexes;
int shapes;

int xyArraySize = vertexes*shapes;
int[] colarray = new int[divs];
int[] colArray2 = new int[shapes];
int[] randomArray = new int[divs];
int[] xArray = new int[xyArraySize];
int[] yArray = new int[xyArraySize];

public void setup() {
  
  //background(0);    // no background needed
  noStroke();
  //noLoop(); // only run the code once
}

public void draw () {
  //for (int k=0; k<pics; k++) { // save each time through
  // create a random array with pixel numbers in the x direction we are slicing
  // create random color array
  divs = PApplet.parseInt(random(divsLow, divsHigh)); // create random number of divs between x and y...
  vertexes = PApplet.parseInt(random(vertexesLow, vertexesHigh)); // ^
  shapes = PApplet.parseInt(random(shapesLow, shapesHigh));
  int xyArraySize = vertexes*shapes;
  int[] colarray = new int[divs];
  int[] colArray2 = new int[shapes];
  int[] randomArray = new int[divs];
  int[] xArray = new int[xyArraySize];
  int[] yArray = new int[xyArraySize];
  for (int i = 0; i < divs; i++) {
    colarray[i] = color(random(255), random(255), random(255));
    randomArray[i] = PApplet.parseInt(random(width));
  }
  for (int i =0; i<xyArraySize; i++) {
    xArray[i] = PApplet.parseInt(random(width));
    yArray[i] = PApplet.parseInt(random(height));
  }
  for (int i=0; i<shapes; i++) {
    colArray2[i] = color(random(255), random(255), random(255));
  }
  randomArray[0] = 0; // make first x value 0, this is the starting point (0,0);
  randomArray[divs-1] = width; // last value needs to be the final pixel so the div 'background' covers the whole screen. 
  randomArray = sort(randomArray); // sort from lowest to highest so we can cycle through and make boxes


  // BACKGROUND - create random rectangles with random colors
  for (int i = 0; i < divs-1; i++) {
    fill(colarray[i]);
    rect(randomArray[i], 0, randomArray[i+1], height);  // Draw rectangle
  }
  //SHAPES
  switch(num) {
    //Vertx polygons
  case 1:
    for (int i=0; i<shapes; i++) {  //draw the shapes
      fill(colArray2[i], 255-(i*fadeVar));
      beginShape();
      for (int j = 0; j<vertexes; j++) {  // vertex of each shape drawing
        int arrayIndex = j+(vertexes*i); //TODO FIGURE THIS INDEX THING OUT
        //if (i>0 && j==0) {
        //arrayIndex = (j+1)*(i+1);
        //}
        vertex(xArray[arrayIndex], yArray[arrayIndex]);
      }
      endShape();
    }
    break;
    // Arcs
  case 2:
    for (int i=0; i<shapes; i++) {  //draw the shapes
      fill(colArray2[i], 255-(fadeVar*i));
      //for (int j = 0; j<4; j++) {
      int arrayIndex = (i*2); 
      arc(xArray[arrayIndex], yArray[arrayIndex], PApplet.parseInt(random(100, 900)), PApplet.parseInt(random(300.900f)), random(0, 2*PI), random(0, 2*PI), OPEN);
      //}
    }
    break;
    //Vertex Polygons + Arcs
  case 3:
    for (int i=0; i<shapes; i++) {  //draw the shapes
      fill(colArray2[i], 255-(i*fadeVar));
      beginShape();
      for (int j = 0; j<vertexes; j++) {  // vertex of each shape drawing
        int arrayIndex = j+(vertexes*i); //TODO FIGURE THIS INDEX THING OUT
        //if (i>0 && j==0) {
        //arrayIndex = (j+1)*(i+1);
        //}
        vertex(xArray[arrayIndex], yArray[arrayIndex]);
      }
      endShape();
    }
    for (int i=0; i<shapes; i++) {  //draw the shapes
      fill(colArray2[i], 255-(fadeVar*i));
      //for (int j = 0; j<4; j++) {
      int arrayIndex = (i*2); 
      arc(xArray[arrayIndex], yArray[arrayIndex], PApplet.parseInt(random(100, 900)), PApplet.parseInt(random(300.900f)), random(0, 2*PI), random(0, 2*PI), OPEN);
      //}
    }
    break;
    // Squares
  case 4:
    for (int i=0; i<shapes; i++) {  //draw the shapes
      fill(colArray2[i], 255-(fadeVar*i));
      //for (int j = 0; j<4; j++) {
      int arrayIndex = (i*2); 
      square(xArray[arrayIndex], yArray[arrayIndex], xArray[arrayIndex+1]/fadeVar);
    }
    break;
    //Squares + Arcs
  case 5:
    for (int i=0; i<shapes; i++) {  //draw the shapes
      fill(colArray2[i], 255-(fadeVar*i));
      //for (int j = 0; j<4; j++) {
      int arrayIndex = (i*2); 
      square(xArray[arrayIndex], yArray[arrayIndex], xArray[arrayIndex+1]/fadeVar);
    }
    for (int i=0; i<shapes; i++) {  //draw the shapes
      fill(colArray2[i], 255-(fadeVar*i));
      //for (int j = 0; j<4; j++) {
      int arrayIndex = (i*2); 
      arc(xArray[arrayIndex], yArray[arrayIndex], PApplet.parseInt(random(100, 900)), PApplet.parseInt(random(300.900f)), random(0, 2*PI), random(0, 2*PI), OPEN);
      //}
    }
    break;
  case 6:
    //squares + arcs + polygons
    for (int i=0; i<shapes/2; i++) {  //draw the shapes
      fill(colArray2[i], 255-(i*fadeVar));
      beginShape();
      for (int j = 0; j<vertexes; j++) {  // vertex of each shape drawing
        int arrayIndex = j+(vertexes*i); //TODO FIGURE THIS INDEX THING OUT
        //if (i>0 && j==0) {
        //arrayIndex = (j+1)*(i+1);
        //}
        vertex(xArray[arrayIndex], yArray[arrayIndex]);
      }
      endShape();
    }
    for (int i=0; i<shapes; i++) {  //draw the shapes
      fill(colArray2[i], 255-(fadeVar*i));
      //for (int j = 0; j<4; j++) {
      int arrayIndex = (i*2); 
      square(xArray[arrayIndex], yArray[arrayIndex], xArray[arrayIndex+1]/fadeVar);
    }
    for (int i=0; i<shapes; i++) {  //draw the shapes
      fill(colArray2[i], 255-(fadeVar*i));
      //for (int j = 0; j<4; j++) {
      int arrayIndex = (i*2); 
      arc(xArray[arrayIndex], yArray[arrayIndex], PApplet.parseInt(random(100, 900)), PApplet.parseInt(random(300.900f)), random(0, 2*PI), random(0, 2*PI), OPEN);
      //}
    }

    break;
  }
  if (frameCount == pics) {
    noLoop();
  }
  saveFrame("output.png");
  exit();
}
  public void settings() {  size(1024, 512); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "genie_2_manyStyles" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
