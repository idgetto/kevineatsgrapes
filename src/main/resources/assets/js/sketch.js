var paused;
var gameOver;

var Enemies;
var Grapes;
var kevin;

var life;
var score;

var lastSpawnedGrape;
var lastSpawnedEnemy;
var lastSpedUp;

var interval;
var velocity;

var speedUpInterval = 100;

var img;

function preload(){
  img = loadImage("/assets/images/kev.png");
  //img = loadImage("http://i.imgur.com/5oH9PkQ.png");

}

function setup() {
  pixelDensity(1);
  var canvas = createCanvas(600,600);
  canvas.parent('sketch-holder');

  //create Kevin
  kevin = new Kevin(width/2);

  //reset or set for first time
  paused = true;
  life = 4;
  score = 0;
  enemyInterval = 1000;
  grapeInterval = 1000;
  scoreIncrementInterval = 500;
  velocity = 2;
  lastSpawnedEnemy = enemyInterval;
  lastSpawnedGrape = grapeInterval/2;
  lastIncrementedScore = 0;
  lastSpedUp = 0;




  Enemies = [];
  Grapes = [];

  gameOver = false;

  // Grapes.push(new Grape());
  // Enemies.push(new Enemy());

}

function draw() {
  if(!paused)
  {
    background(255)
    //Kevin
    kevin.update();
    kevin.show();

    calculateVel();

    //Enemies
    generateEnemies();
    for (var i = Enemies.length-1; i >= 0; i--) {
      Enemies[i].update();
      Enemies[i].show();
      if(Enemies[i].isOutsideCanvas()){
        Enemies.splice(i,1);
      }
      else if(isTouching(Enemies[i])){
        life = life - 1;
        Enemies.splice(i,1);
      }
    }

    //Grapes
    generateGrapes();
    for (var i = Grapes.length-1; i >= 0; i--) {
      Grapes[i].update();
      Grapes[i].show();
      if(Grapes[i].isOutsideCanvas()){
        Grapes.splice(i,1);
      }
      else if(isTouching(Grapes[i])){
        score = score + 1;
        Grapes.splice(i,1);
      }
    }

    //Scoreboard
    checkScore();
    showScore();
  }
  else
  {
    //Display text: "Click to start."
    //Show grey overlay
    background(54);
    fill(255);
    textAlign(CENTER,CENTER);
    textSize(60);
    textStyle(BOLD);
    if(gameOver == true){
      text("Game Over!",width/2,height/2-30);
      textSize(35);
      text("Score: "+score,width/2,height/2+30);
    }
    else {
      text("Click to start",width/2,height/2);
      textSize(20);
      textStyle(ITALIC);
      text("Collect grapes and avoid lunch trays.",width/2,height/2+55);
    }
  }
  //console.log(lastSpawnedEnemy, lastSpawnedGrape, lastSpedUp);

}

function mousePressed(){
  if(paused){
    paused = false;
  }
  if(gameOver){
    setup();
  }
}

function touchStarted(){
  if(paused){
    paused = false;
  }
  if(gameOver){
    setup();
  }
}

function generateEnemies(){
  if(millis()-lastSpawnedEnemy > enemyInterval){
    Enemies.push(new Enemy(velocity));
    lastSpawnedEnemy = millis();
  }

}

function generateGrapes(){
  if(millis()-lastSpawnedGrape > grapeInterval) {
    Grapes.push(new Grape(velocity));
    lastSpawnedGrape = millis();
  }

}

function checkScore(){
  // if(millis()-lastIncrementedScore > scoreIncrementInterval) {
  // 	score = score + 1;
  // 	lastIncrementedScore=millis();
  // }

  if(life <= 0){
    gameOver = true;
    paused = true;
  }
}

function showScore(){
  textSize(15);
  textAlign(LEFT, TOP);
  fill(0);
  textStyle(ITALIC);
  text("GRAPES",5,5);
  text("LIFE",5,55);
  fill(231, 76, 60);
  textStyle(BOLD);
  textSize(30);
  text(score,5,20);
  // text(life,5,70);
  push();
  noStroke();
  translate(15,84);
  for (var i = 0; i < life; i++) {
    ellipse(0,0,20,20);
    translate(25,0);
  }
  pop();
}

function isTouching(thing){
  distanceSq = pow(kevin.x-thing.x,2) + pow(kevin.y-thing.y,2);
  minDist = pow(kevin.w/2 + thing.w/2,2)
  if(distanceSq < minDist){
    return true;
  }
  else return false;
}

function calculateVel(){
  if(millis()-lastSpedUp > speedUpInterval) {
    if(velocity <= 15){
      velocity = velocity + .017;
    }

    enemyInterval = 2000/velocity;
    //console.log(velocity);
    lastSpedUp = millis();
  }
}